package de.baeckerit.jface.examples.databinding.portfolio.util.compare;

import de.baeckerit.jdk.util.foco.DisplayNameFoCo;
import de.baeckerit.jdk.util.foco.IFoCo;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurity;
import de.baeckerit.jface.util.FoCoViewerComparator;

public class ViewableSecurityViewerComparator extends FoCoViewerComparator {
	private static final IFoCo[] GETTERS = new IFoCo[] { ViewableSecurity.GET_TYPE_NAME, DisplayNameFoCo.INSTANCE,
			ViewableSecurity.GET_ISIN, ViewableSecurity.GET_FIRST_TRADING_DAY, ViewableSecurity.GET_LAST_TRADING_DAY };

	public ViewableSecurityViewerComparator() {
		super(GETTERS);
	}
}