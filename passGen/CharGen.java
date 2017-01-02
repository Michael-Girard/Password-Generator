package passGen;

import java.util.Random;

/**
* This class generates character 
* combinations for a characters.
*/
public class CharGen {
	
	private String characters; // String of characters
	private static final String specChar = "!\"#$%&\'()*+,-.:;<=>?@[\\]^_`{|}~"; // Special character types
	private static int length; // Length of string characters
	private static final char[] charArray = {getRandomCapitalLetter(), getRandomMinusculeLetter(), getRandomNumericCharacter(), getRandomSpecialCharacter()};
	
	CharGen() {		
	
	}
	
	// Generate a random minuscule letter
	public static char getRandomMinusculeLetter() {
		return (char)('a' + Math.random() * ('z' - 'a' + 1));

	}
	// Generate a random capital letter
	public static char getRandomCapitalLetter() {
		return (char)('A' + Math.random() * ('Z' - 'A' + 1));

	}
	// Generate a random numeric character
	public static char getRandomNumericCharacter() {
		return (char)('0' + Math.random() * ('9' - '0' + 1));

	}
	
	// Generate a random special character
	public static char getRandomSpecialCharacter() {
		Random rd = new Random();
		return specChar.charAt(rd.nextInt(specChar.length()));

	}
	// Return a string with all character types
	public String getAllCharacters() {
		setCharacters(generateAll());
		return characters;
	}
	// Return a string without special character types
	public String getSomeCharacters() {
		setCharacters(generateWithoutSpec());
		return characters;
	}
	
	public String getCharacters() {
		return characters;
	}
	
	public void setCharacters(String characters) {
		this.characters = characters;
	}
	/*
	 * Get the length of the of string characters. 
	 * Used for loop to determine string length
	 */
	public static int getLength() {
		return length;
	}
	/*
	 * Set length of string characters through user 
	 * input to determine how long they want the string to be
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	// Generate string with all character types
	public static String generateAll(){
		// Use StringBuilder to build a string of characters
		StringBuilder myCharacters = new StringBuilder();
		
		for (int i = 0; i < getLength(); i++){
			myCharacters.append(charArray[(int)(Math.random() * 4)]);
		}
		String charString = myCharacters.toString();
		return charString;
	}
	
	// Generate string without special character types
	public static String generateWithoutSpec(){
		/*
		 * Use StringBuilder to build a string of characters
		 */
		StringBuilder myCharacters = new StringBuilder();
		
		for (int i = 0; i < getLength(); i++){
			myCharacters.append(charArray[(int)(Math.random() * 3)]);
		}
		String charString = myCharacters.toString();
		return charString;
	}
}