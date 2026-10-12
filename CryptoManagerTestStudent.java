import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/*
 * Class: CMSC203 
 * Instructor:
 * Description: CryptoManagerTestStudent class (JUnit test cases)
 * Due: 3/08/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming  assignment independently. 
*  I have not copied the code from a student or any source. 
*  I have not given my code to any student.
*  Print your Name here: Udhaay Jayachandran
*/

public class CryptoManagerTestStudent {

    /*
     * is StringIn Bounds true Test Cases
     */
    @Test
    public void testIsStringInBoundsTrue() {
        assertTrue(CryptoManager.isStringInBounds("TEST 123"));
    }

    /*
     * is StringIn Bounds false Test Cases
     */
    @Test
    public void testIsStringInBoundsFalse() {
        assertFalse(CryptoManager.isStringInBounds("tes enc"));
    }

    /*
     * Caesar Cipher Test Cases
     */
    @Test
    public void testCaesarEncryptionDecryption() {
        String text = "TEST1";
        int key = 5;
        String encrypted = CryptoManager.caesarEncryption(text, key);
        String decrypted = CryptoManager.caesarDecryption(encrypted, key);
        assertEquals(text, decrypted);
    }

    /*
     * Vigenere Cipher Test Cases
     */
    @Test
    public void testVigenereEncryptionDecryption() {
        String text = "RINYESH";
        String key = "KEY1";
        String encrypted = CryptoManager.vigenereEncryption(text, key);
        String decrypted = CryptoManager.vigenereDecryption(encrypted, key);
        assertEquals(text, decrypted);
    }

    /*
     * Vigenere Cipher Single Character Cases
     */
    @Test
    public void testVigenereSingleCharacter() {
        String text = "A";
        String key = "Z";
        String encrypted = CryptoManager.vigenereEncryption(text, key);
        String decrypted = CryptoManager.vigenereDecryption(encrypted, key);
        assertEquals(text, decrypted);
    }

     /*
     * Playfair Cipher Test cases
     */
    @Test
    public void testPlayfairEncryptionDecryption() {
        String text = "RINYESHJ";
        String key = "MYPASSWORD";
        String encrypted = CryptoManager.playfairEncryption(text, key);
        String decrypted = CryptoManager.playfairDecryption(encrypted, key);
        assertEquals(text.replace(" ", ""), decrypted.replace(" ", ""));
    }

    /*
     * Playfair Cipher Test odd length text
     */
    @Test
    public void testPlayfairOddLengthText() {
        String text = "JAVA";
        String key = "SAMPLEKEY";
        String encrypted = CryptoManager.playfairEncryption(text, key);
        String decrypted = CryptoManager.playfairDecryption(encrypted, key);
        assertEquals(text, decrypted);
    }

    /*
     * Playfair Cipher Test Single Character length text
     */
    @Test
    public void testPlayfairSingleCharacter() {
        String text = "X";
        String key = "SAMPLEKEY";
        String encrypted = CryptoManager.playfairEncryption(text, key);
        String decrypted = CryptoManager.playfairDecryption(encrypted, key);
        assertEquals(text, decrypted);
    }

    /*
     * Playfair Cipher Test With Symbolr text
     */
    @Test
    public void testPlayfairWithSymbols() {
        String text = "!@#";
        String key = "TESTKEY";
        String encrypted = CryptoManager.playfairEncryption(text, key);
        String decrypted = CryptoManager.playfairDecryption(encrypted, key);
        assertEquals(text, decrypted); 
    }
}