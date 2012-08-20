package de.baeckerit.jface.examples.databinding.portfolio.data;

import de.baeckerit.jdk.util.IProvidesDisplayName;

/**
 * Type of a security.
 * 
 * Instances of this class must be immutable!
 */
public class SecurityType implements IProvidesDisplayName {
	private final String primaryKey;
	private final String displayName;

	public String getPrimaryKey() {
		return primaryKey;
	}

	public String getDisplayName() {
		return displayName;
	}

	public SecurityType(String primaryKey, String displayName) {
		this.primaryKey = primaryKey;
		this.displayName = displayName;
	}
}
