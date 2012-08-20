package de.baeckerit.jdk.util.getter;

import de.baeckerit.jdk.util.IProvidesDisplayName;

public class DisplayNameGetter extends AbstractStringGetter {

	public static final DisplayNameGetter INSTANCE = new DisplayNameGetter();

	protected DisplayNameGetter() {
		super(true);
	}

	@Override
	public String get(Object object) {
		return ((IProvidesDisplayName) object).getDisplayName();
	}
}
