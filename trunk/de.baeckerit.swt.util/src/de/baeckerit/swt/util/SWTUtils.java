package de.baeckerit.swt.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * SWTU = SWT Utilities
 */
public class SWTUtils {

	public static Table createTable(Composite parent) {
		Table table = new Table(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		return table;
	}

	public static void enableSelectAllOnFocusIn(Text... textControls) {
		for (int i = 0; i < textControls.length; i++) {
			textControls[i].addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					((Text) e.widget).selectAll();
				}
			});
		}
	}
}
