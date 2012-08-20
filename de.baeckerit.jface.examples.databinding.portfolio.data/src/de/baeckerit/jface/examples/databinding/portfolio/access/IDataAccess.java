package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.util.List;

import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityPosition;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityType;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurity;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurityPosition;

public interface IDataAccess {
	List<SecurityType> getSecurityTypes();

	List<SecurityDirection> getSecurityDirections();

	List<Security> getSecurities();

	List<ViewableSecurity> getViewableSecurities();

	List<SecurityPosition> getPositions();

	List<ViewableSecurityPosition> getViewablePositions();

	ViewableSecurity addSecurity(SecurityParams p);

	ViewableSecurityPosition addSecurityPosition(SecurityPositionParams p);
}
