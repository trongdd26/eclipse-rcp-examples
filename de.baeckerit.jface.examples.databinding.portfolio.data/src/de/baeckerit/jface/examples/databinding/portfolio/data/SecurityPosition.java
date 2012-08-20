package de.baeckerit.jface.examples.databinding.portfolio.data;

import java.util.Date;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jdk.util.foco.AbstractBooleanPrimitiveFoCo;
import de.baeckerit.jdk.util.foco.AbstractDateFoCo;
import de.baeckerit.jface.examples.databinding.portfolio.access.SecurityPositionParams;

/**
 * A position in a security, either buy (long) or sell (short).
 * 
 * Instances of this class must be immutable!
 */
public class SecurityPosition {

	public static final AbstractBooleanPrimitiveFoCo GET_ACTION = new AbstractBooleanPrimitiveFoCo("Buy", "Sell") {
		public boolean getBoolean(Object object) {
			return ((SecurityPosition) object).isBuy();
		}
	};

	public static final AbstractDateFoCo GET_OPEN_DATE = new AbstractDateFoCo(true) {
		public Date get(Object object) {
			return ((SecurityPosition) object).getOpenDate();
		}
	};

	public static final AbstractDateFoCo GET_CLOSING_DATE = new AbstractDateFoCo(true) {
		public Date get(Object object) {
			return ((SecurityPosition) object).getClosingDate();
		}
	};

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
