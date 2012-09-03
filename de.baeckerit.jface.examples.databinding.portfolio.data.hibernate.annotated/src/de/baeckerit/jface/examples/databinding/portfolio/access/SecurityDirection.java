package de.baeckerit.jface.examples.databinding.portfolio.access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityDirection;

/**
 * The direction of speculation, either long or short.
 * 
 * Instances of this class must be immutable!
 */
@Entity
@Immutable
@Table(name = "PM_SECURITY_DIRECTION")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class SecurityDirection implements ISecurityDirection {

  @Id
  @Column(name = "PK", nullable = false, unique = true)
  private String primaryKey;

  @Column(name = "DN", nullable = false, unique = true)
  private String displayName;

  public SecurityDirection() {
  }

  public SecurityDirection(String primaryKey, String displayName) {
    this.primaryKey = primaryKey;
    this.displayName = displayName;
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
    if (!(obj instanceof SecurityDirection))
      return false;
    SecurityDirection other = (SecurityDirection) obj;
    if (primaryKey == null) {
      if (other.primaryKey != null)
        return false;
    } else if (!primaryKey.equals(other.primaryKey))
      return false;
    return true;
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(String primaryKey) {
    this.primaryKey = primaryKey;
  }

  public String getDisplayName() {
    return displayName;
  }

  protected void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
