/**
 * This class is used to store word, definition and ID of the vocabulary
 * inputted
 * 
 * @author Kimhuibeom
 *
 */
public class Vocabulary {
	private String word;
	private String definition;
	private int primaryID;
	/*
	 * public Vocabulary(String word, String definition, int primaryID) { this.word
	 * = word; this.definition = definition; this.primaryID = primaryID; }
	 */

	public void setWord(String word) {
		this.word = word;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public void setPrimaryID(int primaryID) {
		this.primaryID = primaryID;
	}

	public String getWord() {
		return word;
	}

	public String getDefinition() {
		return definition;
	}

	public int getID() {
		return primaryID;
	}
}
