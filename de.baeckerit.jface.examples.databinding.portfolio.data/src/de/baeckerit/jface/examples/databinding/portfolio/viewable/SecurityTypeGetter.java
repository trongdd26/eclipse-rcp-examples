package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import de.baeckerit.jdk.util.getter.AbstractDetailGetter;
import de.baeckerit.jdk.util.getter.Getter;

public class SecurityTypeGetter extends AbstractDetailGetter {
	public SecurityTypeGetter(Getter getter) {
		super(getter);
	}

	public Object getDetail(Object object) {
		return ((IProvidesSecurityType) object).getSecurityType();
	}
}