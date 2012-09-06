package de.baeckerit.jface.examples.databinding.portfolio.data.mybatis;

import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityDirection;


/**
 * The direction of speculation, either long or short.
 * 
 * Instances of this class must be immutable!
 */
public class SecurityDirection extends EntityWithStringKey implements ISecurityDirection {
  private String displayName;

  public SecurityDirection() {
    this(null, null);
  }

  public SecurityDirection(String primaryKey, String displayName) {
    super(primaryKey);
    this.displayName = displayName;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof SecurityDirection))
      return false;
    return super.equals(obj);
  }

  public String getDisplayName() {
    return displayName;
  }

  protected void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
