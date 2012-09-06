create sequence pm_security_pk_seq minvalue 1 maxvalue 2147483647 increment by 1 start with 1 nocache order nocycle;
create sequence pm_security_position_pk_seq minvalue 1 maxvalue 2147483647 increment by 1 start with 1 nocache order nocycle;

create table pm_security_direction (
  pk varchar(3) constraint pm_security_direction_pk primary key,
  dn varchar(100 char) not null
);

create unique index idx_pm_sec_dir_upper_dn on pm_security_direction(UPPER(dn));

create table pm_security_type (
  pk varchar(3) constraint pm_security_type_pk primary key,
  dn varchar(100 char) not null
);

create unique index idx_pm_sec_typ_upper_dn on pm_security_type(upper(dn));

create table pm_security (
  pk number(9) constraint pm_security_pk primary key,
  dn varchar(100 char) not null,
  fk_security_type varchar(3) not null references pm_security_type(pk),
  fk_security_direction varchar(3) references pm_security_direction(pk),
  isin char(12) not null,
  first_trading_day date not null,
  last_trading_day date
);

-- Ensure uniqueness of trading dates for an ISIN
-- For lookups by isin
create unique index idx_pm_security_unique on pm_security(isin, first_trading_day);

alter table pm_security add constraint chk_security__pk check (pk > 0 and pk <= 2147483647);
alter table pm_security add constraint chk_security__trading_range check (last_trading_day is null or first_trading_day <= last_trading_day);

create or replace
trigger trg_security_date_check before insert on pm_security for each row
declare
  overlapcount number;
begin
  if :new.last_trading_day is null then
    select count(*) into overlapcount from pm_security s where s.isin = :new.isin and 
      ((s.last_trading_day is null) or (s.last_trading_day >= :new.first_trading_day));
  else
    select count(*) into overlapcount from pm_security s where s.isin = :new.isin and
      not (s.first_trading_day > :new.last_trading_day or ((s.last_trading_day is not null) and (s.last_trading_day < :new.first_trading_day)));
  end if;
  if overlapcount > 0 then
    raise_application_error(-20101, 'Overlapping ranges: ' || overlapcount);
  end if ;
end;

create table pm_security_position (
  pk number(9) constraint pm_security_position_pk primary key,
  fk_security number(9) not null references pm_security(pk),
  buy number(1) not null,
  open_date date not null,
  closing_date date
);

alter table pm_security_position add constraint chk_security_position__pk check (pk > 0 and pk <= 2147483647);
alter table pm_security_position add constraint chk_security_position__buy check (buy = 0 or buy = 1);

--create or replace trigger trg_pm_security_pk before insert on pm_security for each row when (new.pk is null or new.pk = -1)
--begin
--  :new.pk := pm_security_pk_seq.nextval;
--end;

--create or replace trigger trg_pm_security_position_pk before insert on pm_security_position for each row when (new.pk is null or new.pk = -1)
--begin
--  :new.pk := pm_security_position_pk_seq.nextval;
--end;