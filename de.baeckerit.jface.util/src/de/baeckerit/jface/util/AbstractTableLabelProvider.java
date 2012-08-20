package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public abstract class AbstractTableLabelProvider extends LabelProvider implements ITableLabelProvider {
	public Image getColumnImage(Object obj, int index) {
		return null;
	}

	@Override
	public String getText(Object element) {
		return getColumnText(element, 0);
	}

	@Override
	public Image getImage(Object element) {
		return getColumnImage(element, 0);
	}
}