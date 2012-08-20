package de.baeckerit.jdk.util.mvc;

import de.baeckerit.jdk.util.CharacterRange;
import de.baeckerit.jdk.util.CharacterSet;
import de.baeckerit.jdk.util.CharacterSets;
import de.baeckerit.jdk.util.CharacterString;

public interface MvcConstants {

	CharacterRange ANY_CHARACTER = new CharacterRange();

	CharacterRange ANY_DIGIT = new CharacterRange('0', '9');

	CharacterRange ANY_UPPER = new CharacterRange('A', 'Z');
	CharacterRange ANY_LOWER = new CharacterRange('a', 'z');
	CharacterSet ANY_LETTER = new CharacterSets(ANY_UPPER, ANY_LOWER);

	CharacterSet LETTERS_AND_DIGITS = new CharacterSets(ANY_UPPER, ANY_LOWER, ANY_DIGIT);
	CharacterString SPECIAL_CHARACTERS = new CharacterString(" !\"§$%&/()=?\\+*#-_,.;:'~@€|<>[]{}");

	CharacterString DE_UMLAUT_LOWER = new CharacterString("äöüß");
	CharacterString DE_UMLAUT_UPPER = new CharacterString("ÄÖÜ");
	CharacterString DE_UMLAUT = new CharacterString("äöüßÄÖÜ");
	CharacterSet DE_LETTERS = new CharacterSets(ANY_LETTER, DE_UMLAUT);
	CharacterSet DE_LETTERS_AND_DIGITS = new CharacterSets(ANY_LETTER, ANY_DIGIT, DE_UMLAUT);
}
