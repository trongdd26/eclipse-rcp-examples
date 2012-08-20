package de.baeckerit.jdk.util.getter;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractDateGetter extends AbstractComparableGetter<Date> {

	public static final ThreadLocal<SimpleDateFormat> dateFormats = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("dd.MM.yyyy");
		}
	};

	protected AbstractDateGetter(boolean nullIsAfter) {
		super(nullIsAfter);
	}

	public abstract Date get(Object object);

	@Override
	public String format(Object object) {
		Date date = get(object);
		return date == null ? "" : dateFormats.get().format(date);
	}
}
