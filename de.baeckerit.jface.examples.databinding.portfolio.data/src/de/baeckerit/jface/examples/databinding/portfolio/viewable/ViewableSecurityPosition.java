package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import java.util.Date;

import de.baeckerit.jdk.util.IProvidesDisplayName;
import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityPosition;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityType;

/**
 * A security position with its foreign keys resolved.
 * 
 * Instances of this class must be immutable!
 */
public class ViewableSecurityPosition implements IProvidesDisplayName {

  private final Security security;
  private final SecurityType securityType;
  private final SecurityPosition position;

  public Security getSecurity() {
    return security;
  }

  public SecurityType getSecurityType() {
    return securityType;
  }

  public SecurityPosition getSecurityPosition() {
    return position;
  }

  @Override
  public String getDisplayName() {
    return security.getDisplayName();
  }

  public ViewableSecurityPosition(Security security, SecurityType securityType, SecurityPosition position) {
    this.security = security;
    this.securityType = securityType;
    this.position = position;
  }

  public String getSecurityName() {
    return security.getSecurityName();
  }

  public String getSecurityTypeName() {
    return securityType.getDisplayName();
  }

  public String getIsin() {
    return security.getIsin();
  }

  public Date getFirstTradingDay() {
    return security.getFirstTradingDay();
  }

  public Date getLastTradingDay() {
    return security.getLastTradingDay();
  }

  public Date getOpenDate() {
    return position.getOpenDate();
  }

  public Date getClosingDate() {
    return position.getClosingDate();
  }

  public boolean isBuy() {
    return position.isBuy();
  }
}
