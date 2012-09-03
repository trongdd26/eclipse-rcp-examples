package de.baeckerit.jface.examples.databinding.portfolio.access;

import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

public class HibernateServiceFactory extends AbstractServiceFactory {

  public HibernateServiceFactory() {
  }

  @Override
  public Object create(@SuppressWarnings("rawtypes") Class serviceInterface, IServiceLocator parentLocator, IServiceLocator locator) {
    if (IDataAccess.class == serviceInterface)
      return HibernateStorage.INSTANCE;

    return null;
  }
}
