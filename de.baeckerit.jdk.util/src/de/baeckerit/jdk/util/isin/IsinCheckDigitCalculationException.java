package de.baeckerit.jdk.util.isin;

public class IsinCheckDigitCalculationException extends IsinException {

	private static final long serialVersionUID = 4546824094716226374L;

	public IsinCheckDigitCalculationException(String isin) {
		super(isin);
	}
}
