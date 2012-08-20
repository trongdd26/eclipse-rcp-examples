package de.baeckerit.jdk.util.getter;

public abstract class AbstractBooleanPrimitiveGetter extends AbstractBooleanGetter {

	public abstract boolean getBoolean(Object object);

	public Boolean get(Object object) {
		return Boolean.valueOf(getBoolean(object));
	}

	public AbstractBooleanPrimitiveGetter(String forTrue, String forFalse) {
		super(true, forTrue, forFalse, null);
	}
}
