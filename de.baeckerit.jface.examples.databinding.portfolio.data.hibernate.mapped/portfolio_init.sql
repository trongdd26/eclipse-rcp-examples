insert into pm_security_direction values ('L', 'Long');
insert into pm_security_direction values ('S', 'Short');
insert into pm_security_type values ('SHA', 'Share');
insert into pm_security_type values ('ETF', 'ETF');
insert into pm_security_type values ('OPT', 'Option');
commit;

insert into pm_security values (pm_security_pk_seq.nextval,
  'HENKEL AG &'||' CO. KGAA Inhaber-Vorzugsaktien O.ST.O.N', 'SHA', null, 'DE0006048432', date '1962-02-05', null);
insert into pm_security values (pm_security_pk_seq.nextval,
  'Metro AG Inhaber-Stammaktien O.N.', 'SHA', null, 'DE0007257503', date '1963-02-05', null);
insert into pm_security values (pm_security_pk_seq.nextval,
  'Daimler AG Namensaktien O.N.', 'SHA', null, 'DE0007100000', date '1964-02-05', null);
insert into pm_security values (pm_security_pk_seq.nextval,
  'db x-trackers DAX ETF', 'ETF', 'L', 'LU0274211480', date '2000-02-05', null);
insert into pm_security values (pm_security_pk_seq.nextval,
  'db x-trackers ShortDAX ETF', 'ETF', 'L', 'LU0292106241', date '2000-02-05', null);
insert into pm_security values (pm_security_pk_seq.nextval,
  'CALL Daimler AG Namensaktien O.N.', 'OPT', 'L', 'DE000BN4EY57', date '2012-02-05', null);
insert into pm_security values (pm_security_pk_seq.nextval,
  'PUT Daimler AG Namensaktien O.N.', 'OPT', 'S', 'DE000BN4FG33', date '2012-02-05',  date '2012-03-05');
insert into pm_security values (pm_security_pk_seq.nextval,
  'PUT Daimler AG Namensaktien O.N.', 'OPT', 'S', 'DE000BN4FG33', date '2012-04-05',  null);
commit;

declare
  cursor c1 is select pk from pm_security;
  v_security_pk pm_security.pk%type;
begin
  open c1;
  loop
    fetch c1 into v_security_pk;
    exit when c1%notfound;
    insert into pm_security_position values (pm_security_position_pk_seq.nextval,
      v_security_pk, 1, date '2012-01-01', date '2012-02-01');
    insert into pm_security_position values (pm_security_position_pk_seq.nextval,
      v_security_pk, 1, date '2012-04-01', null);
  end loop;
  close c1;
end;
/

commit;