package model;

import util.Coord2D;

public class TicTacToeGrid {
		
	/**
	 * Grid size of the game.
	 */
	private int gridSize;
	
	/**
	 * Rows in the grid.
	 */
	private TicTacToeAxis[] rows;
	
	/**
	 * Columns in the grid.
	 */
	private TicTacToeAxis[] columns;
	
	/**
	 * Diagonal from top left to bottom right.
	 */
	private TicTacToeAxis leftDiagonal;

	/**
	 * Diagonal from top right to bottom left.
	 */
	private TicTacToeAxis rightDiagonal;
	
	/**
	 * Constructor for TicTacToeGrid.
	 * @param gridSize
	 */
	public TicTacToeGrid(int gridSize) {
		this.gridSize = gridSize;
		this.rows = new TicTacToeAxis[gridSize];
		this.columns = new TicTacToeAxis[gridSize];
		this.leftDiagonal = new TicTacToeAxis(gridSize);
		this.rightDiagonal = new TicTacToeAxis(gridSize);
		for (int i = 0; i < gridSize; i++) {
			this.rows[i] = new TicTacToeAxis(gridSize);
			this.columns[i] = new TicTacToeAxis(gridSize);
		}
	}
	
	/**
	 * @param gridItem
	 */
	public void setGridItem (Coord2D gridItem, TicTacToePlayer player) {
		this.getRow(gridItem.getRow()).incrementPlayerMarking(player);
		this.getColumn(gridItem.getColumn()).incrementPlayerMarking(player);
		if (gridItem.getRow() == gridItem.getColumn()) {
			this.getLeftDiagonal().incrementPlayerMarking(player);
		}
		if (gridItem.getRow() + gridItem.getColumn() == this.gridSize - 1) {
			this.getRightDiagonal().incrementPlayerMarking(player);
		}
	}
	
	/**
	 * @param gridItem
	 * @return Row object at index {@code row}
	 */
	public TicTacToeAxis getRow (int row) {
		return this.rows[row];
	}
	
	/**
	 * @param gridItem
	 * @return Column object at index {@code column}
	 */
	public TicTacToeAxis getColumn (int column) {
		return this.columns[column];
	}
	
	/**
	 * @param gridItem
	 * @return left diagonal object of grid.
	 */
	public TicTacToeAxis getLeftDiagonal () {
		return this.leftDiagonal;
	}
	
	/**
	 * @param gridItem
	 * @return right diagonal object of grid.
	 */
	public TicTacToeAxis getRightDiagonal () {
		return this.rightDiagonal;
	}
	
}
