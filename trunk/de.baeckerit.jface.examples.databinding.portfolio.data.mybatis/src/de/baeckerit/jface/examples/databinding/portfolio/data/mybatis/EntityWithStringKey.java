package de.baeckerit.jface.examples.databinding.portfolio.data.mybatis;

import de.baeckerit.jface.examples.databinding.portfolio.data.IEntityWithStringKey;

public class EntityWithStringKey implements IEntityWithStringKey {

  private String primaryKey;

  public EntityWithStringKey() {
  }

  public EntityWithStringKey(String primaryKey) {
    this.primaryKey = primaryKey;
  }

  @Override
  public String getPrimaryKey() {
    return primaryKey;
  }

  protected void setPrimaryKey(String primaryKey) {
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
    if (!(obj instanceof EntityWithStringKey))
      return false;
    EntityWithStringKey other = (EntityWithStringKey) obj;
    if (primaryKey == null) {
      if (other.primaryKey != null)
        return false;
    } else if (!primaryKey.equals(other.primaryKey))
      return false;
    return true;
  }
}