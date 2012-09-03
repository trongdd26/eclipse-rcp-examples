package de.baeckerit.jface.examples.databinding.portfolio.data;

import java.util.Date;

import de.baeckerit.jdk.util.IProvidesDisplayName;

public interface ISecurityPosition extends IEntityWithIntegerKey, IProvidesDisplayName {

  ISecurity getSecurity();

  boolean isBuy();

  Date getOpenDate();

  Date getClosingDate();

  void setSecurity(ISecurity security);

  void setBuy(boolean buy);

  void setOpenDate(Date openDate);

  void setClosingDate(Date closingDate);

  String getIsin();

  String getSecurityTypeName();
}