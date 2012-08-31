package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.util.List;

import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityPosition;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityType;

public interface IDataAccess {
  List<SecurityType> getSecurityTypes();

  List<SecurityDirection> getSecurityDirections();

  List<Security> getSecurities();

  List<SecurityPosition> getOpenPositions();

  List<SecurityPosition> getClosedPositions();

  boolean addSecurity(Security security);

  boolean addSecurityPosition(SecurityPosition position);
}
