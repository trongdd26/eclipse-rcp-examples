package de.baeckerit.jdk.util.isin;

public class IsinException extends RuntimeException {

	private static final long serialVersionUID = 3487449746577748032L;

	public IsinException(String isin) {
		super(isin);
	}

	public String getIsin() {
		return getMessage();
	}
}
