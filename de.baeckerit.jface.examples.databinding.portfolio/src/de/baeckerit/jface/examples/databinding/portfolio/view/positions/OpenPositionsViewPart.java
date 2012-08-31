package de.baeckerit.jface.examples.databinding.portfolio.view.positions;

import java.util.List;

import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableSetContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.EventHandling;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityPosition;
import de.baeckerit.jface.examples.databinding.portfolio.view.SecurityPositionBuyCellLabelProvider;
import de.baeckerit.jface.examples.databinding.portfolio.view.SecurityPositionIsinCellLabelProvider;
import de.baeckerit.jface.examples.databinding.portfolio.view.SecurityPositionNameCellLabelProvider;
import de.baeckerit.jface.examples.databinding.portfolio.view.SecurityPositionOpenDateCellLabelProvider;
import de.baeckerit.jface.examples.databinding.portfolio.view.SecurityPositionTypeNameCellLabelProvider;
import de.baeckerit.jface.util.CompositeViewerComparator;
import de.baeckerit.jface.util.JFaceUtils;
import de.baeckerit.swt.util.SWTUtils;

public class OpenPositionsViewPart extends ViewPart {
  public static final String ID = "de.baeckerit.jface.examples.databinding.portfolio.view.positions.open";

  private TableViewer viewer;

  public void createPartControl(Composite parent) {
    viewer = new TableViewer(SWTUtils.createTable(parent));
    JFaceUtils.createColumn(viewer, "ISIN", 100, new SecurityPositionIsinCellLabelProvider());
    JFaceUtils.createColumn(viewer, "Action", 50, new SecurityPositionBuyCellLabelProvider());
    JFaceUtils.createColumn(viewer, "Name", 300, new SecurityPositionNameCellLabelProvider());
    JFaceUtils.createColumn(viewer, "Type", 100, new SecurityPositionTypeNameCellLabelProvider());
    JFaceUtils.createColumn(viewer, "Opened", 100, new SecurityPositionOpenDateCellLabelProvider());

    viewer.setComparator(new CompositeViewerComparator(new ViewerComparator() {
      public int compare(Viewer viewer, Object e1, Object e2) {
        return Utils.compare(((SecurityPosition) e1).getOpenDate(), ((SecurityPosition) e2).getOpenDate());
      };
    }, new ViewerComparator() {
      public int compare(Viewer viewer, Object e1, Object e2) {
        return Utils.compare(((SecurityPosition) e1).getSecurityTypeName(), ((SecurityPosition) e2).getSecurityTypeName());
      };
    }, new ViewerComparator() {
      public int compare(Viewer viewer, Object e1, Object e2) {
        return Utils.compare(((SecurityPosition) e1).getDisplayName(), ((SecurityPosition) e2).getDisplayName());
      };
    }, new ViewerComparator() {
      public int compare(Viewer viewer, Object e1, Object e2) {
        return Utils.compare(((SecurityPosition) e1).getIsin(), ((SecurityPosition) e2).getIsin());
      };
    }));

    viewer.setContentProvider(new ObservableSetContentProvider());

    List<SecurityPosition> openPositions = ServiceLocator.getDataAccess().getOpenPositions();
    final IObservableSet positions = new WritableSet(openPositions, SecurityPosition.class);
    viewer.setInput(positions);

    final IViewerObservableValue singleSelection = ViewersObservables.observeSingleSelection(viewer);

    EventHandling.addNewSecurityPositionEventHandler(new EventHandler() {
      @Override
      public void handleEvent(final Event event) {
        positions.getRealm().exec(new Runnable() {
          public void run() {
            SecurityPosition position = EventHandling.extractNewSecurityPosition(event);
            if (position.getClosingDate() == null) {
              positions.add(position);
              singleSelection.setValue(position);
            }
          }
        });
      }
    });
  }

  public void setFocus() {
    viewer.getTable().setFocus();
  }
}