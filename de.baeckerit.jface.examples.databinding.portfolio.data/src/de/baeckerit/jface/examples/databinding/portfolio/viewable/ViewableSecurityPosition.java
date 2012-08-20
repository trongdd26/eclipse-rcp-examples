package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import java.util.Date;

import de.baeckerit.jdk.util.IProvidesDisplayName;
import de.baeckerit.jdk.util.foco.DisplayNameFoCo;
import de.baeckerit.jdk.util.foco.IFoCo;
import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityPosition;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityType;

/**
 * A security position with its foreign keys resolved.
 * 
 * Instances of this class must be immutable!
 */
public class ViewableSecurityPosition implements IProvidesDisplayName, IProvidesSecurity, IProvidesSecurityType,
		IProvidesSecurityPosition {

	public static final IFoCo GET_ISIN = new SecurityGetter(Security.GET_ISIN);
	public static final IFoCo GET_FIRST_TRADING_DAY = new SecurityGetter(Security.GET_FIRST_TRADING_DAY);
	public static final IFoCo GET_LAST_TRADING_DAY = new SecurityGetter(Security.GET_LAST_TRADING_DAY);
	public static final IFoCo GET_TYPE_NAME = new SecurityTypeGetter(DisplayNameFoCo.INSTANCE);
	public static final IFoCo GET_ACTION = new SecurityPositionGetter(SecurityPosition.GET_ACTION);
	public static final IFoCo GET_OPEN_DATE = new SecurityPositionGetter(SecurityPosition.GET_OPEN_DATE);
	public static final IFoCo GET_CLOSING_DATE = new SecurityPositionGetter(SecurityPosition.GET_CLOSING_DATE);

	private final Security security;
	private final SecurityType securityType;
	private final SecurityPosition position;

	public Security getSecurity() {
		return security;
	}

	public SecurityType getSecurityType() {
		return securityType;
	}

	public SecurityPosition getSecurityPosition() {
		return position;
	}

	@Override
	public String getDisplayName() {
		return security.getDisplayName();
	}

	public ViewableSecurityPosition(Security security, SecurityType securityType, SecurityPosition position) {
		this.security = security;
		this.securityType = securityType;
		this.position = position;
	}

	public String getSecurityName() {
		return security.getSecurityName();
	}

	public String getSecurityTypeName() {
		return securityType.getDisplayName();
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

	public Date getOpenDate() {
		return position.getOpenDate();
	}

	public Date getClosingDate() {
		return position.getClosingDate();
	}

	public boolean isBuy() {
		return position.isBuy();
	}
}
