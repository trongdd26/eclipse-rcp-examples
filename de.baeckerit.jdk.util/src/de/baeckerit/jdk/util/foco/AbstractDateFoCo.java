package de.baeckerit.jdk.util.foco;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.baeckerit.jdk.util.getter.IGetter;

public abstract class AbstractDateFoCo extends AbstractComparableFoCo<Date> implements IGetter<Date, Object> {

  public static final ThreadLocal<SimpleDateFormat> dateFormats = new ThreadLocal<SimpleDateFormat>() {
    protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat("dd.MM.yyyy");
    }
  };

  protected AbstractDateFoCo(boolean nullIsAfter) {
    super(nullIsAfter);
  }

  public abstract Date get(Object object);

  @Override
  public String format(Object object) {
    Date date = get(object);
    return date == null ? "" : dateFormats.get().format(date);
  }
}
