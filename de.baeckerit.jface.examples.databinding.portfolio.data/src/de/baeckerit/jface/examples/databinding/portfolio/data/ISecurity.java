package de.baeckerit.jface.examples.databinding.portfolio.data;

import java.util.Date;

import de.baeckerit.jdk.util.IProvidesDisplayName;

public interface ISecurity extends IEntityWithIntegerKey, IProvidesDisplayName {

  ISecurityType getSecurityType();

  ISecurityDirection getSecurityDirection();

  String getSecurityName();

  String getIsin();

  Date getFirstTradingDay();

  Date getLastTradingDay();

  void setSecurityDirection(ISecurityDirection securityDirection);

  void setSecurityType(ISecurityType securityType);

  void setSecurityName(String securityName);

  void setIsin(String isin);

  void setFirstTradingDay(Date firstTradingDay);

  void setLastTradingDay(Date lastTradingDay);

  String getSecurityTypeName();

  String getSecurityDirectionName();

}