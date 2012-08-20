package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import de.baeckerit.jdk.util.getter.AbstractDetailGetter;
import de.baeckerit.jdk.util.getter.Getter;

public class SecurityGetter extends AbstractDetailGetter {
	public SecurityGetter(Getter getter) {
		super(getter);
	}

	public Object getDetail(Object object) {
		return ((IProvidesSecurity) object).getSecurity();
	}
}