package de.baeckerit.jface.databinding.util.validator;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jdk.util.isin.IsinCheckDigitException;
import de.baeckerit.jdk.util.isin.IsinFormatException;
import de.baeckerit.jdk.util.isin.IsinLengthException;
import de.baeckerit.jdk.util.isin.IsinUtils;

public class IsinValidator implements IValidator {

	@Override
	public IStatus validate(Object value) {
		String input = Utils.getContent((String) value);
		if (input == null) {
			return ValidationStatus.OK_STATUS;
		}
		try {
			IsinUtils.verifyIsin(input, true);
		} catch (IsinLengthException e) {
			return ValidationStatus.error("ISIN must have 12 letters/digits!");
		} catch (IsinFormatException e) {
			return ValidationStatus.error("ISIN must have a 2 letters + 9 digits or letters + 1 check digit!");
		} catch (IsinCheckDigitException e) {
			return ValidationStatus.error("ISIN has an invalid check digit! Hint: The correct check digit would be "
					+ e.getExpectedCheckDigit());
		} catch (RuntimeException e) {
			return ValidationStatus.error("Unknown ISIN validation error", e);
		}
		return ValidationStatus.OK_STATUS;
	}

}
