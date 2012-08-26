package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import java.util.Date;

import de.baeckerit.jdk.util.IProvidesDisplayName;
import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityType;

/**
 * A security with its foreign keys resolved.
 * 
 * Instances of this class must be immutable!
 */
public class ViewableSecurity implements IProvidesDisplayName {

  private final SecurityType securityType;
  private final SecurityDirection securityDirection;
  private final Security security;

  public SecurityType getSecurityType() {
    return securityType;
  }

  public SecurityDirection getSecurityDirection() {
    return securityDirection;
  }

  public Security getSecurity() {
    return security;
  }

  @Override
  public String getDisplayName() {
    return security.getDisplayName();
  }

  public ViewableSecurity(SecurityType securityType, SecurityDirection securityDirection, Security security) {
    this.securityType = securityType;
    this.securityDirection = securityDirection;
    this.security = security;
  }

  public String getSecurityDirectionName() {
    return securityDirection == null ? null : securityDirection.getDisplayName();
  }

  public String getSecurityTypeName() {
    return securityType.getDisplayName();
  }

  public String getSecurityName() {
    return security.getSecurityName();
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
}
