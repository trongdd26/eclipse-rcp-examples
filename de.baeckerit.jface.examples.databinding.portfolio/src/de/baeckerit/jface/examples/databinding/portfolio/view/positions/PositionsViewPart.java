package de.baeckerit.jface.examples.databinding.portfolio.view.positions;

import java.util.List;

import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableSetContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.databinding.util.converter.DateToStringConverter;
import de.baeckerit.jface.examples.databinding.portfolio.EventHandling;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurityPosition;
import de.baeckerit.jface.util.CompositeViewerComparator;
import de.baeckerit.jface.util.JFaceUtils;
import de.baeckerit.swt.util.SWTUtils;

public class PositionsViewPart extends ViewPart {
  public static final String ID = "de.baeckerit.jface.examples.databinding.portfolio.view.positions";

  private TableViewer viewer;

  public void createPartControl(Composite parent) {
    viewer = new TableViewer(SWTUtils.createTable(parent));
    JFaceUtils.createColumn(viewer, "ISIN", 100, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        ViewableSecurityPosition position = (ViewableSecurityPosition) cell.getElement();
        cell.setText(position.getIsin());
      }
    });
    JFaceUtils.createColumn(viewer, "Action", 50, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        ViewableSecurityPosition position = (ViewableSecurityPosition) cell.getElement();
        cell.setText(position.isBuy() ? "Buy" : "Sell");
      }
    });
    JFaceUtils.createColumn(viewer, "Name", 300, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        ViewableSecurityPosition position = (ViewableSecurityPosition) cell.getElement();
        cell.setText(position.getIsin());
      }
    });
    JFaceUtils.createColumn(viewer, "Type", 100, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        ViewableSecurityPosition position = (ViewableSecurityPosition) cell.getElement();
        cell.setText(position.getSecurityTypeName());
      }
    });
    JFaceUtils.createColumn(viewer, "Opened", 100, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        ViewableSecurityPosition position = (ViewableSecurityPosition) cell.getElement();
        cell.setText(DateToStringConverter.INSTANCE.format(position.getOpenDate()));
      }
    });
    JFaceUtils.createColumn(viewer, "Closed", 100, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        ViewableSecurityPosition position = (ViewableSecurityPosition) cell.getElement();
        cell.setText(DateToStringConverter.INSTANCE.format(position.getClosingDate()));
      }
    });

    viewer.setComparator(new CompositeViewerComparator(new ViewerComparator() {
      public int compare(Viewer viewer, Object e1, Object e2) {
        return Utils.compare(((ViewableSecurityPosition) e1).getOpenDate(), ((ViewableSecurityPosition) e2).getOpenDate());
      };
    }, new ViewerComparator() {
      public int compare(Viewer viewer, Object e1, Object e2) {
        return Utils.compare(((ViewableSecurityPosition) e1).getSecurityTypeName(), ((ViewableSecurityPosition) e2).getSecurityTypeName());
      };
    }, new ViewerComparator() {
      public int compare(Viewer viewer, Object e1, Object e2) {
        return Utils.compare(((ViewableSecurityPosition) e1).getDisplayName(), ((ViewableSecurityPosition) e2).getDisplayName());
      };
    }, new ViewerComparator() {
      public int compare(Viewer viewer, Object e1, Object e2) {
        return Utils.compare(((ViewableSecurityPosition) e1).getIsin(), ((ViewableSecurityPosition) e2).getIsin());
      };
    }));

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