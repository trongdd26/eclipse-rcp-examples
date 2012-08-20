package de.baeckerit.jface.databinding.util.converter;

import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;

public class TrimConverter extends Converter {

	private final IConverter delegate;

	public TrimConverter() {
		this(null);
	}

	public TrimConverter(IConverter delegate) {
		super(String.class, delegate == null ? String.class : delegate.getToType());
		this.delegate = delegate;
	}

	@Override
	public Object convert(Object fromObject) {
		String trimmed = fromObject == null ? null : ((String) fromObject).trim();
		return delegate == null ? trimmed : delegate.convert(trimmed);
	}
}
