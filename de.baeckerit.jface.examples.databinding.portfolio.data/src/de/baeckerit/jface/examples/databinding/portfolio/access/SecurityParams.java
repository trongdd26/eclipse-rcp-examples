package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.util.Date;

public class SecurityParams {
  public Integer primaryKey;
  public String securityTypeKey;
  public String securityDirectionKey;
  public String securityName;
  public String isin;
  public Date firstTradingDay;
  public Date lastTradingDay;

  public SecurityParams() {
  }

  public SecurityParams(String securityType, String securityDirection, String securityName, String isin, Date firstTradingDay,
      Date lastTradingday) {
    this.securityTypeKey = securityType;
    this.securityDirectionKey = securityDirection;
    this.securityName = securityName;
    this.isin = isin;
    this.firstTradingDay = firstTradingDay;
    this.lastTradingDay = lastTradingday;
  }
}
