package de.baeckerit.jdk.util.mvc;

import java.util.List;

public class SelectFromListCommand<T> extends MvcCommand {

  private final SelectFromList<T> selectFromList;
  private List<T> newSelected;

  public SelectFromListCommand(SelectFromList<T> selectFromList) {
    this.selectFromList = selectFromList;
  }

  public SelectFromList<T> getSelectFromList() {
    return selectFromList;
  }

  public void setNewSelected(List<T> newSelected) {
    if (newSelected == null)
      throw new NullPointerException("newSelected");
    this.newSelected = newSelected;
  }

  @Override
  public boolean execute() {
    if (newSelected == null)
      throw new NullPointerException("newSelected");
    return selectFromList.setSelectedAndNotify(newSelected);
  }
}
