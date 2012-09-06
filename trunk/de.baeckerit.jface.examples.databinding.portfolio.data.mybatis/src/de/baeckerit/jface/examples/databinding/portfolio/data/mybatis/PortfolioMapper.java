package de.baeckerit.jface.examples.databinding.portfolio.data.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface PortfolioMapper {

  @Select("select pk primaryKey, dn displayName from pm_security_direction")
  List<SecurityDirection> selectAllSecurityDirections();

  @Select("select pk primaryKey, dn displayName from pm_security_type")
  List<SecurityType> selectAllSecurityTypes();

  @Select("select pk primaryKey, dn securityName, isin, first_trading_day firstTradingDay, last_trading_day lastTradingDay"
      + ", fk_security_type fkSecurityType, fk_security_direction fkSecurityDirection from pm_security")
  List<Security> selectAllSecurities();

  public static final String SELECT_ALL_SECURITIES = "select pk primaryKey, buy, fk_security fkSecurity, open_date openDate, closing_date closingDate from pm_security_position";

  @Select(SELECT_ALL_SECURITIES + " where closing_date is null")
  List<SecurityPosition> selectOpenSecurityPositions();

  @Select(SELECT_ALL_SECURITIES + " where closing_date is not null")
  List<SecurityPosition> selectClosedSecurityPositions();

  @Insert("insert into pm_security (pk, dn, fk_security_type, isin, fk_security_direction, first_trading_day, last_trading_day) "
      + "values (pm_security_pk_seq.nextval, #{displayName}, #{securityType.primaryKey}, #{isin}, "
      + "#{securityDirection.primaryKey,jdbcType=NUMERIC}, #{firstTradingDay,jdbcType=DATE}, #{lastTradingDay,jdbcType=DATE})")
  @Options(useGeneratedKeys = true, keyProperty = "primaryKey", keyColumn = "pk")
  void insertSecurity(Security security);

  @Insert("insert into pm_security_position (pk, fk_security, buy, open_date, closing_date) values "
      + "(pm_security_position_pk_seq.nextval, #{security.primaryKey}, "
      + "#{buy}, #{openDate,jdbcType=DATE}, #{closingDate,jdbcType=DATE})")
  @Options(useGeneratedKeys = true, keyProperty = "primaryKey", keyColumn = "pk")
  void insertPosition(SecurityPosition position);
}
