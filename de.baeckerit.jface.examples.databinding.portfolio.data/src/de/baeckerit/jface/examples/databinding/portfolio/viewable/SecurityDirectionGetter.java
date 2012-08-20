package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import de.baeckerit.jdk.util.foco.AbstractDetailFoCo;
import de.baeckerit.jdk.util.foco.IFoCo;

public class SecurityDirectionGetter extends AbstractDetailFoCo {
	public SecurityDirectionGetter(IFoCo getter) {
		super(getter);
	}

	public Object getDetail(Object object) {
		return ((IProvidesSecurityDirection) object).getSecurityDirection();
	}
}