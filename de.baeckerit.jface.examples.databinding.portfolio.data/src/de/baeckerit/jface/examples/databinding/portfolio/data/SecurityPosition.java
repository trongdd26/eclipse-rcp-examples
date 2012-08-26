package de.baeckerit.jface.examples.databinding.portfolio.data;

import java.util.Date;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.access.SecurityPositionParams;

/**
 * A position in a security, either buy (long) or sell (short).
 * 
 * Instances of this class must be immutable!
 */
public class SecurityPosition {

  private final String primaryKey;
  private final String securityKey;
  private final boolean buy;
  private final Date openDate;
  private final Date closingDate;

  public SecurityPosition(SecurityPositionParams p) {
    primaryKey = p.primaryKey;
    securityKey = p.securityKey;
    buy = p.buy;
    openDate = Utils.toDate(p.openDate);
    closingDate = Utils.toDate(p.closingDate);
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getSecurityKey() {
    return securityKey;
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
}