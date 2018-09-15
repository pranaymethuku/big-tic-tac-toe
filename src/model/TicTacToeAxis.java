package model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pranay Methuku
 *
 */
public class TicTacToeAxis {
	
	private Map<TicTacToePlayer, Integer> playerMark;
	
	private int size;
	
	public TicTacToeAxis(int size) {
		playerMark = new HashMap<>();
		this.size = size;
	}

	public int getPlayerMark(TicTacToePlayer player) {
		playerMark.putIfAbsent(player, 0);
		return playerMark.get(player);
	}

	public void incrementPlayerMarking(TicTacToePlayer player) {
		if (getPlayerMark(player) < size) {
			playerMark.replace(player, playerMark.get(player) + 1);
		}
	}
	
	public boolean isAxisIdentical() {
		boolean isAxisIdentical = false;
		for (TicTacToePlayer player : playerMark.keySet()) {
			isAxisIdentical |= (playerMark.get(player) == size);
		}
		return isAxisIdentical;
	}
}
