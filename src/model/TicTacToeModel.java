package model;

import java.awt.Color;

import util.Coord2D;

/**
 * Classic Tic-Tac-Toe game with dynamic functionalities,
 * parameterized by Grid Size.
 * @author Pranay Methuku
 * @since February 11, 2018
 * @updated August 18, 2018
 * @updated September 15, 2018
 * @updated September 24, 2018
 *
 */
public class TicTacToeModel {
	
	/**
	 * Field for default grid size of the game.
	 */
	private static final int DEFAULT_GRID_SIZE = 3;

	/**
	 * Grid size of the game.
	 */
	private int gridSize;
	
	/**
	 * Game end condition.
	 */
	private boolean isGameOver;
	
	/**
	 * Game end condition.
	 */
	private boolean isGameDraw;
	
	/**
	 * Counter for the number of turns played yet.
	 */
	private int numOfTurns;
	
	/**
	 * Matrix representation of the game.
	 */
	private TicTacToeGrid grid;
	
	/**
	 * Stores current player information.
	 */
	private TicTacToePlayer currentPlayer;
	
	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(TicTacToePlayer currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	/**
	 * @return Current Player in the game
	 */
	public TicTacToePlayer getCurrentPlayer() {
		return currentPlayer;
	}	

	/**
	 * Stores both players' information.
	 */
	private TicTacToePlayer player1;
	private TicTacToePlayer player2;
	
	/**
	 * @param gridSize
	 */
	private void setupModel(int gridSize) {
		this.grid = new TicTacToeGrid(gridSize);
		this.gridSize = gridSize;
		this.player1 = new TicTacToePlayer("X", new Color(95, 95, 95));
		this.player2 = new TicTacToePlayer("O", Color.LIGHT_GRAY);
		this.currentPlayer = this.player1;
		this.isGameOver  = false;
		this.isGameDraw = false;
		this.numOfTurns = 0;		
	}

	/**
	 * No-argument constructor.
	 */
	public TicTacToeModel() {
		setupModel(DEFAULT_GRID_SIZE);
	}

	/**
	 * Constructor with grid size argument.
	 * @param gridSize
	 */
	public TicTacToeModel(int gridSize) {
		setupModel(gridSize);
	}
	
	/**
	 * @param gridItem
	 * @return the current player object for that turn
	 */
	public TicTacToePlayer setGridItem (Coord2D gridItem) {
		this.grid.setGridItem(gridItem, this.currentPlayer);
		this.numOfTurns++;
		return this.currentPlayer;
	}

	/**
	 * @param row
	 * @return whether row with index {@code row} is completely
	 * 	populated with all same players
	 */
	private boolean isRowSame(int row) {
		return this.grid.getRow(row).isAxisIdentical();
	}

	/**
	 * @param column
	 * @return whether column with index {@code column} is completely
	 * 	populated with all same players
	 */
	private boolean isColumnSame(int column) {
		return this.grid.getColumn(column).isAxisIdentical();
	}

	/**
	 * @return whether diagonal going from top left 
	 * 	to bottom right is completely populated with all same players
	 */
	private boolean isLeftDiagonalSame() {
		return this.grid.getLeftDiagonal().isAxisIdentical();
	}

	/**
	 * @return whether diagonal going from top right 
	 * 	to bottom left is completely populated with all same players
	 */
	private boolean isRightDiagonalSame() {
		return this.grid.getRightDiagonal().isAxisIdentical();
	}

	/**
	 * @return whether either diagonal is completely populated with
	 * 	all same players
	 */
	private boolean isAnyDiagonalSame() {
		return this.isLeftDiagonalSame() || this.isRightDiagonalSame();
	}

	/**
	 * Updates the game termination state after last turn is completed and someone wins.
	 */
	public void updateGameOver(Coord2D lastTurn) {
		this.isGameOver |= this.isRowSame(lastTurn.getRow()) || this.isColumnSame(lastTurn.getColumn());
		if (!this.isGameOver && (lastTurn.getRow() == lastTurn.getColumn() || 
				lastTurn.getRow() + lastTurn.getColumn() == this.gridSize - 1)) {
			this.isGameOver |= this.isAnyDiagonalSame();
		}
	}

	/**
	 * Updates the game termination state after last turn is completed and its a draw.
	 */
	public void updateGameDraw() {
		this.isGameDraw |=  (this.numOfTurns == this.gridSize * this.gridSize);
	}

	/**
	 * Switches players after each turn.
	 */
	public void toggleCurrentPlayer() {
		if (this.currentPlayer == this.player1) {
			this.currentPlayer = this.player2;
		} else {
			this.currentPlayer = this.player1;
		}
	}
	
	/**
	 * @return whether game is over and someone has won.
	 */
	public boolean isGameOver() {
		return this.isGameOver;
	}
	
	/**
	 * @return whether game is over and it is a draw.
	 */
	public boolean isGameDraw() {
		return this.isGameDraw;
	}

}
