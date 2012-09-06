package de.baeckerit.jface.examples.databinding.portfolio.util.compare;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurity;
import de.baeckerit.jface.util.CompositeViewerComparator;

public class ViewableSecurityViewerComparator extends CompositeViewerComparator {

  private static final ViewerComparator SORT_VIEWABLE_SECURITY_BY_ISIN = new ViewerComparator() {
    public int compare(Viewer viewer, Object e1, Object e2) {
      return Utils.compare(((ISecurity) e1).getIsin(), ((ISecurity) e2).getIsin());
    };
  };

  private static final ViewerComparator SORT_VIEWABLE_SECURITY_BY_FIRST_TRADING_DAY = new ViewerComparator() {
    public int compare(Viewer viewer, Object e1, Object e2) {
      return Utils.compare(((ISecurity) e1).getFirstTradingDay(), ((ISecurity) e2).getFirstTradingDay());
    };
  };

  public ViewableSecurityViewerComparator() {
    super(SORT_VIEWABLE_SECURITY_BY_ISIN, SORT_VIEWABLE_SECURITY_BY_FIRST_TRADING_DAY);
  }
}