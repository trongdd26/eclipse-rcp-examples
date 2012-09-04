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

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurity;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityType;

/**
 * A security of some type.
 */
@Entity
@Immutable
@Table(name = "PM_SECURITY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Security implements ISecurity {

  @Id
  @Column(name = "PK", nullable = false, unique = true)
  @GeneratedValue(generator = "pm-security-pk-generator")
  @GenericGenerator(name = "pm-security-pk-generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "pm_security_pk_seq"))
  private Integer primaryKey;

  @ManyToOne(targetEntity = SecurityType.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_security_type", nullable = false)
  private ISecurityType securityType;

  @ManyToOne(targetEntity = SecurityDirection.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_security_direction", nullable = true)
  private ISecurityDirection securityDirection;

  @Column(name = "DN", nullable = false, unique = true)
  private String securityName;

  @Column(name = "isin", nullable = false)
  private String isin;

  @Temporal(TemporalType.DATE)
  @Column(name = "first_trading_day", nullable = false)
  private Date firstTradingDay;

  @Temporal(TemporalType.DATE)
  @Column(name = "last_trading_day", nullable = true)
  private Date lastTradingDay;

  public Security() {
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
    if (!(obj instanceof Security))
      return false;
    Security other = (Security) obj;
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
  public ISecurityType getSecurityType() {
    return securityType;
  }

  @Override
  public ISecurityDirection getSecurityDirection() {
    return securityDirection;
  }

  @Override
  public String getDisplayName() {
    return securityName;
  }

  @Override
  public String getSecurityName() {
    return securityName;
  }

  @Override
  public String getIsin() {
    return isin;
  }

  @Override
  public Date getFirstTradingDay() {
    return Utils.toDate(firstTradingDay);
  }

  @Override
  public Date getLastTradingDay() {
    return Utils.toDate(lastTradingDay);
  }

  @Override
  public void setSecurityDirection(ISecurityDirection securityDirection) {
    this.securityDirection = securityDirection;
  }

  @Override
  public void setSecurityType(ISecurityType securityType) {
    this.securityType = securityType;
  }

  @Override
  public void setSecurityName(String securityName) {
    this.securityName = securityName;
  }

  @Override
  public void setIsin(String isin) {
    this.isin = isin;
  }

  @Override
  public void setFirstTradingDay(Date firstTradingDay) {
    this.firstTradingDay = firstTradingDay;
  }

  @Override
  public void setLastTradingDay(Date lastTradingDay) {
    this.lastTradingDay = lastTradingDay;
  }

  @Override
  public String getSecurityTypeName() {
    return securityType.getDisplayName();
  }

  @Override
  public String getSecurityDirectionName() {
    return securityDirection == null ? null : securityDirection.getDisplayName();
  }
}
