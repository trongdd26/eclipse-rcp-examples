package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import de.baeckerit.jdk.util.getter.Getter;

public class GetterColumnLabelProvider extends ColumnLabelProvider {

	private final Getter getter;

	public GetterColumnLabelProvider(Getter getter) {
		this.getter = getter;
	}

	public String getText(Object element) {
		return getter.format(element);
	}
}