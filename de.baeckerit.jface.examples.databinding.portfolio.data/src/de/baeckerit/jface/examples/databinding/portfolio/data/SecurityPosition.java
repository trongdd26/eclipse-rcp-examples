package de.baeckerit.jface.examples.databinding.portfolio.data;

import java.util.Date;

import de.baeckerit.jdk.util.IProvidesDisplayName;
import de.baeckerit.jdk.util.Utils;

/**
 * A position in a security, either buy (long) or sell (short).
 */
public class SecurityPosition extends EntityWithIntegerKey implements IProvidesDisplayName {

  private Security security;
  private boolean buy;
  private Date openDate;
  private Date closingDate;

  public SecurityPosition() {
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof SecurityPosition))
      return false;
    return super.equals(obj);
  }

  @Override
  public String getDisplayName() {
    return security.getDisplayName();
  }

  public Security getSecurity() {
    return security;
  }

  public boolean isBuy() {
    return buy;
  }

  public Date getOpenDate() {
    return Utils.toDate(openDate);
  }

  public Date getClosingDate() {
    return Utils.toDate(closingDate);
  }

  public void setSecurity(Security security) {
    this.security = security;
  }

  public void setBuy(boolean buy) {
    this.buy = buy;
  }

  public void setOpenDate(Date openDate) {
    this.openDate = openDate;
  }

  public void setClosingDate(Date closingDate) {
    this.closingDate = closingDate;
  }

  public String getIsin() {
    return security.getIsin();
  }

  public String getSecurityTypeName() {
    return security.getSecurityType().getDisplayName();
  }
}
