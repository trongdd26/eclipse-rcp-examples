package de.baeckerit.rcp.ui.model.binding;

import java.util.Collection;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;

import de.baeckerit.swt.builder.grid.BuilderCommonBase;

public class TableToCollectionBinding {

  public TableToCollectionBinding(BuilderCommonBase<?> builder, Collection<?> c) {
    Table table = builder.getTable();
    TableViewer viewer = new TableViewer(table);
    viewer.setContentProvider(new ArrayContentProvider());
    viewer.setInput(c);
  }
}
