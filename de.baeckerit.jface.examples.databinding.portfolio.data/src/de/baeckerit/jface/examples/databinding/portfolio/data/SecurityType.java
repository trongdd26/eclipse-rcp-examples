package de.baeckerit.jface.examples.databinding.portfolio.data;

import de.baeckerit.jdk.util.IProvidesDisplayName;

/**
 * Type of a security.
 * 
 * Instances of this class must be immutable!
 */
public class SecurityType extends EntityWithStringKey implements IProvidesDisplayName {
  private String displayName;

  public SecurityType() {
    this(null, null);
  }

  public SecurityType(String primaryKey, String displayName) {
    super(primaryKey);
    this.displayName = displayName;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof SecurityType))
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
