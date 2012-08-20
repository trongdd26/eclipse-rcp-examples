package de.baeckerit.jdk.util.getter;

public abstract class AbstractBooleanGetter extends AbstractComparableGetter<Boolean> {
	private final String forTrue;
	private final String forFalse;
	private final String forNull;

	public abstract Boolean get(Object object);

	public AbstractBooleanGetter(boolean nullIsAfter, String forTrue, String forFalse, String forNull) {
		super(nullIsAfter);
		this.forTrue = forTrue;
		this.forFalse = forFalse;
		this.forNull = forNull;
	}

	@Override
	public String format(Object object) {
		Boolean value = get(object);
		return value == null ? forNull : value.booleanValue() ? forTrue : forFalse;
	}

	@Override
	public int compare(Object left, Object right) {
		return get(left).compareTo(get(right));
	}
}
