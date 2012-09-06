package de.baeckerit.jface.examples.databinding.portfolio.data.mybatis;

import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

import de.baeckerit.jface.examples.databinding.portfolio.access.IDataAccess;

public class MyBatisServiceFactory extends AbstractServiceFactory {

  public MyBatisServiceFactory() {
  }

  @Override
  public Object create(@SuppressWarnings("rawtypes") Class serviceInterface, IServiceLocator parentLocator, IServiceLocator locator) {
    if (IDataAccess.class == serviceInterface)
      return MyBatisStorage.INSTANCE;

    return null;
  }
}
