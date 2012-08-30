package de.baeckerit.jface.examples.databinding.portfolio.access;

import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

import de.baeckerit.jface.examples.databinding.portfolio.access.IDataAccess;

public class ServiceFactory extends AbstractServiceFactory {

	public ServiceFactory() {
	}

	@Override
	public Object create(@SuppressWarnings("rawtypes") Class serviceInterface, IServiceLocator parentLocator,
			IServiceLocator locator) {
		if (IDataAccess.class == serviceInterface)
			return TransientStorage.INSTANCE;

		return null;
	}
}
