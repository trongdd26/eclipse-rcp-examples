package de.baeckerit.jface.databinding.util;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;

import de.baeckerit.jface.databinding.util.converter.DateToStringConverter;
import de.baeckerit.jface.databinding.util.converter.StringToDateConverter;
import de.baeckerit.jface.databinding.util.converter.ToUpperCaseConverter;
import de.baeckerit.jface.databinding.util.converter.TrimConverter;
import de.baeckerit.jface.databinding.util.validator.DateValidator;
import de.baeckerit.jface.databinding.util.validator.IsinValidator;

public class DefaultDataBindingContext extends DataBindingContext {

	public DefaultDataBindingContext() {
	}

	public DefaultDataBindingContext(Realm validationRealm) {
		super(validationRealm);
	}

	public UpdateValueStrategy createUpdateValueStrategy() {
		return new UpdateValueStrategy();
	}

	public IValidator createDateValidator() {
		return DateValidator.INSTANCE;
	}

	public IConverter createStringToDateConverter() {
		return StringToDateConverter.INSTANCE;
	}

	public IConverter createDateToStringConverter() {
		return DateToStringConverter.INSTANCE;
	}

	public Binding bindDateValue(IObservableValue target, IObservableValue model) {
		return bindValue(target, model, createUpdateValueStrategy().setAfterGetValidator(createDateValidator())
				.setConverter(createStringToDateConverter()),
				createUpdateValueStrategy().setConverter(createDateToStringConverter()));
	}

	public Binding bindDateValue(IObservableValue target, IObservableValue focusTarget, IObservableValue model) {
		Binding binding = bindDateValue(target, model);
		enablePostFormatting(focusTarget, binding);
		return binding;
	}

	public Binding bindDateValue(IControlValues targetValues, IObservableValue model) {
		return bindDateValue(targetValues.getTextObservable(), targetValues.getFocusObservable(), model);
	}

	public Binding bindTextValue(IObservableValue target, IObservableValue model) {
		return bindValue(target, model, createUpdateValueStrategy().setConverter(new TrimConverter()), null);
	}

	public Binding bindTextValue(IObservableValue target, IObservableValue focusTarget, IObservableValue model) {
		Binding binding = bindTextValue(target, model);
		enablePostFormatting(focusTarget, binding);
		return binding;
	}

	public Binding bindTextValue(IControlValues targetValues, IObservableValue model) {
		return bindTextValue(targetValues.getTextObservable(), targetValues.getFocusObservable(), model);
	}

	public Binding bindIsinValue(IControlValues targetValues, IObservableValue model) {
		Binding binding = bindValue(targetValues.getTextObservable(), model,
				createUpdateValueStrategy().setConverter(new TrimConverter(new ToUpperCaseConverter()))
						.setAfterConvertValidator(new IsinValidator()), null);
		enablePostFormatting(targetValues.getFocusObservable(), binding);
		return binding;
	}

	public void enablePostFormatting(final IObservableValue focusObservable, final Binding binding) {
		focusObservable.addValueChangeListener(new IValueChangeListener() {
			public void handleValueChange(final ValueChangeEvent event) {
				final Boolean focusWhenFired = (Boolean) (event.diff.getNewValue());
				getValidationRealm().asyncExec(new Runnable() {
					public void run() {
						if (Boolean.FALSE.equals(focusWhenFired) && !binding.isDisposed()) {
							if (((IStatus) binding.getValidationStatus().getValue()).isOK()) {
								binding.updateModelToTarget();
							}
						}
					}
				});
			}
		});
	}
}
