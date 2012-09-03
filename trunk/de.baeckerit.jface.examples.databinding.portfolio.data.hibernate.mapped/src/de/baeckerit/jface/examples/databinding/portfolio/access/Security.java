package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.util.Date;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurity;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityType;

/**
 * A security of some type.
 */
public class Security extends EntityWithIntegerKey implements ISecurity {
  private ISecurityDirection securityDirection;
  private ISecurityType securityType;
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
    if (!(obj instanceof ISecurity))
      return false;
    return super.equals(obj);
  }

  @Override
  public ISecurityType getSecurityType() {
    return securityType;
  }

  @Override
  public ISecurityDirection getSecurityDirection() {
    return securityDirection;
  }

  @Override
  public String getDisplayName() {
    return securityName;
  }

  @Override
  public String getSecurityName() {
    return securityName;
  }

  @Override
  public String getIsin() {
    return isin;
  }

  @Override
  public Date getFirstTradingDay() {
    return Utils.toDate(firstTradingDay);
  }

  @Override
  public Date getLastTradingDay() {
    return Utils.toDate(lastTradingDay);
  }

  @Override
  public void setSecurityDirection(ISecurityDirection securityDirection) {
    this.securityDirection = securityDirection;
  }

  @Override
  public void setSecurityType(ISecurityType securityType) {
    this.securityType = securityType;
  }

  @Override
  public void setSecurityName(String securityName) {
    this.securityName = securityName;
  }

  @Override
  public void setIsin(String isin) {
    this.isin = isin;
  }

  @Override
  public void setFirstTradingDay(Date firstTradingDay) {
    this.firstTradingDay = firstTradingDay;
  }

  @Override
  public void setLastTradingDay(Date lastTradingDay) {
    this.lastTradingDay = lastTradingDay;
  }

  @Override
  public String getSecurityTypeName() {
    return securityType.getDisplayName();
  }

  @Override
  public String getSecurityDirectionName() {
    return securityDirection == null ? null : securityDirection.getDisplayName();
  }
}
