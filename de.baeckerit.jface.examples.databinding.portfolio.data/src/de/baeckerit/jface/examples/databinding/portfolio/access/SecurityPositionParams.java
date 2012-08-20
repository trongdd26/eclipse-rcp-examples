package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.util.Date;

public class SecurityPositionParams {

	public String primaryKey;
	public String securityKey;
	public boolean buy;
	public Date openDate;
	public Date closingDate;

	public SecurityPositionParams() {
	}

	public SecurityPositionParams(String primaryKey, boolean buy, String securityKey, Date openDate, Date closingDate) {
		this.primaryKey = primaryKey;
		this.buy = buy;
		this.securityKey = securityKey;
		this.openDate = openDate;
		this.closingDate = closingDate;
	}
}
