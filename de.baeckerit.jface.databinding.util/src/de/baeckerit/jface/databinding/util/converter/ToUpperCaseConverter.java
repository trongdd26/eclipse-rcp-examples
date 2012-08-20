package de.baeckerit.jface.databinding.util.converter;

import java.util.Locale;

import org.eclipse.core.databinding.conversion.Converter;

public class ToUpperCaseConverter extends Converter {
	public ToUpperCaseConverter() {
		super(String.class, String.class);
	}

	@Override
	public Object convert(Object fromObject) {
		return fromObject == null ? null : ((String) fromObject).toUpperCase(Locale.ENGLISH);
	}
}