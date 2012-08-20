package de.baeckerit.jdk.util.isin;

public class IsinUtils {

	private static final int ISIN_LENGTH = 12;

	public static void verifyIsin(String isin, boolean verifyCheckDigit) throws IsinLengthException,
			IsinFormatException, IsinCheckDigitException {
		int length = isin.length();
		if (verifyCheckDigit) {
			if (length != ISIN_LENGTH) {
				throw new IsinLengthException(isin);
			}
		} else {
			if (length != ISIN_LENGTH - 1 && length != ISIN_LENGTH) {
				throw new IsinLengthException(isin);
			}
		}
		if (!Character.isUpperCase(isin.charAt(0)) || !Character.isUpperCase(isin.charAt(1))) {
			throw new IsinFormatException(isin);
		}
		for (int i = 2; i < ISIN_LENGTH - 1; i++) {
			if (!Character.isUpperCase(isin.charAt(i)) && !Character.isDigit(isin.charAt(i))) {
				throw new IsinFormatException(isin);
			}
		}
		if (verifyCheckDigit && !Character.isDigit(isin.charAt(ISIN_LENGTH - 1))) {
			throw new IsinFormatException(isin);
		}
		if (verifyCheckDigit) {
			char expectedCheckDigit = calculateCheckDigit(isin.substring(0, ISIN_LENGTH - 1));
			if (expectedCheckDigit != isin.charAt(ISIN_LENGTH - 1)) {
				throw new IsinCheckDigitException(isin, expectedCheckDigit);
			}
		}
	}

	public static char calculateCheckDigit(String isin) throws IsinCheckDigitCalculationException {
		StringBuilder step1 = new StringBuilder();
		for (int i = 0; i < ISIN_LENGTH - 1; i++) {
			char ch = isin.charAt(i);
			if (Character.isDigit(ch)) {
				step1.append(ch);
			} else if (Character.isUpperCase(ch)) {
				step1.append((int) ch - (int) 'A' + 10);
			} else {
				throw new IsinCheckDigitCalculationException(isin);
			}
		}
		StringBuilder step2 = new StringBuilder();
		for (int i = 0; i < step1.length(); i++) {
			char ch = step1.charAt(i);
			int product = (int) ch - (int) '0';
			if (i % 2 == 0) {
				product *= 2;
			}
			step2.append(product);
		}
		int sumOfDigits = 0;
		for (int i = 0; i < step2.length(); i++) {
			sumOfDigits += (int) step2.charAt(i) - (int) '0';
		}
		return (char) ((int) '0' + (10 - (sumOfDigits % 10)) % 10);
	}
}
