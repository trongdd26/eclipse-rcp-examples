package de.baeckerit.jface.examples.databinding.portfolio.util.compare;

import de.baeckerit.jdk.util.getter.DisplayNameGetter;
import de.baeckerit.jdk.util.getter.Getter;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurity;
import de.baeckerit.jface.util.GetterViewerComparator;

public class ViewableSecurityViewerComparator extends GetterViewerComparator {
	private static final Getter[] GETTERS = new Getter[] { ViewableSecurity.GET_TYPE_NAME, DisplayNameGetter.INSTANCE,
			ViewableSecurity.GET_ISIN, ViewableSecurity.GET_FIRST_TRADING_DAY, ViewableSecurity.GET_LAST_TRADING_DAY };

	public ViewableSecurityViewerComparator() {
		super(GETTERS);
	}
}