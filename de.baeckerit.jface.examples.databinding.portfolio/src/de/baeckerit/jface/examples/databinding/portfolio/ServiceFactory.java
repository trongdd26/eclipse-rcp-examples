package de.baeckerit.jface.examples.databinding.portfolio;

import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

import de.baeckerit.jface.examples.databinding.portfolio.access.IDataAccess;
import de.baeckerit.jface.examples.databinding.portfolio.access.TransientStorage;

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
