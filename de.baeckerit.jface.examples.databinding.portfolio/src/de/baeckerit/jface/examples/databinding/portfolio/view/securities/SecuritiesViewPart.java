package de.baeckerit.jface.examples.databinding.portfolio.view.securities;

import java.util.List;

import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableSetContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import de.baeckerit.jdk.util.foco.DisplayNameFoCo;
import de.baeckerit.jface.examples.databinding.portfolio.EventHandling;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.util.compare.ViewableSecurityViewerComparator;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurity;
import de.baeckerit.jface.util.JFaceUtils;
import de.baeckerit.swt.util.SWTUtils;

public class SecuritiesViewPart extends ViewPart {
  public static final String ID = "de.baeckerit.jface.examples.databinding.portfolio.view.securities";

  private TableViewer viewer;

  /**
   * This is a callback that will allow us to create the viewer and initialize
   * it.
   */
  public void createPartControl(Composite parent) {
    viewer = new TableViewer(SWTUtils.createTable(parent));
    JFaceUtils.createColumn(viewer, "ISIN", 100, ViewableSecurity.GET_ISIN);
    JFaceUtils.createColumn(viewer, "Name", 300, DisplayNameFoCo.INSTANCE);
    JFaceUtils.createColumn(viewer, "Type", 50, ViewableSecurity.GET_TYPE_NAME);
    JFaceUtils.createColumn(viewer, "Direction", 50, ViewableSecurity.GET_DIRECTION_NAME);
    JFaceUtils.createColumn(viewer, "Trading Since", 100, ViewableSecurity.GET_FIRST_TRADING_DAY);
    JFaceUtils.createColumn(viewer, "Last Traded", 100, ViewableSecurity.GET_LAST_TRADING_DAY);

    viewer.setComparator(new ViewableSecurityViewerComparator());
    viewer.setContentProvider(new ObservableSetContentProvider());

    List<ViewableSecurity> viewableSecurities = ServiceLocator.getDataAccess().getViewableSecurities();
    final IObservableSet securities = new WritableSet(viewableSecurities, ViewableSecurity.class);
    viewer.setInput(securities);

    final IViewerObservableValue singleSelection = ViewersObservables.observeSingleSelection(viewer);

    EventHandling.addNewSecurityEventHandler(new EventHandler() {
      @Override
      public void handleEvent(final Event event) {
        securities.getRealm().exec(new Runnable() {
          public void run() {
            ViewableSecurity newSecurity = EventHandling.extractNewSecurity(event);
            securities.add(newSecurity);
            singleSelection.setValue(newSecurity);
          }
        });
      }
    });
  }

  public void setFocus() {
    viewer.getTable().setFocus();
  }
}