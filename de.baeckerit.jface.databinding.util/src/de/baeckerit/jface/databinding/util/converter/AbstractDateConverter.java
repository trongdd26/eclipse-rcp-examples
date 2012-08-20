package de.baeckerit.jface.databinding.util.converter;

import java.text.SimpleDateFormat;

import org.eclipse.core.databinding.conversion.Converter;

public abstract class AbstractDateConverter extends Converter {

	protected static final ThreadLocal<SimpleDateFormat> dateFormats = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("dd.MM.yyyy");
		}
	};

	protected AbstractDateConverter(Object fromType, Object toType) {
		super(fromType, toType);
	}
}