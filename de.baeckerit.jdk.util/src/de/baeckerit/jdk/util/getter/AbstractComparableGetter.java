package de.baeckerit.jdk.util.getter;

import de.baeckerit.jdk.util.Utils;

public abstract class AbstractComparableGetter<T extends Comparable<T>> implements Getter {

	private final boolean nullIsGreater;

	protected AbstractComparableGetter(boolean nullIsGreater) {
		this.nullIsGreater = nullIsGreater;
	}

	public abstract T get(Object object);

	@Override
	public int compare(Object left, Object right) {
		return Utils.compare(get(left), get(right), nullIsGreater);
	}
}
