package de.baeckerit.jdk.util.isin;

public class IsinCheckDigitException extends IsinException {

	private static final long serialVersionUID = 4546824094716226374L;

	private final char expectedCheckDigit;

	public IsinCheckDigitException(String isin, char expectedCheckDigit) {
		super(isin);
		this.expectedCheckDigit = expectedCheckDigit;
	}

	public char getExpectedCheckDigit() {
		return expectedCheckDigit;
	}
}
