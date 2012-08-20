package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import de.baeckerit.jdk.util.getter.AbstractDetailGetter;
import de.baeckerit.jdk.util.getter.Getter;

public class SecurityDirectionGetter extends AbstractDetailGetter {
	public SecurityDirectionGetter(Getter getter) {
		super(getter);
	}

	public Object getDetail(Object object) {
		return ((IProvidesSecurityDirection) object).getSecurityDirection();
	}
}