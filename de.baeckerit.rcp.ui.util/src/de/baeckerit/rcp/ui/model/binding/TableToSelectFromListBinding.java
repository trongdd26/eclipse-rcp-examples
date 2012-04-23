package de.baeckerit.rcp.ui.model.binding;

import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;

import de.baeckerit.jdk.util.mvc.MvcCommand;
import de.baeckerit.jdk.util.mvc.MvcCommandManager;
import de.baeckerit.jdk.util.mvc.SelectFromList;
import de.baeckerit.jdk.util.mvc.SelectFromListCommand;
import de.baeckerit.jdk.util.mvc.SelectFromListListener;
import de.baeckerit.jface.util.SafeDoubleClickListener;
import de.baeckerit.jface.util.SafeSelectionChangedListener;
import de.baeckerit.rcp.ui.util.UtilsViewer;

public class TableToSelectFromListBinding {

  private boolean updating = false;

  public <T> TableToSelectFromListBinding(final TableViewer viewer, final SelectFromListCommand<T> command) {

    final SelectFromList<T> selectFromList = command.getSelectFromList();

    final SelectFromListListener<T> listener = new SelectFromListListener<T>() {
      @Override
      public void listChanged(List<T> newList) {
        if (!updating && isEnabled()) {
          updating = true;
          try {
            viewer.setInput(newList);
          } finally {
            updating = false;
          }
        }
      }

      @Override
      public void selectionChanged(List<T> newSelection) {
        if (!updating && isEnabled()) {
          updating = true;
          try {
            viewer.setSelection(new StructuredSelection(newSelection));
          } finally {
            updating = false;
          }
        }
      }

      @Override
      public void stateChanged(List<T> changedElements) {
        if (!updating && isEnabled()) {
          updating = true;
          try {
            if (changedElements.isEmpty()) {
              viewer.refresh();
            } else {
              viewer.update(changedElements.toArray(), null);
            }
          } finally {
            updating = false;
          }
        }
      }

      @Override
      public void safeUpdateSelf() {
        super.safeUpdateSelf();
        listChanged(selectFromList.getElements());
        selectionChanged(selectFromList.getSelected());
      }
    };

    listener.updateSelf();

    selectFromList.addListener(listener);

    viewer.getTable().addDisposeListener(new DisposeListener() {

      @Override
      public void widgetDisposed(DisposeEvent event) {
        selectFromList.removeListener(listener);
      }
    });

    viewer.addSelectionChangedListener(new SafeSelectionChangedListener() {

      @Override
      public void safeSelectionChanged(SelectionChangedEvent event) {
        if (!updating) {
          updating = true;
          try {
            command.setNewSelected(UtilsViewer.<T> getSelectedElements(event.getSelection()));
            MvcCommandManager.execute(command);
          } finally {
            updating = false;
          }
        }
      }
    });
  }

  public <T> TableToSelectFromListBinding(TableViewer viewer, SelectFromListCommand<T> command, final MvcCommand doubleClickCommand) {
    this(viewer, command);

    viewer.addDoubleClickListener(new SafeDoubleClickListener() {

      @Override
      public void safeDoubleClick(DoubleClickEvent event) {
        MvcCommandManager.execute(doubleClickCommand);
      }
    });
  }
}
