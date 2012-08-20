package de.baeckerit.jface.examples.databinding.portfolio.view.positions;

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

import de.baeckerit.jdk.util.getter.DisplayNameGetter;
import de.baeckerit.jface.examples.databinding.portfolio.EventHandling;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurityPosition;
import de.baeckerit.jface.util.GetterViewerComparator;
import de.baeckerit.jface.util.JFaceUtils;
import de.baeckerit.swt.util.SWTUtils;

public class PositionsViewPart extends ViewPart {
  public static final String ID = "de.baeckerit.jface.examples.databinding.portfolio.view.positions";

  private TableViewer viewer;

  public void createPartControl(Composite parent) {
    viewer = new TableViewer(SWTUtils.createTable(parent));
    JFaceUtils.createColumn(viewer, "ISIN", 100, ViewableSecurityPosition.GET_ISIN);
    JFaceUtils.createColumn(viewer, "Action", 50, ViewableSecurityPosition.GET_ACTION);
    JFaceUtils.createColumn(viewer, "Name", 300, DisplayNameGetter.INSTANCE);
    JFaceUtils.createColumn(viewer, "Type", 100, ViewableSecurityPosition.GET_TYPE_NAME);
    JFaceUtils.createColumn(viewer, "Opened", 100, ViewableSecurityPosition.GET_OPEN_DATE);
    JFaceUtils.createColumn(viewer, "Closed", 100, ViewableSecurityPosition.GET_CLOSING_DATE);

    viewer.setComparator(new GetterViewerComparator(ViewableSecurityPosition.GET_OPEN_DATE, ViewableSecurityPosition.GET_TYPE_NAME,
        DisplayNameGetter.INSTANCE, ViewableSecurityPosition.GET_ISIN));

    viewer.setContentProvider(new ObservableSetContentProvider());

    List<ViewableSecurityPosition> viewablePositions = ServiceLocator.getDataAccess().getViewablePositions();
    final IObservableSet positions = new WritableSet(viewablePositions, ViewableSecurityPosition.class);
    viewer.setInput(positions);

    final IViewerObservableValue singleSelection = ViewersObservables.observeSingleSelection(viewer);

    EventHandling.addNewSecurityPositionEventHandler(new EventHandler() {
      @Override
      public void handleEvent(final Event event) {
        positions.getRealm().exec(new Runnable() {
          public void run() {
            ViewableSecurityPosition position = EventHandling.extractNewSecurityPosition(event);
            positions.add(position);
            singleSelection.setValue(position);
          }
        });
      }
    });
  }

  public void setFocus() {
    viewer.getTable().setFocus();
  }
}