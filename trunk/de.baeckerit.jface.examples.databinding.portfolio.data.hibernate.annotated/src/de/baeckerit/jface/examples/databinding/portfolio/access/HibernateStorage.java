package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.services.IDisposable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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

  @SuppressWarnings("unchecked")
  private <T> List<T> list(Criteria crit, ArrayList<T> input) {
    session.beginTransaction();
    input.addAll(crit.list());
    session.getTransaction().commit();
    return input;
  }

  private Criteria critForClass(Class<?> type, boolean cacheable) {
    return session.createCriteria(type).setCacheable(cacheable);
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
  public boolean addSecurity(ISecurity security) {
    if (!(security instanceof Security)) {
      throw new IllegalArgumentException("Must be an " + Security.class);
    }
    session.beginTransaction();
    session.save(security);
    session.getTransaction().commit();
    return true;
  }

  @Override
  public boolean addSecurityPosition(ISecurityPosition position) {
    if (!(position instanceof SecurityPosition)) {
      throw new IllegalArgumentException("Must be an " + SecurityPosition.class);
    }
    session.beginTransaction();
    session.save(position);
    session.getTransaction().commit();
    return true;
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
