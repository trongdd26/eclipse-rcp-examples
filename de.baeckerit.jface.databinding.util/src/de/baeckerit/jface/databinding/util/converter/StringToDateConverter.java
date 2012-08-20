package de.baeckerit.jface.databinding.util.converter;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.baeckerit.jdk.util.Utils;

public class StringToDateConverter extends AbstractDateConverter {

	public static final StringToDateConverter INSTANCE = new StringToDateConverter();

	protected StringToDateConverter() {
		super(String.class, Date.class);
	}

	@Override
	public Object convert(Object fromObject) {
		String input = Utils.getContent((String) fromObject);
		if (input == null) {
			return null;
		}
		if (Utils.onlyDigits(input)) {
			if (input.length() == 2) {
				input = "0" + input.substring(0, 1) + ".0" + input.substring(1, 2);
			} else if (input.length() == 3) {
				input = "0" + input.substring(0, 1) + "." + input.substring(1, 3);
			} else if (input.length() == 4) {
				input = input.substring(0, 2) + "." + input.substring(2, 4);
			} else if (input.length() == 5) {
				input = "0" + input.substring(0, 1) + "." + input.substring(1, 3) + ".20" + input.substring(3, 5);
			} else if (input.length() == 6) {
				input = input.substring(0, 2) + "." + input.substring(2, 4) + ".20" + input.substring(4, 6);
			} else if (input.length() == 7) {
				input = "0" + input.substring(0, 1) + "." + input.substring(1, 3) + "." + input.substring(3, 7);
			} else if (input.length() == 8) {
				input = input.substring(0, 2) + "." + input.substring(2, 4) + "." + input.substring(4, 8);
			}
			if (input.length() == 5) {
				input += "." + Utils.getCurrentYearString();
			}
		}

		SimpleDateFormat format = dateFormats.get();
		ParsePosition pos = new ParsePosition(0);
		Date date = format.parse(input, pos);
		if (pos.getErrorIndex() != -1 || pos.getIndex() != input.length()) {
			return null;
		}
		return date;
	}
}
