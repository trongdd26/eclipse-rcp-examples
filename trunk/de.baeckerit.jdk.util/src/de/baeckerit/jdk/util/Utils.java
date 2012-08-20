package de.baeckerit.jdk.util;

import java.util.Calendar;
import java.util.Date;


public final class Utils {

  public static <T> boolean equals(T a, T b) {
    return a == b || (a != null && a.equals(b));
  }

  private Utils() {
  }

  public static String getContent(String s) {
  	if (s == null) {
  		return null;
  	}
  	s = s.trim();
  	if (s.length() == 0) {
  		return null;
  	}
  	return s;
  }

  public static boolean onlyDigits(String s) {
  	for (int i = 0; i < s.length(); i++) {
  		if (!Character.isDigit(s.charAt(i))) {
  			return false;
  		}
  	}
  	return true;
  }

  public static int getCurrentYear() {
  	return Calendar.getInstance().get(Calendar.YEAR);
  }

  public static String getCurrentYearString() {
  	return String.valueOf(getCurrentYear());
  }

  public static Date toDate(Date date) {
  	return date == null ? null : new Date(date.getTime());
  }

  public static <T extends Comparable<T>> int compare(T first, T second) {
  	return compare(first, second, true);
  }

  public static <T extends Comparable<T>> int compare(T first, T second, boolean nullGreater) {
  	if (first == null) {
  		return second == null ? 0 : nullGreater ? 1 : -1;
  	}
  	return second == null ? nullGreater ? -1 : 1 : first.compareTo(second);
  }
}
