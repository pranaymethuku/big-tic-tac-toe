package model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pranay Methuku
 * 
 * TicTacToeAxis represents a generic axis in a grid, (for example, a row,
 *  a column, or a diagonal)
 *
 */
public class TicTacToeAxis {
	
	
	/**
	 * Map for holding instances of player in this.
	 */
	private Map<TicTacToePlayer, Integer> playerMark;
	
	/**
	 * Size of axis.
	 */
	private int size;
	
	/**
	 * Constructor for TicTacToeAxis.
	 * @param size
	 */
	public TicTacToeAxis(int size) {
		playerMark = new HashMap<>();
		this.size = size;
	}

	/**
	 * Gets number of instances of {@code player} on the axis.
	 * @param player
	 * @return instances of {@code player} on the axis
	 */
	public int getPlayerMark(TicTacToePlayer player) {
		playerMark.putIfAbsent(player, 0);
		return playerMark.get(player);
	}

	/**
	 * Given the player, increments the player instances for this.
	 * @param player
	 */
	public void incrementPlayerMarking(TicTacToePlayer player) {
		if (getPlayerMark(player) < size) {
			playerMark.replace(player, playerMark.get(player) + 1);
		}
	}
	
	/**
	 * @return whether this is completely populated with one player.
	 */
	public boolean isAxisIdentical() {
		boolean isAxisIdentical = false;
		for (TicTacToePlayer player : playerMark.keySet()) {
			isAxisIdentical |= (playerMark.get(player) == size);
		}
		return isAxisIdentical;
	}
}
