package de.baeckerit.jface.examples.databinding.portfolio.access;

import de.baeckerit.jface.examples.databinding.portfolio.data.IEntityWithIntegerKey;

public class EntityWithIntegerKey implements IEntityWithIntegerKey {

  private Integer primaryKey;

  public EntityWithIntegerKey() {
    super();
  }

  @Override
  public Integer getPrimaryKey() {
    return primaryKey;
  }

  protected void setPrimaryKey(Integer primaryKey) {
    this.primaryKey = primaryKey;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof EntityWithIntegerKey))
      return false;
    EntityWithIntegerKey other = (EntityWithIntegerKey) obj;
    if (primaryKey == null) {
      if (other.primaryKey != null)
        return false;
    } else if (!primaryKey.equals(other.primaryKey))
      return false;
    return true;
  }
}