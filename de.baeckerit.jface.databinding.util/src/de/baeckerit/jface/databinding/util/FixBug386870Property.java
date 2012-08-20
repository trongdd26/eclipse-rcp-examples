package de.baeckerit.jface.databinding.util;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.jface.internal.databinding.swt.ControlFocusedProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;

@SuppressWarnings("restriction")
public class FixBug386870Property extends ControlFocusedProperty {

	boolean loosingFocus = false;

	public FixBug386870Property() {
	}

	@Override
	public boolean doGetBooleanValue(Object source) {
		return loosingFocus ? false : super.doGetBooleanValue(source);
	}

	public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
		int[] events = { SWT.FocusIn, SWT.FocusOut };
		return new ControlFocusListener(this, listener, events, null);
	}

	private class ControlFocusListener extends WidgetListener {
		/**
		 * @param property
		 * @param listener
		 * @param changeEvents
		 * @param staleEvents
		 */
		private ControlFocusListener(IProperty property, ISimplePropertyListener listener, int[] changeEvents,
				int[] staleEvents) {
			super(property, listener, changeEvents, staleEvents);
		}

		public void handleEvent(Event event) {
			switch (event.type) {
			case SWT.FocusIn:
				fireChange(event.widget, Diffs.createValueDiff(Boolean.FALSE, Boolean.TRUE));
				break;
			case SWT.FocusOut:
				loosingFocus = true;
				fireChange(event.widget, Diffs.createValueDiff(Boolean.TRUE, Boolean.FALSE));
				loosingFocus = false;
				break;
			}
		}
	}

}
