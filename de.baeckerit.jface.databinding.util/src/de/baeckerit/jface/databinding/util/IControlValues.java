package de.baeckerit.jface.databinding.util;

import org.eclipse.jface.databinding.swt.ISWTObservableValue;

public interface IControlValues {

  ISWTObservableValue getTextObservable();

  ISWTObservableValue getFocusObservable();

  ISWTObservableValue getEnabledObservable();

  ISWTObservableValue getImageObservable();
}