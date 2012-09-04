package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.util.List;

import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurity;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityPosition;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityType;

public interface IDataAccess {
  List<ISecurityType> getSecurityTypes();

  List<ISecurityDirection> getSecurityDirections();

  List<ISecurity> getSecurities();

  List<ISecurityPosition> getOpenPositions();

  List<ISecurityPosition> getClosedPositions();

  ISecurity addSecurity(ISecurity security);

  boolean addSecurityPosition(ISecurityPosition position);

  ISecurity createSecurity();

  ISecurityPosition createSecurityPosition();
}
