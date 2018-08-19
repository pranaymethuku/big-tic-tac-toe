package model;

/**
 * @author Pranay Methuku
 *
 */
public class TicTacToePlayer {
	
	/**
	 * Field for a Player object's name.
	 */
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Constructor for TicTacToePlayer.
	 * 
	 * @param name
	 * 			Name of the Player object
	 */
	public TicTacToePlayer (String name) {
		this.name = name;
	}
	
}
