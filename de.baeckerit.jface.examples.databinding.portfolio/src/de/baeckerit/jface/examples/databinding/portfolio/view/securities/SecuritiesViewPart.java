package de.baeckerit.jface.examples.databinding.portfolio.view.securities;

import java.util.List;

import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableSetContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import de.baeckerit.jface.databinding.util.converter.DateToStringConverter;
import de.baeckerit.jface.examples.databinding.portfolio.EventHandling;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.util.compare.ViewableSecurityViewerComparator;
import de.baeckerit.jface.util.DisplayNameCellLabelProvider;
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
    JFaceUtils.createColumn(viewer, "ISIN", 100, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        cell.setText(((Security) cell.getElement()).getIsin());
      }
    });
    JFaceUtils.createColumn(viewer, "Name", 300, new DisplayNameCellLabelProvider());
    JFaceUtils.createColumn(viewer, "Type", 50, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        cell.setText(((Security) cell.getElement()).getSecurityTypeName());
      }
    });
    JFaceUtils.createColumn(viewer, "Direction", 50, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        cell.setText(((Security) cell.getElement()).getSecurityDirectionName());
      }
    });
    JFaceUtils.createColumn(viewer, "Trading Since", 100, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        Security security = (Security) cell.getElement();
        cell.setText(DateToStringConverter.INSTANCE.format(security.getFirstTradingDay()));
      }
    });
    JFaceUtils.createColumn(viewer, "Last Traded", 100, new CellLabelProvider() {
      public void update(ViewerCell cell) {
        Security security = (Security) cell.getElement();
        cell.setText(DateToStringConverter.INSTANCE.format(security.getLastTradingDay()));
      }
    });

    viewer.setComparator(new ViewableSecurityViewerComparator());
    viewer.setContentProvider(new ObservableSetContentProvider());

    List<Security> viewableSecurities = ServiceLocator.getDataAccess().getSecurities();
    final IObservableSet securities = new WritableSet(viewableSecurities, Security.class);
    viewer.setInput(securities);

    final IViewerObservableValue singleSelection = ViewersObservables.observeSingleSelection(viewer);

    EventHandling.addNewSecurityEventHandler(new EventHandler() {
      @Override
      public void handleEvent(final Event event) {
        securities.getRealm().exec(new Runnable() {
          public void run() {
            Security newSecurity = EventHandling.extractNewSecurity(event);
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