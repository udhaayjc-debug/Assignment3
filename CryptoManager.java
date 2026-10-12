
/**
 * This is a utility class that encrypts and decrypts a phrase using three
 * different approaches. 
 * 
 * The first approach is called the Vigenere Cipher.Vigenere encryption 
 * is a method of encrypting alphabetic text based on the letters of a keyword.
 * 
 * The second approach is Playfair Cipher. It encrypts two letters (a digraph) 
 * at a time instead of just one.
 * 
 * The third approach is Caesar Cipher. It is a simple replacement cypher. 
 * 
 * @author Huseyin Aygun
 * @version 8/3/2025
 */

import java.util.ArrayList;

public class CryptoManager {

	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;
	// Use 64-character matrix (8X8) for Playfair cipher
	private static final String ALPHABET64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&'()*+,-./:;<=>?@[\\]^_";

	public static boolean isStringInBounds(String plainText) {
		for (int i = 0; i < plainText.length(); i++) {
			if (!(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Vigenere Cipher is a method of encrypting alphabetic text based on the
	 * letters of a keyword. It works as below: Choose a keyword (e.g., KEY). Repeat
	 * the keyword to match the length of the plaintext. Each letter in the
	 * plaintext is shifted by the position of the corresponding letter in the
	 * keyword (A = 0, B = 1, ..., Z = 25).
	 */

	public static String vigenereEncryption(String plainText, String key) {
		// to be implemented by students
		if (!isStringInBounds(plainText) || !isStringInBounds(key)) {
			return "The selected string is not in bounds, Try again.";
		}

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < plainText.length(); i++) {

			int pIndex = ALPHABET64.indexOf(plainText.charAt(i));
			int kIndex = ALPHABET64.indexOf(key.charAt(i % key.length()));

			int encIndex = (pIndex + kIndex) % ALPHABET64.length();

			result.append(ALPHABET64.charAt(encIndex));
		}

		return result.toString();
	}

	// Vigenere Decryption
	public static String vigenereDecryption(String encryptedText, String key) {

		if (!isStringInBounds(key)) {
			return "The selected string is not in bounds, Try again.";
		}

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < encryptedText.length(); i++) {

			int eIndex = ALPHABET64.indexOf(encryptedText.charAt(i));
			int kIndex = ALPHABET64.indexOf(key.charAt(i % key.length()));

			int decIndex = (eIndex - kIndex + ALPHABET64.length()) % ALPHABET64.length();

			result.append(ALPHABET64.charAt(decIndex));
		}

		return result.toString();
	}

	/**
	 * Playfair Cipher encrypts two letters at a time instead of just one. It works
	 * as follows: A matrix (8X8 in our case) is built using a keyword Plaintext is
	 * split into letter pairs (e.g., ME ET YO UR). Encryption rules depend on the
	 * positions of the letters in the matrix: Same row: replace each letter with
	 * the one to its right. Same column: replace each with the one below.
	 * Rectangle: replace each letter with the one in its own row but in the column
	 * of the other letter in the pair.
	 */

	public static String playfairEncryption(String plainText, String key) {
		// to be implemented by students
		if (!isStringInBounds(plainText) || !isStringInBounds(key)) {
			return "The selected string is not in bounds, Try again.";
		}

		ArrayList<Character> matrix = buildPlayfairMatrix(key);

		ArrayList<Character> text = new ArrayList<>();
		for (char c : plainText.toCharArray()) {
			text.add(c);
		}
 		
		// A final X completes an unmatched last pair.
		if (text.size() % 2 != 0) {
			text.add('X');
		}

		for (int i = 0; i < text.size(); i += 2) {

			char a = text.get(i);
			char b = text.get(i + 1);

			int posA = matrix.indexOf(a);
			int posB = matrix.indexOf(b);

			int r1 = posA / 8, c1 = posA % 8;
			int r2 = posB / 8, c2 = posB % 8;

			if (r1 == r2) {
				// Same row: move each character one column right, wrapping if needed.
				c1 = (c1 + 1) % 8;
				c2 = (c2 + 1) % 8;
			} else if (c1 == c2) {
				// Same column: move each character one row down.
				r1 = (r1 + 1) % 8;
				r2 = (r2 + 1) % 8;
			} else {
				// Rectangle: exchange the columns while preserving each row.
				int temp = c1;
				c1 = c2;
				c2 = temp;
			}

			text.set(i, matrix.get(r1 * 8 + c1));
			text.set(i + 1, matrix.get(r2 * 8 + c2));
		}

		StringBuilder result = new StringBuilder();
        for (char c : text) {
            result.append(c);
        }
		return result.toString();
	}

	// Playfair Decryption
	public static String playfairDecryption(String encryptedText, String key) {

		if (!isStringInBounds(key)) {
			return "The selected string is not in bounds, Try again.";
		}

		ArrayList<Character> matrix = buildPlayfairMatrix(key);

		ArrayList<Character> text = new ArrayList<>();
		for (char c : encryptedText.toCharArray()) {
			text.add(c);
		}

		for (int i = 0; i < text.size(); i += 2) {

			char a = text.get(i);
			char b = text.get(i + 1);

			int posA = matrix.indexOf(a);
			int posB = matrix.indexOf(b);

			int r1 = posA / 8, c1 = posA % 8;
			int r2 = posB / 8, c2 = posB % 8;

			if (r1 == r2) {
				c1 = (c1 + 7) % 8;
				c2 = (c2 + 7) % 8;
			} else if (c1 == c2) {
				r1 = (r1 + 7) % 8;
				r2 = (r2 + 7) % 8;
			} else {
				int temp = c1;
				c1 = c2;
				c2 = temp;
			}

			text.set(i, matrix.get(r1 * 8 + c1));
			text.set(i + 1, matrix.get(r2 * 8 + c2));
		}

		StringBuilder result = new StringBuilder();
        for (char c : text) {
            result.append(c);
        }
        String finalResult = result.toString();

		// only remove trailing X if it looks like padding
		if (finalResult.endsWith("X")) {
			finalResult = finalResult.substring(0, finalResult.length() - 1);
		}

		return finalResult;

	}

	/**
	 * Caesar Cipher is a simple substitution cipher that replaces each letter in a
	 * message with a letter some fixed number of positions down the alphabet. For
	 * example, with a shift of 3, 'A' would become 'D', 'B' would become 'E', and
	 * so on.
	 */

	public static String caesarEncryption(String plainText, int key) {
		// to be implemented by students
		if (!isStringInBounds(plainText)) {
			return "The selected string is not in bounds, Try again.";
		}

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < plainText.length(); i++) {

			char c = plainText.charAt(i);
			int shifted = c + key;

			while (shifted > UPPER_RANGE) {
				shifted = LOWER_RANGE + (shifted - UPPER_RANGE - 1);
			}

			result.append((char) shifted);
		}

		return result.toString();
	}

	// Caesar Decryption
    // Decrypts a Caesar message by shifting in the opposite direction.
	public static String caesarDecryption(String encryptedText, int key) {

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < encryptedText.length(); i++) {

			char c = encryptedText.charAt(i);
			int shifted = c - key;

			while (shifted < LOWER_RANGE) {
				shifted = UPPER_RANGE - (LOWER_RANGE - shifted - 1);
			}

			result.append((char) shifted);
		}

		return result.toString();
	}

	/*
	 * Playfair Helper build matrix methods
	 */
	private static ArrayList<Character> buildPlayfairMatrix(String key) {

		ArrayList<Character> matrix = new ArrayList<>();

		key = key.toUpperCase();

		for (char c : key.toCharArray()) {
			if (!matrix.contains(c) && ALPHABET64.indexOf(c) != -1) {
				matrix.add(c);
			}
		}
		
		// Fill all remaining cells with the alphabet's unused characters.
		for (char c : ALPHABET64.toCharArray()) {
			if (!matrix.contains(c)) {
				matrix.add(c);
			}
		}

		return matrix;
	}
}
