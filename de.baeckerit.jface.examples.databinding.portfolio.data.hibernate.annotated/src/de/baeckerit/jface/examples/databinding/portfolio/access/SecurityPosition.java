package de.baeckerit.jface.examples.databinding.portfolio.access;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurity;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityPosition;

/**
 * A position in a security, either buy (long) or sell (short).
 */
@Entity
@Immutable
@Table(name = "PM_SECURITY_POSITION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SecurityPosition implements ISecurityPosition {

  @Id
  @Column(name = "PK", nullable = false, unique = true)
  @GeneratedValue(generator = "pm-security-position-pk-generator")
  @GenericGenerator(name = "pm-security-position-pk-generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "pm_security_position_pk_seq"))
  private Integer primaryKey;

  @ManyToOne(targetEntity = Security.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_security", nullable = false)
  private ISecurity security;

  @Type(type = "org.hibernate.type.NumericBooleanType")
  private boolean buy;

  @Temporal(TemporalType.DATE)
  @Column(name = "open_date", nullable = false)
  private Date openDate;

  @Temporal(TemporalType.DATE)
  @Column(name = "closing_date", nullable = true)
  private Date closingDate;

  public SecurityPosition() {
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
    if (!(obj instanceof SecurityPosition))
      return false;
    SecurityPosition other = (SecurityPosition) obj;
    if (primaryKey == null) {
      if (other.primaryKey != null)
        return false;
    } else if (!primaryKey.equals(other.primaryKey))
      return false;
    return true;
  }

  public Integer getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(Integer primaryKey) {
    this.primaryKey = primaryKey;
  }

  @Override
  public String getDisplayName() {
    return security.getDisplayName();
  }

  @Override
  public ISecurity getSecurity() {
    return security;
  }

  @Override
  public boolean isBuy() {
    return buy;
  }

  @Override
  public Date getOpenDate() {
    return Utils.toDate(openDate);
  }

  @Override
  public Date getClosingDate() {
    return Utils.toDate(closingDate);
  }

  @Override
  public void setSecurity(ISecurity security) {
    this.security = security;
  }

  @Override
  public void setBuy(boolean buy) {
    this.buy = buy;
  }

  @Override
  public void setOpenDate(Date openDate) {
    this.openDate = openDate;
  }

  @Override
  public void setClosingDate(Date closingDate) {
    this.closingDate = closingDate;
  }

  @Override
  public String getIsin() {
    return security.getIsin();
  }

  @Override
  public String getSecurityTypeName() {
    return security.getSecurityType().getDisplayName();
  }
}
