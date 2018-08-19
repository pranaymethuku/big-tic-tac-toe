package model;

import utilities.Coord2D;

/**
 * Classic Tic-Tac-Toe game with dynamic functionalities,
 * parameterized by Grid Size.
 * @author Pranay Methuku
 * @since February 11, 2018
 * @updated August 18, 2018
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
	 * Stores current player information.
	 */
	private TicTacToePlayer otherPlayer;	

	/**
	 * @return the otherPlayer
	 */
	public TicTacToePlayer getOtherPlayer() {
		return otherPlayer;
	}

	/**
	 * @param otherPlayer the otherPlayer to set
	 */
	public void setOtherPlayer(TicTacToePlayer otherPlayer) {
		this.otherPlayer = otherPlayer;
	}

	/**
	 * Matrix representation of the game.
	 */
	private TicTacToePlayer[][] grid;

	/**
	 * @param gridSize
	 */
	private void setupModel(int gridSize) {
		this.grid = new TicTacToePlayer[gridSize][gridSize];
		this.gridSize = gridSize;
		this.currentPlayer = new TicTacToePlayer("X");
		this.otherPlayer = new TicTacToePlayer("O");
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
	 */
	public void updateGridItem (Coord2D gridItem) {
		this.grid[gridItem.getRow()][gridItem.getColumn()] = this.currentPlayer;
		this.numOfTurns++;
	}
	
	/**
	 * @param gridItem
	 * @return
	 */
	public TicTacToePlayer readGridItem (Coord2D gridItem) {
		return this.grid[gridItem.getRow()][gridItem.getColumn()];
	}

	/**
	 * @param row
	 * @return
	 */
	private boolean isRowSame(int row) {
		boolean isRowSame = this.grid[row][0] != null;
		for (int column = 0; column < this.gridSize - 1; column++) {
			isRowSame &= (this.grid[row][column] == this.grid[row][column + 1]);
			if (!isRowSame) {
				break;
			}
		}
		return isRowSame;
	}

	/**
	 * @param column
	 * @return
	 */
	private boolean isColumnSame(int column) {
		boolean isColumnSame = this.grid[0][column] != null;
		for (int row = 0; row < this.gridSize - 1; row++) {
			isColumnSame &= (this.grid[row][column] == this.grid[row + 1][column]);
			if (!isColumnSame) {
				break;
			}
		}
		return isColumnSame;
	}

	/**
	 * @return
	 */
	private boolean isLeftDiagonalSame() {
		boolean isLeftDiagonalSame = this.grid[0][0] != null;
		for (int i = 0; i < this.gridSize - 1; i++) {
			isLeftDiagonalSame &= this.grid[i][i] == this.grid[i + 1][i + 1];
			if (!isLeftDiagonalSame) {
				break;
			}
		}
		return isLeftDiagonalSame;
	}

	/**
	 * @return
	 */
	private boolean isRightDiagonalSame() {
		boolean isRightDiagonalSame = this.grid[0][this.gridSize - 1] != null;
		int column = this.gridSize - 1;
		for (int row = 0; row < this.gridSize - 1; row++) {
			isRightDiagonalSame &= this.grid[row][column] == this.grid[row + 1][column - 1];
			column--;
			if (!isRightDiagonalSame) {
				break;
			}
		}
		return isRightDiagonalSame;
	}

	/**
	 * @return
	 */
	private boolean isAnyDiagonalSame() {
		return this.isLeftDiagonalSame() || this.isRightDiagonalSame();
	}

	/**
	 * @return
	 */
	public void updateGameOver(Coord2D lastTurn) {
		this.isGameOver |= this.isRowSame(lastTurn.getRow()) || this.isColumnSame(lastTurn.getColumn()) || this.isAnyDiagonalSame();
	}

	/**
	 * @return
	 */
	public void updateGameDraw() {
		this.isGameDraw |=  (this.numOfTurns == this.gridSize * this.gridSize);
	}

	/**
	 * 
	 */
	public void toggleCurrentPlayer() {
		TicTacToePlayer temp = this.currentPlayer;
		this.currentPlayer = this.otherPlayer;
		this.otherPlayer = temp;
	}
	
	/**
	 * @return
	 */
	public boolean isGameOver() {
		return this.isGameOver;
	}
	
	/**
	 * @return
	 */
	public boolean isGameDraw() {
		return this.isGameDraw;
	}

}
