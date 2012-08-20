package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class SingleColumnTableLabelProvider extends LabelProvider implements ITableLabelProvider {
    public String getColumnText(Object obj, int index) {
        return getText(obj);
    }

    public Image getColumnImage(Object obj, int index) {
        return getImage(obj);
    }
}
