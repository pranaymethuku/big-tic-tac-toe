package model;

import utilities.Coord2D;

public class TicTacToeGrid {
	
	private TicTacToePlayer[][] grid;
	
	private TicTacToeAxis[] rows;
	
	private TicTacToeAxis[] columns;
	
	private TicTacToeAxis leftDiagonal;

	private TicTacToeAxis rightDiagonal;
	
	public TicTacToeGrid(int gridSize) {
		this.grid = new TicTacToePlayer[gridSize][gridSize];
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
	 * @return
	 */
	public TicTacToePlayer getGridItem (Coord2D gridItem) {
		return this.grid[gridItem.getRow()][gridItem.getColumn()];
	}
	
	/**
	 * @param gridItem
	 * @return
	 */
	public void setGridItem (Coord2D gridItem, TicTacToePlayer player) {
		this.grid[gridItem.getRow()][gridItem.getColumn()] = player;
		this.getRow(gridItem.getRow()).incrementPlayerMarking(player);
		this.getColumn(gridItem.getColumn()).incrementPlayerMarking(player);
		if (gridItem.getRow() == gridItem.getColumn()) {
			this.getLeftDiagonal().incrementPlayerMarking(player);
		}
		if (gridItem.getRow() + gridItem.getColumn() == this.grid.length - 1) {
			this.getRightDiagonal().incrementPlayerMarking(player);
		}
	}
	
	/**
	 * @param gridItem
	 * @return
	 */
	public TicTacToeAxis getRow (int row) {
		return this.rows[row];
	}
	
	/**
	 * @param gridItem
	 * @return
	 */
	public TicTacToeAxis getColumn (int column) {
		return this.columns[column];
	}
	
	/**
	 * @param gridItem
	 * @return
	 */
	public TicTacToeAxis getLeftDiagonal () {
		return this.leftDiagonal;
	}
	
	/**
	 * @param gridItem
	 * @return
	 */
	public TicTacToeAxis getRightDiagonal () {
		return this.rightDiagonal;
	}
	
}
