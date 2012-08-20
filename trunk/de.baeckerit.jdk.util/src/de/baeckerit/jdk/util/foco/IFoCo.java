package de.baeckerit.jdk.util.foco;

/**
 * Something that is both formattable and comparable.
 * <p>
 * To be used by my JFace viewer utilities.
 */
public interface IFoCo {
  String format(Object object);

  int compare(Object left, Object right);
}
