package de.baeckerit.jface.databinding.util.validator;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.databinding.util.converter.StringToDateConverter;

public class DateValidator implements IValidator {

	public static final DateValidator INSTANCE = new DateValidator();

	private final StringToDateConverter dateConverter;

	protected DateValidator() {
		this(StringToDateConverter.INSTANCE);
	}

	public DateValidator(StringToDateConverter dateConverter) {
		this.dateConverter = dateConverter;
	}

	@Override
	public IStatus validate(Object value) {
		String input = Utils.getContent((String) value);
		if (input == null || dateConverter.convert(input) != null) {
			return ValidationStatus.OK_STATUS;
		}
		return ValidationStatus.error("Not a valid date: " + input);
	}
}
