package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.util.Date;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurity;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityPosition;

/**
 * A position in a security, either buy (long) or sell (short).
 */
public class SecurityPosition extends EntityWithIntegerKey implements ISecurityPosition {

  private ISecurity security;
  private boolean buy;
  private Date openDate;
  private Date closingDate;

  public SecurityPosition() {
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof ISecurityPosition))
      return false;
    return super.equals(obj);
  }

  @Override
  public String getDisplayName() {
    return security.getDisplayName();
  }

  @Override
  public ISecurity getSecurity() {
    return security;
  }

  @Override
  public boolean isBuy() {
    return buy;
  }

  @Override
  public Date getOpenDate() {
    return Utils.toDate(openDate);
  }

  @Override
  public Date getClosingDate() {
    return Utils.toDate(closingDate);
  }

  @Override
  public void setSecurity(ISecurity security) {
    this.security = security;
  }

  @Override
  public void setBuy(boolean buy) {
    this.buy = buy;
  }

  @Override
  public void setOpenDate(Date openDate) {
    this.openDate = openDate;
  }

  @Override
  public void setClosingDate(Date closingDate) {
    this.closingDate = closingDate;
  }

  @Override
  public String getIsin() {
    return security.getIsin();
  }

  @Override
  public String getSecurityTypeName() {
    return security.getSecurityType().getDisplayName();
  }

  @Override
  public void setPrimaryKey(Integer primaryKey) {
    super.setPrimaryKey(primaryKey);
  }
}
