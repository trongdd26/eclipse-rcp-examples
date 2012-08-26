package de.baeckerit.jface.examples.databinding.portfolio.util.compare;

import org.eclipse.jface.viewers.ViewerComparator;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurity;
import de.baeckerit.jface.util.CompositeViewerComparator;
import de.baeckerit.jface.util.DisplayNameViewerComparator;

public class ViewableSecurityViewerComparator extends CompositeViewerComparator {

  private static final ViewerComparator SORT_VIEWABLE_SECURITY_BY_TYPE_NAME = new ViewerComparator() {
    public int compare(org.eclipse.jface.viewers.Viewer viewer, Object e1, Object e2) {
      return getComparator().compare(((ViewableSecurity) e1).getSecurityTypeName(), ((ViewableSecurity) e2).getSecurityTypeName());
    };
  };

  private static final ViewerComparator SORT_VIEWABLE_SECURITY_BY_ISIN = new ViewerComparator() {
    public int compare(org.eclipse.jface.viewers.Viewer viewer, Object e1, Object e2) {
      return Utils.compare(((ViewableSecurity) e1).getIsin(), ((ViewableSecurity) e2).getIsin());
    };
  };

  private static final ViewerComparator SORT_VIEWABLE_SECURITY_BY_FIRST_TRADING_DAY = new ViewerComparator() {
    public int compare(org.eclipse.jface.viewers.Viewer viewer, Object e1, Object e2) {
      return Utils.compare(((ViewableSecurity) e1).getFirstTradingDay(), ((ViewableSecurity) e2).getFirstTradingDay());
    };
  };

  private static final ViewerComparator SORT_VIEWABLE_SECURITY_BY_LAST_TRADING_DAY = new ViewerComparator() {
    public int compare(org.eclipse.jface.viewers.Viewer viewer, Object e1, Object e2) {
      return Utils.compare(((ViewableSecurity) e1).getLastTradingDay(), ((ViewableSecurity) e2).getLastTradingDay());
    };
  };

  public ViewableSecurityViewerComparator() {
    super(SORT_VIEWABLE_SECURITY_BY_TYPE_NAME, new DisplayNameViewerComparator(), SORT_VIEWABLE_SECURITY_BY_ISIN,
        SORT_VIEWABLE_SECURITY_BY_FIRST_TRADING_DAY, SORT_VIEWABLE_SECURITY_BY_LAST_TRADING_DAY);
  }
}