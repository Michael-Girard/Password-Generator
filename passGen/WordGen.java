package passGen;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class securely accesses random words from 
 * online dictionaries to be used in passwords
 */
public class WordGen {
	private int charLnth = 0;
	private String words;
	
	WordGen(){
		
	}
	
	public int getCharLnth() {
		return charLnth;
	}

	public void setCharLnth(int charLnth) {
		this.charLnth = charLnth;
	}
	
	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}
}
