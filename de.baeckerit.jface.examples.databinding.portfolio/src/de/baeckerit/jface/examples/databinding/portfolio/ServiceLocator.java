package de.baeckerit.jface.examples.databinding.portfolio;

import org.eclipse.ui.PlatformUI;

import de.baeckerit.jface.examples.databinding.portfolio.access.IDataAccess;

public class ServiceLocator {

	public static IDataAccess getDataAccess() {
		return getService(IDataAccess.class);
	}

	private static <T> T getService(Class<T> clazz) {
		try {
			return clazz.cast(PlatformUI.getWorkbench().getService(clazz));
		} catch (Throwable t) {
			return null;
		}
	}
}
