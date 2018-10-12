package model;

import java.awt.Color;

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
	 * Field for a Player color.
	 */
	private Color color;

	/**
	 * @return the color of the player
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

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
	public TicTacToePlayer (String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
}
