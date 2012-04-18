package de.baeckerit.rcp.ui.util;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

/**
 * Provides utility methods for dealing with adapters.
 * 
 * @author Andreas Baecker
 */
public abstract class Adapters {

  /** Initializes this instance. */
  private Adapters() {
  }

  /**
   * Tries to look up an adapter for a given object and a given class.
   * <p>
   * Note: This method consults the platform's adapter manager even if the adaptable object does not
   * implement {@link IAdaptable}.
   * 
   * @param object the adaptable object.
   * @param clazz the class to adapt to.
   * @param useAdapterManager if true, the platform's adapter manager shall be asked to provide an
   *        adapter
   * @return an adapter, if one exists. Returns null if there is no adapter or if the input object
   *         is null.
   */
  public static <T> T getAdapter(Object object, Class<T> clazz, boolean useAdapterManager) {
    if (object != null) {
      if (clazz.isAssignableFrom(object.getClass()))
        return clazz.cast(object);
      if (object instanceof IAdaptable) {
        Object adapter = ((IAdaptable) object).getAdapter(clazz);
        if (adapter != null)
          return clazz.cast(adapter);
      }
      if (useAdapterManager)
        return clazz.cast(Platform.getAdapterManager().getAdapter(object, clazz));
    }
    return null;
  }
}
