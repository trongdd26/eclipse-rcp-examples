package de.baeckerit.jface.examples.databinding.portfolio.viewable;

import de.baeckerit.jdk.util.foco.AbstractDetailFoCo;
import de.baeckerit.jdk.util.foco.IFoCo;

public class SecurityGetter extends AbstractDetailFoCo {
	public SecurityGetter(IFoCo getter) {
		super(getter);
	}

	public Object getDetail(Object object) {
		return ((IProvidesSecurity) object).getSecurity();
	}
}