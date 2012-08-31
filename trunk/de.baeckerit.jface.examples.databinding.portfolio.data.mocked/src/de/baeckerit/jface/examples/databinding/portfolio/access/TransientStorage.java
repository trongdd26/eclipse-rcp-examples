package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.baeckerit.jdk.util.isin.IsinUtils;
import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityPosition;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityType;

public class TransientStorage implements IDataAccess {

  public static final TransientStorage INSTANCE = new TransientStorage();

  private final Map<String, SecurityType> types = new HashMap<>();
  private final Map<String, SecurityDirection> directions = new HashMap<>();
  private final Map<Integer, Security> securities = new HashMap<>();
  private final Map<Integer, SecurityPosition> positions = new HashMap<>();
  private int securitieKeySequence = 0;
  private int positionKeySequence = 0;

  public TransientStorage() {
    directions.put("L", new SecurityDirection("L", "Long"));
    directions.put("S", new SecurityDirection("S", "Short"));
    types.put("SHA", new SecurityType("SHA", "Share"));
    types.put("ETF", new SecurityType("ETF", "ETF"));
    types.put("OPT", new SecurityType("OPT", "Option"));

    DateFormat df = DateFormat.getDateInstance();
    try {
      String securityName = "HENKEL AG & CO. KGAA Inhaber-Vorzugsaktien O.ST.O.N";
      internalAddSecurity("SHA", null, securityName, "DE0006048432", df.parse("05.02.1962"), null);
      securityName = "Metro AG Inhaber-Stammaktien O.N.";
      internalAddSecurity("SHA", null, securityName, "DE0007257503", df.parse("05.02.1963"), null);
      securityName = "Daimler AG Namensaktien O.N.";
      internalAddSecurity("SHA", null, securityName, "DE0007100000", df.parse("05.02.1964"), null);
      securityName = "db x-trackers DAX ETF";
      internalAddSecurity("ETF", "L", securityName, "LU0274211480", df.parse("05.02.2000"), null);
      securityName = "db x-trackers ShortDAX ETF";
      internalAddSecurity("ETF", "S", securityName, "LU0292106241", df.parse("05.02.2000"), null);
      securityName = "CALL Daimler AG Namensaktien O.N.";
      internalAddSecurity("OPT", "L", securityName, "DE000BN4EY57", df.parse("05.02.2012"), null);
      securityName = "PUT Daimler AG Namensaktien O.N.";
      internalAddSecurity("OPT", "S", securityName, "DE000BN4FG33", df.parse("05.02.2012"), df.parse("05.03.2012"));
      securityName = "PUT Daimler AG Namensaktien O.N.";
      internalAddSecurity("OPT", "S", securityName, "DE000BN4FG33", df.parse("05.04.2012"), null);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    for (Security security : securities.values()) {
      IsinUtils.verifyIsin(security.getIsin(), true);
    }

    try {
      for (Security security : securities.values()) {
        SecurityPosition p = new SecurityPosition();
        p.setPrimaryKey(createPositionKey());
        p.setBuy(true);
        p.setSecurity(security);
        p.setOpenDate(df.parse("01.01.2012"));
        p.setClosingDate(df.parse("01.02.2012"));
        positions.put(p.getPrimaryKey(), p);

        p = new SecurityPosition();
        p.setPrimaryKey(createPositionKey());
        p.setSecurity(security);
        p.setBuy(false);
        p.setOpenDate(df.parse("01.04.2012"));
        p.setClosingDate(null);
        positions.put(p.getPrimaryKey(), p);
      }
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  private void internalAddSecurity(String typeKey, String directionKey, String securityName, String isin, Date firstTradingDay,
      Date lastTradingDay) {
    SecurityType securityType = types.get(typeKey);
    SecurityDirection securityDirection = directionKey == null ? null : directions.get(directionKey);
    Security security = new Security();
    security.setSecurityType(securityType);
    security.setSecurityDirection(securityDirection);
    security.setSecurityName(securityName);
    security.setIsin(isin);
    security.setFirstTradingDay(firstTradingDay);
    security.setLastTradingDay(lastTradingDay);

    internalAddSecurity(security);
  }

  @Override
  public synchronized List<SecurityDirection> getSecurityDirections() {
    return new ArrayList<>(directions.values());
  }

  @Override
  public synchronized List<SecurityType> getSecurityTypes() {
    return new ArrayList<>(types.values());
  }

  @Override
  public synchronized List<Security> getSecurities() {
    return new ArrayList<>(securities.values());
  }

  @Override
  public synchronized List<SecurityPosition> getOpenPositions() {
    ArrayList<SecurityPosition> openPositions = new ArrayList<>();
    for (SecurityPosition p : positions.values()) {
      if (p.getClosingDate() == null) {
        openPositions.add(p);
      }
    }
    return openPositions;
  }

  @Override
  public synchronized List<SecurityPosition> getClosedPositions() {
    ArrayList<SecurityPosition> openPositions = new ArrayList<>();
    for (SecurityPosition p : positions.values()) {
      if (p.getClosingDate() != null) {
        openPositions.add(p);
      }
    }
    return openPositions;
  }

  @Override
  public boolean addSecurity(Security security) {
    return internalAddSecurity(security);
  }

  @Override
  public boolean addSecurityPosition(SecurityPosition p) {
    p.setPrimaryKey(createPositionKey());
    positions.put(p.getPrimaryKey(), p);
    return true;
  }

  private boolean internalAddSecurity(Security security) {
    security.setPrimaryKey(createSecurityKey());
    Security previous = securities.put(security.getPrimaryKey(), security);
    return previous == null;
  }

  private Integer createSecurityKey() {
    return Integer.valueOf(securitieKeySequence++);
  }

  private Integer createPositionKey() {
    return Integer.valueOf(positionKeySequence++);
  }
}
