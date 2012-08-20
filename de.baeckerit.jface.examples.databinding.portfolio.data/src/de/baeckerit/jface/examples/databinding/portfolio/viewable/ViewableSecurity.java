package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import java.util.Date;

import de.baeckerit.jdk.util.IProvidesDisplayName;
import de.baeckerit.jdk.util.getter.DisplayNameGetter;
import de.baeckerit.jdk.util.getter.Getter;
import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityType;

/**
 * A security with its foreign keys resolved.
 * 
 * Instances of this class must be immutable!
 */
public class ViewableSecurity implements IProvidesSecurity, IProvidesSecurityType, IProvidesSecurityDirection,
		IProvidesDisplayName {

	public static final Getter GET_ISIN = new SecurityGetter(Security.GET_ISIN);
	public static final Getter GET_TYPE_NAME = new SecurityTypeGetter(DisplayNameGetter.INSTANCE);
	public static final Getter GET_DIRECTION_NAME = new SecurityDirectionGetter(DisplayNameGetter.INSTANCE);
	public static final Getter GET_FIRST_TRADING_DAY = new SecurityGetter(Security.GET_FIRST_TRADING_DAY);
	public static final Getter GET_LAST_TRADING_DAY = new SecurityGetter(Security.GET_LAST_TRADING_DAY);

	private final SecurityType securityType;
	private final SecurityDirection securityDirection;
	private final Security security;

	@Override
	public SecurityType getSecurityType() {
		return securityType;
	}

	@Override
	public SecurityDirection getSecurityDirection() {
		return securityDirection;
	}

	@Override
	public Security getSecurity() {
		return security;
	}

	@Override
	public String getDisplayName() {
		return security.getDisplayName();
	}

	public ViewableSecurity(SecurityType securityType, SecurityDirection securityDirection, Security security) {
		this.securityType = securityType;
		this.securityDirection = securityDirection;
		this.security = security;
	}

	public String getSecurityDirectionName() {
		return securityDirection == null ? null : securityDirection.getDisplayName();
	}

	public String getSecurityTypeName() {
		return securityType.getDisplayName();
	}

	public String getSecurityName() {
		return security.getSecurityName();
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
}
