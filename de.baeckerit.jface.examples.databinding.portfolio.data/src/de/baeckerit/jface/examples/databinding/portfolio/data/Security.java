package de.baeckerit.jface.examples.databinding.portfolio.data;

import java.util.Date;

import de.baeckerit.jdk.util.IProvidesDisplayName;
import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.access.SecurityParams;

/**
 * A security of some type.
 * 
 * Instances of this class must be immutable!
 */
public class Security implements IProvidesDisplayName {
  private final Integer primaryKey;
  private final String securityDirectionKey;
  private final String securityTypeKey;
  private final String securityName;
  private final String isin;
  private final Date firstTradingDay;
  private final Date lastTradingDay;

  public Integer getPrimaryKey() {
    return primaryKey;
  }

  public String getSecurityDirectionKey() {
    return securityDirectionKey;
  }

  public String getSecurityTypeKey() {
    return securityTypeKey;
  }

  @Override
  public String getDisplayName() {
    return securityName;
  }

  public String getSecurityName() {
    return securityName;
  }

  public String getIsin() {
    return isin;
  }

  public Date getFirstTradingDay() {
    return Utils.toDate(firstTradingDay);
  }

  public Date getLastTradingDay() {
    return Utils.toDate(lastTradingDay);
  }

  public Security(SecurityParams p) {
    this.primaryKey = p.primaryKey;
    this.securityDirectionKey = p.securityDirectionKey;
    this.securityTypeKey = p.securityTypeKey;
    this.securityName = p.securityName;
    this.isin = p.isin;
    this.firstTradingDay = Utils.toDate(p.firstTradingDay);
    this.lastTradingDay = Utils.toDate(p.lastTradingDay);
  }

  // This ctor keeps Hibernate happy :-)
  protected Security() {
    this.primaryKey = null;
    this.securityDirectionKey = null;
    this.securityTypeKey = null;
    this.securityName = null;
    this.isin = null;
    this.firstTradingDay = null;
    this.lastTradingDay = null;
  }
}
