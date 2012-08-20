package de.baeckerit.jdk.util.getter;

public abstract class AbstractStringGetter extends AbstractComparableGetter<String> {
	public abstract String get(Object object);

	public AbstractStringGetter(boolean nullIsAfter) {
		super(nullIsAfter);
	}

	@Override
	public String format(Object object) {
		return get(object);
	}
}
