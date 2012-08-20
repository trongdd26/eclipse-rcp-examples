package de.baeckerit.jface.databinding.util.converter;

import java.util.Date;

public class DateToStringConverter extends AbstractDateConverter {

	public static final DateToStringConverter INSTANCE = new DateToStringConverter();

	protected DateToStringConverter() {
		super(Date.class, String.class);
	}

	public Object convert(Object fromObject) {
		return format((Date) fromObject);
	}

	public String format(Date date) {
		return date == null ? "" : dateFormats.get().format(date);
	}
}
