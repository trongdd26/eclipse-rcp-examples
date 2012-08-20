package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.baeckerit.jdk.util.isin.IsinUtils;
import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityPosition;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityType;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurity;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurityPosition;

public class TransientStorage implements IDataAccess {

	public static final TransientStorage INSTANCE = new TransientStorage();

	private final Map<String, SecurityType> types = new HashMap<>();
	private final Map<String, SecurityDirection> directions = new HashMap<>();
	private final Map<String, Security> securities = new HashMap<>();
	private final Map<String, SecurityPosition> positions = new HashMap<>();
	private int securitieKeySequence = 0;
	private int positionKeySequence = 0;

	public TransientStorage() {
		directions.put("L", new SecurityDirection("L", "Long"));
		directions.put("S", new SecurityDirection("S", "Short"));
		types.put("SHA", new SecurityType("SHA", "Share"));
		types.put("ETF", new SecurityType("ETF", "ETF"));
		types.put("OPT", new SecurityType("OPT", "Option"));

		DateFormat df = DateFormat.getDateInstance();
		try {
			String securityName = "HENKEL AG & CO. KGAA Inhaber-Vorzugsaktien O.ST.O.N";
			internalAddSecurity("SHA", null, securityName, "DE0006048432", df.parse("05.02.1962"), null);
			securityName = "Metro AG Inhaber-Stammaktien O.N.";
			internalAddSecurity("SHA", null, securityName, "DE0007257503", df.parse("05.02.1963"), null);
			securityName = "Daimler AG Namensaktien O.N.";
			internalAddSecurity("SHA", null, securityName, "DE0007100000", df.parse("05.02.1964"), null);
			securityName = "db x-trackers DAX ETF";
			internalAddSecurity("ETF", "L", securityName, "LU0274211480", df.parse("05.02.2000"), null);
			securityName = "db x-trackers ShortDAX ETF";
			internalAddSecurity("ETF", "S", securityName, "LU0292106241", df.parse("05.02.2000"), null);
			securityName = "CALL Daimler AG Namensaktien O.N.";
			internalAddSecurity("OPT", "L", securityName, "DE000BN4EY57", df.parse("05.02.2012"), null);
			securityName = "PUT Daimler AG Namensaktien O.N.";
			internalAddSecurity("OPT", "S", securityName, "DE000BN4FG33", df.parse("05.02.2012"),
					df.parse("05.03.2012"));
			securityName = "PUT Daimler AG Namensaktien O.N.";
			internalAddSecurity("OPT", "S", securityName, "DE000BN4FG33", df.parse("05.04.2012"), null);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		for (Security security : securities.values()) {
			IsinUtils.verifyIsin(security.getIsin(), true);
		}

		try {
			for (Security security : securities.values()) {
				SecurityPositionParams p = new SecurityPositionParams();
				p.primaryKey = createPositionKey();
				p.buy = true;
				p.securityKey = security.getPrimaryKey();
				p.openDate = df.parse("01.01.2012");
				p.closingDate = df.parse("01.02.2012");
				positions.put(p.primaryKey, new SecurityPosition(p));

				p.primaryKey = createPositionKey();
				p.openDate = df.parse("01.04.2012");
				p.closingDate = null;
				positions.put(p.primaryKey, new SecurityPosition(p));
			}
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private void internalAddSecurity(String type, String direction, String securityName, String isin,
			Date firstTradingDay, Date lastTradingDate) {
		internalAddSecurity(new SecurityParams(type, direction, securityName, isin, firstTradingDay, lastTradingDate));
	}

	@Override
	public synchronized List<SecurityDirection> getSecurityDirections() {
		return new ArrayList<>(directions.values());
	}

	@Override
	public synchronized List<SecurityType> getSecurityTypes() {
		return new ArrayList<>(types.values());
	}

	@Override
	public synchronized List<Security> getSecurities() {
		return new ArrayList<>(securities.values());
	}

	public synchronized List<SecurityPosition> getPositions() {
		return new ArrayList<>(positions.values());
	}

	@Override
	public synchronized List<ViewableSecurity> getViewableSecurities() {
		List<ViewableSecurity> result = new ArrayList<>(securities.size());
		for (Security security : securities.values()) {
			result.add(toViewableSecurity(security));
		}
		return result;
	}

	@Override
	public List<ViewableSecurityPosition> getViewablePositions() {
		List<ViewableSecurityPosition> result = new ArrayList<>(securities.size());
		for (SecurityPosition position : positions.values()) {
			result.add(toViewableSecurityPosition(position));
		}
		return result;
	}

	@Override
	public ViewableSecurity addSecurity(SecurityParams p) {
		return toViewableSecurity(internalAddSecurity(p));
	}

	@Override
	public ViewableSecurityPosition addSecurityPosition(SecurityPositionParams p) {
		p.primaryKey = createPositionKey();
		SecurityPosition position = new SecurityPosition(p);
		positions.put(p.primaryKey, position);
		return toViewableSecurityPosition(position);
	}

	private ViewableSecurity toViewableSecurity(Security security) {
		SecurityType securityType = types.get(security.getSecurityTypeKey());
		SecurityDirection securityDirection = directions.get(security.getSecurityDirectionKey());
		return new ViewableSecurity(securityType, securityDirection, security);
	}

	private ViewableSecurityPosition toViewableSecurityPosition(SecurityPosition position) {
		Security security = securities.get(position.getSecurityKey());
		SecurityType securityType = types.get(security.getSecurityTypeKey());
		return new ViewableSecurityPosition(security, securityType, position);
	}

	private Security internalAddSecurity(SecurityParams p) {
		p.primaryKey = createSecurityKey();
		Security newSecurity = new Security(p);
		securities.put(p.primaryKey, newSecurity);
		return newSecurity;
	}

	private String createSecurityKey() {
		return String.valueOf(securitieKeySequence++);
	}

	private String createPositionKey() {
		return String.valueOf(positionKeySequence++);
	}
}
