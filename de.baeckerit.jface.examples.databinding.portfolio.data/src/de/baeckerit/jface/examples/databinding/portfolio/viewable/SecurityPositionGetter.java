package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import de.baeckerit.jdk.util.foco.AbstractDetailFoCo;
import de.baeckerit.jdk.util.foco.IFoCo;

public class SecurityPositionGetter extends AbstractDetailFoCo {
	public SecurityPositionGetter(IFoCo getter) {
		super(getter);
	}

	public Object getDetail(Object object) {
		return ((IProvidesSecurityPosition) object).getSecurityPosition();
	}
}