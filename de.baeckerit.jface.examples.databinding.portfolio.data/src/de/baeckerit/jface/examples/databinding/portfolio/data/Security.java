package de.baeckerit.jface.examples.databinding.portfolio.data;

import java.util.Date;

import de.baeckerit.jdk.util.IProvidesDisplayName;
import de.baeckerit.jdk.util.Utils;

/**
 * A security of some type.
 */
public class Security extends EntityWithIntegerKey implements IProvidesDisplayName {
  private SecurityDirection securityDirection;
  private SecurityType securityType;
  private String securityName;
  private String isin;
  private Date firstTradingDay;
  private Date lastTradingDay;

  public Security() {
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Security))
      return false;
    return super.equals(obj);
  }

  public SecurityType getSecurityType() {
    return securityType;
  }

  public SecurityDirection getSecurityDirection() {
    return securityDirection;
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

  public void setSecurityDirection(SecurityDirection securityDirection) {
    this.securityDirection = securityDirection;
  }

  public void setSecurityType(SecurityType securityType) {
    this.securityType = securityType;
  }

  public void setSecurityName(String securityName) {
    this.securityName = securityName;
  }

  public void setIsin(String isin) {
    this.isin = isin;
  }

  public void setFirstTradingDay(Date firstTradingDay) {
    this.firstTradingDay = firstTradingDay;
  }

  public void setLastTradingDay(Date lastTradingDay) {
    this.lastTradingDay = lastTradingDay;
  }

  public String getSecurityTypeName() {
    return securityType.getDisplayName();
  }

  public String getSecurityDirectionName() {
    return securityDirection == null ? null : securityDirection.getDisplayName();
  }
}
