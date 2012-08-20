package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import de.baeckerit.jdk.util.getter.AbstractDetailGetter;
import de.baeckerit.jdk.util.getter.Getter;

public class SecurityPositionGetter extends AbstractDetailGetter {
	public SecurityPositionGetter(Getter getter) {
		super(getter);
	}

	public Object getDetail(Object object) {
		return ((IProvidesSecurityPosition) object).getSecurityPosition();
	}
}