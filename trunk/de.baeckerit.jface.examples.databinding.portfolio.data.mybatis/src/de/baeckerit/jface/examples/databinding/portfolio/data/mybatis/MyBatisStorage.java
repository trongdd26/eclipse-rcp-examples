package de.baeckerit.jface.examples.databinding.portfolio.data.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.ui.services.IDisposable;

import de.baeckerit.jface.examples.databinding.portfolio.access.IDataAccess;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurity;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityPosition;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityType;

public class MyBatisStorage implements IDataAccess, IDisposable {

  private static final Logger LOGGER = LogManager.getLogger(MyBatisStorage.class);

  public static final MyBatisStorage INSTANCE = new MyBatisStorage();

  private SqlSessionFactory sqlSessionFactory;

  private final Map<String, ISecurityType> types = new HashMap<>();
  private final Map<String, ISecurityDirection> directions = new HashMap<>();
  private final Map<Integer, ISecurity> securities = new HashMap<>();

  @Override
  public void dispose() {
  }

  public MyBatisStorage() {
    try {
      InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException e) {
      LOGGER.error("MyBatis initialization failed", e);
    }

    getSecurityDirections();
    getSecurityTypes();
  }

  @Override
  public synchronized List<ISecurityType> getSecurityTypes() {
    if (types.isEmpty()) {
      SqlSession session = sqlSessionFactory.openSession();
      try {
        PortfolioMapper mapper = session.getMapper(PortfolioMapper.class);
        List<SecurityType> results = mapper.selectAllSecurityTypes();
        for (SecurityType type : results) {
          types.put(type.getPrimaryKey(), type);
        }
      } catch (Throwable t) {
        LOGGER.error("Unable to get all security types", t);
      } finally {
        session.close();
      }
    }
    return new ArrayList<>(types.values());
  }

  @Override
  public synchronized List<ISecurityDirection> getSecurityDirections() {
    if (directions.isEmpty()) {
      SqlSession session = sqlSessionFactory.openSession();
      try {
        PortfolioMapper mapper = session.getMapper(PortfolioMapper.class);
        List<SecurityDirection> results = mapper.selectAllSecurityDirections();
        for (SecurityDirection direction : results) {
          directions.put(direction.getPrimaryKey(), direction);
        }
      } catch (Throwable t) {
        LOGGER.error("Unable to get all security directions", t);
      } finally {
        session.close();
      }
    }
    return new ArrayList<>(directions.values());
  }

  @Override
  public synchronized List<ISecurity> getSecurities() {
    if (securities.isEmpty()) {
      SqlSession session = sqlSessionFactory.openSession();
      try {
        PortfolioMapper mapper = session.getMapper(PortfolioMapper.class);
        List<Security> results = mapper.selectAllSecurities();
        for (Security security : results) {
          security.setSecurityType(types.get(security.getFkSecurityType()));
          if (security.getFkSecurityDirection() != null) {
            security.setSecurityDirection(directions.get(security.getFkSecurityDirection()));
          }
          securities.put(security.getPrimaryKey(), security);
        }
      } catch (Throwable t) {
        LOGGER.error("Unable to get all securities", t);
      } finally {
        session.close();
      }
    }
    return new ArrayList<>(securities.values());
  }

  public synchronized List<ISecurity> getSecuritiesByIsin(String isin) {
    return null;
  }

  private List<ISecurityPosition> mapPositions(List<SecurityPosition> positions) {
    ArrayList<ISecurityPosition> mapped = new ArrayList<>();
    for (SecurityPosition p : positions) {
      p.setSecurity(securities.get(p.getFkSecurity()));
      mapped.add(p);
    }
    return mapped;
  }

  @Override
  public synchronized List<ISecurityPosition> getOpenPositions() {
    SqlSession session = sqlSessionFactory.openSession();
    try {
      PortfolioMapper mapper = session.getMapper(PortfolioMapper.class);
      return mapPositions(mapper.selectOpenSecurityPositions());
    } catch (Throwable t) {
      LOGGER.error("Unable to get all open positions", t);
    } finally {
      session.close();
    }
    return new ArrayList<>();
  }

  @Override
  public synchronized List<ISecurityPosition> getClosedPositions() {
    SqlSession session = sqlSessionFactory.openSession();
    try {
      PortfolioMapper mapper = session.getMapper(PortfolioMapper.class);
      return mapPositions(mapper.selectClosedSecurityPositions());
    } catch (Throwable t) {
      LOGGER.error("Unable to get all closed positions", t);
    } finally {
      session.close();
    }
    return new ArrayList<>();
  }

  @Override
  public AddSecurityResult addSecurity(ISecurity security) {
    SqlSession session = sqlSessionFactory.openSession();
    try {
      PortfolioMapper mapper = session.getMapper(PortfolioMapper.class);
      mapper.insertSecurity((Security) security);
      session.commit();
      securities.put(security.getPrimaryKey(), security);
    } catch (PersistenceException pe) {
      if (pe.getCause() instanceof SQLException) {
        SQLException sqlException = (SQLException) pe.getCause();
        if (sqlException.getErrorCode() == 20101) {
          return AddSecurityResult.OVERLAPPING;
        }
      }
      LOGGER.error("Unable to insert new security", pe);
      return AddSecurityResult.UNKNOWN;
    } catch (Throwable t) {
      LOGGER.error("Unable to insert new security", t);
      return AddSecurityResult.UNKNOWN;
    } finally {
      session.close();
    }
    return AddSecurityResult.OK;
  }

  @Override
  public boolean addSecurityPosition(ISecurityPosition position) {
    SqlSession session = sqlSessionFactory.openSession();
    try {
      PortfolioMapper mapper = session.getMapper(PortfolioMapper.class);
      mapper.insertPosition((SecurityPosition) position);
      session.commit();
      return true;
    } catch (Throwable t) {
      LOGGER.error("Unable to insert new security position", t);
      return false;
    } finally {
      session.close();
    }
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
