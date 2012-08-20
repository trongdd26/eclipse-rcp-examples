package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import de.baeckerit.jdk.util.getter.Getter;

public class GetterViewerComparator extends ViewerComparator {

	private final Getter[] getters;

	public GetterViewerComparator(Getter... getters) {
		this.getters = getters.clone();
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		int cmp = 0;
		for (int i = 0; cmp == 0 && i < getters.length; i++) {
			cmp = getters[i].compare(e1, e2);
		}
		return cmp;
	}
}
