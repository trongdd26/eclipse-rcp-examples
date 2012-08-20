package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import de.baeckerit.jdk.util.foco.AbstractDetailFoCo;
import de.baeckerit.jdk.util.foco.IFoCo;

public class SecurityTypeGetter extends AbstractDetailFoCo {
	public SecurityTypeGetter(IFoCo getter) {
		super(getter);
	}

	public Object getDetail(Object object) {
		return ((IProvidesSecurityType) object).getSecurityType();
	}
}