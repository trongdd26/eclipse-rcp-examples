package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.services.IDisposable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurity;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityPosition;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityType;

public class HibernateStorage implements IDataAccess, IDisposable {

  public static final HibernateStorage INSTANCE = new HibernateStorage();

  private SessionFactory sessionFactory;

  private Session session;

  @Override
  public void dispose() {
    try {
      if (session != null) {
        session.close();
      }
    } catch (Throwable e1) {
      e1.printStackTrace(System.err);
    }
    session = null;
    try {
      if (sessionFactory != null) {
        sessionFactory.close();
      }
    } catch (Throwable e1) {
      e1.printStackTrace(System.err);
    }
    sessionFactory = null;
  }

  private <T> List<T> list(Criteria crit, ArrayList<T> input) {
    session.beginTransaction();
    try {
      internalList(crit, input);
      session.getTransaction().commit();
    } catch (Throwable t) {
      session.getTransaction().rollback();
      input.clear();
    }
    return input;
  }

  @SuppressWarnings("unchecked")
  private <T> ArrayList<T> internalList(Criteria crit, ArrayList<T> input) {
    input.addAll(crit.list());
    return input;
  }

  private Criteria critForClass(Class<?> type, boolean cacheable) {
    return session.createCriteria(type).setCacheable(cacheable);
  }

  private Criteria critForSecuritiesByIsin(String isin) {
    return critForClass(Security.class, true).add(Restrictions.eq("isin", isin));
  }

  @SuppressWarnings("deprecation")
  public HibernateStorage() {
    try {
      sessionFactory = new Configuration().configure().buildSessionFactory();
      session = sessionFactory.openSession();
    } catch (Throwable e1) {
      e1.printStackTrace(System.err);
      return;
    }
    getSecurityDirections();
    getSecurityTypes();
  }

  @Override
  public synchronized List<ISecurityDirection> getSecurityDirections() {
    return list(critForClass(SecurityDirection.class, true), new ArrayList<ISecurityDirection>());
  }

  @Override
  public synchronized List<ISecurityType> getSecurityTypes() {
    return list(critForClass(SecurityType.class, true), new ArrayList<ISecurityType>());
  }

  @Override
  public synchronized List<ISecurity> getSecurities() {
    return list(critForClass(Security.class, true), new ArrayList<ISecurity>());
  }

  public synchronized List<ISecurity> getSecuritiesByIsin(String isin) {
    return list(critForSecuritiesByIsin(isin), new ArrayList<ISecurity>());
  }

  @Override
  public synchronized List<ISecurityPosition> getOpenPositions() {
    Criteria crit = critForClass(SecurityPosition.class, true).add(Restrictions.isNull("closingDate"));
    return list(crit, new ArrayList<ISecurityPosition>());
  }

  @Override
  public synchronized List<ISecurityPosition> getClosedPositions() {
    Criteria crit = critForClass(SecurityPosition.class, true).add(Restrictions.isNotNull("closingDate"));
    return list(crit, new ArrayList<ISecurityPosition>());
  }

  @Override
  public AddSecurityResult addSecurity(ISecurity aSecurity) {
    if (!(aSecurity instanceof Security)) {
      throw new IllegalArgumentException("Must be an " + Security.class);
    }
    session.beginTransaction();
    try {
      Criteria crit = critForSecuritiesByIsin(aSecurity.getIsin());
      if (aSecurity.getLastTradingDay() == null) {
        Criterion first = Restrictions.isNull("lastTradingDay");
        Criterion second = Restrictions.ge("lastTradingDay", aSecurity.getFirstTradingDay());
        crit = crit.add(Restrictions.or(first, second));
      } else {
        Criterion first = Restrictions.gt("firstTradingDay", aSecurity.getLastTradingDay());
        Criterion second = Restrictions.isNotNull("lastTradingDay");
        Criterion third = Restrictions.lt("lastTradingDay", aSecurity.getFirstTradingDay());
        crit = crit.add(Restrictions.not(Restrictions.or(first, Restrictions.and(second, third))));
      }
      ArrayList<ISecurity> securities = internalList(crit, new ArrayList<ISecurity>());
      // Should deliver zero or one entity
      if (securities.size() > 0) {
        return AddSecurityResult.OVERLAPPING;
      }
      session.save(aSecurity);
      return AddSecurityResult.OK;
    } catch (Throwable t) {
      try {
        session.getTransaction().rollback();
      } catch (Exception e1) {
        // ignore
      }
      session.clear();
      return AddSecurityResult.UNKNOWN;
    } finally {
      if (!session.getTransaction().wasRolledBack()) {
        session.getTransaction().commit();
      }
    }
  }

  @Override
  public boolean addSecurityPosition(ISecurityPosition position) {
    if (!(position instanceof SecurityPosition)) {
      throw new IllegalArgumentException("Must be an " + SecurityPosition.class);
    }
    boolean success = false;
    session.beginTransaction();
    try {
      session.save(position);
      session.getTransaction().commit();
      success = true;
    } catch (Throwable t) {
      try {
        session.getTransaction().rollback();
      } catch (Exception e1) {
        // ignore
      }
      session.clear();
      success = false;
    }
    return success;
  }

  @Override
  public ISecurity createSecurity() {
    return new Security();
  }

  @Override
  public ISecurityPosition createSecurityPosition() {
    return new SecurityPosition();
  }
}
