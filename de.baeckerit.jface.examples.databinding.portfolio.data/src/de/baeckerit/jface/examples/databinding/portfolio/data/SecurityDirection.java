package de.baeckerit.jface.examples.databinding.portfolio.data;

import de.baeckerit.jdk.util.IProvidesDisplayName;

/**
 * The direction of speculation, either long or short.
 * 
 * Instances of this class must be immutable!
 */
public class SecurityDirection implements IProvidesDisplayName {
	private final String primaryKey;
	private final String displayName;

	public String getPrimaryKey() {
		return primaryKey;
	}

	public String getDisplayName() {
		return displayName;
	}

	public SecurityDirection(String primaryKey, String displayName) {
		this.primaryKey = primaryKey;
		this.displayName = displayName;
	}
}
