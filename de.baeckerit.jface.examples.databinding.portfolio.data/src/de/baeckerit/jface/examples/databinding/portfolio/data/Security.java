package de.baeckerit.jface.examples.databinding.portfolio.data;

import java.util.Date;

import de.baeckerit.jdk.util.IProvidesDisplayName;
import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jdk.util.foco.AbstractDateFoCo;
import de.baeckerit.jdk.util.foco.AbstractStringFoCo;
import de.baeckerit.jface.examples.databinding.portfolio.access.SecurityParams;

/**
 * A security of some type.
 * 
 * Instances of this class must be immutable!
 */
public class Security implements IProvidesDisplayName {

	public static final AbstractStringFoCo GET_ISIN = new AbstractStringFoCo(true) {
		public String get(Object object) {
			return ((Security) object).getIsin();
		}
	};

	public static final AbstractDateFoCo GET_FIRST_TRADING_DAY = new AbstractDateFoCo(true) {
		public Date get(Object object) {
			return ((Security) object).getFirstTradingDay();
		}
	};

	public static final AbstractDateFoCo GET_LAST_TRADING_DAY = new AbstractDateFoCo(true) {
		public Date get(Object object) {
			return ((Security) object).getLastTradingDay();
		}
	};

	private final String primaryKey;
	private final String securityDirectionKey;
	private final String securityTypeKey;
	private final String securityName;
	private final String isin;
	private final Date firstTradingDay;
	private final Date lastTradingDay;

	public String getPrimaryKey() {
		return primaryKey;
	}

	public String getSecurityDirectionKey() {
		return securityDirectionKey;
	}

	public String getSecurityTypeKey() {
		return securityTypeKey;
	}

	@Override
	public String getDisplayName() {
		return securityName;
	}

	public String getSecurityName() {
		return securityName;
	}

	public String getIsin() {
		return isin;
	}

	public Date getFirstTradingDay() {
		return Utils.toDate(firstTradingDay);
	}

	public Date getLastTradingDay() {
		return Utils.toDate(lastTradingDay);
	}

	public Security(SecurityParams p) {
		this.primaryKey = p.primaryKey;
		this.securityDirectionKey = p.securityDirectionKey;
		this.securityTypeKey = p.securityTypeKey;
		this.securityName = p.securityName;
		this.isin = p.isin;
		this.firstTradingDay = Utils.toDate(p.firstTradingDay);
		this.lastTradingDay = Utils.toDate(p.lastTradingDay);
	}
}
