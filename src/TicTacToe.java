import java.util.Scanner;

public class TicTacToe {

	private int gridSize;
	private boolean isInputBoxWise;
	private static final int DEFAULT_GRID_SIZE = 3;
	private Player currentPlayer;

	private enum Player {
		X, O;
	}
	
	private class Coord2D {
		private int row;
		private int column;
		
		private Coord2D(int row, int column) {
			this.setRow(row);
			this.setColumn(column);
		}

		/**
		 * @return the row
		 */
		public int getRow() {
			return row;
		}

		/**
		 * @param row the row to set
		 */
		public void setRow(int row) {
			this.row = row;
		}

		/**
		 * @return the column
		 */
		public int getColumn() {
			return column;
		}

		/**
		 * @param column the column to set
		 */
		public void setColumn(int column) {
			this.column = column;
		}
		
		
	}

	private Player[][] grid;
	
	private void initNewGame(int gridSize) {
		this.grid = new Player[gridSize][gridSize];
		this.gridSize = gridSize;
		this.currentPlayer = Player.X;
	}
		

	public TicTacToe() {
		initNewGame(DEFAULT_GRID_SIZE);
	}

	public TicTacToe(int gridSize) {
		initNewGame(gridSize);
	}

	public void printState() {
		String placeHolder;
		for (int row = 0; row < this.grid.length; row++) {
			for (int column = 0; column < this.grid[0].length; column++) {
				placeHolder = "_";
				if (this.grid[row][column] != null) {
					placeHolder = this.grid[row][column].toString();
				}
				System.out.print("[" + placeHolder + "]");
			}
			System.out.println();
		}
	}

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

	private boolean isAnyDiagonalSame() {
		boolean isAnyDiagonalSame = this.grid[0][0] != null;
		for (int i = 0; i < this.gridSize - 1; i++) {
			isAnyDiagonalSame &= this.grid[i][i] == this.grid[i + 1][i + 1];
			if (!isAnyDiagonalSame) {
				break;
			}
		}
		if (!isAnyDiagonalSame) {
			isAnyDiagonalSame = this.grid[0][this.gridSize - 1] != null;
			for (int row = 0; row < this.gridSize - 1; row++) {
				for (int column = this.gridSize - 1; column > 0; column--)
				isAnyDiagonalSame &= this.grid[row][column] == this.grid[row + 1][column - 1];
				if (!isAnyDiagonalSame) {
					break;
				}
			}
		}
		return isAnyDiagonalSame;
	}

	public boolean isGameOver() {
		boolean isGameOver = false;
		for (int index = 0; index < this.gridSize; index++) {
			isGameOver |= this.isRowSame(index) || this.isColumnSame(index);
			if (isGameOver) {
				break;
			}
		}
		if (!isGameOver) {
			isGameOver |= this.isAnyDiagonalSame();
		}
		return isGameOver;
	}
	
	private Coord2D convertBoxToIndex(int boxNum) {
		int column = (boxNum % this.gridSize) - 1;
		int row = boxNum/this.gridSize;
		return new Coord2D(row, column);
	}
	
	public Coord2D getInputBoxNumber(Scanner in) {
		System.out.println("Player "+this.currentPlayer.toString()+"'s turn...");
		System.out.print("Choose box from 1 to "+this.gridSize*this.gridSize+": ");
		int playerChoice = in.nextInt();
		while (playerChoice < 1 && playerChoice > this.gridSize * this.gridSize) {
			System.out.println("ERROR: Invalid box!");
			System.err.print("Choose box from 1 to "+this.gridSize*this.gridSize+": ");
			playerChoice = in.nextInt();
		}
		return convertBoxToIndex(playerChoice);
	}
	
	private int getInputRowIndex(Scanner in) {
		System.out.print("Choose row number from 1 to "+this.gridSize+": ");
		int rowChoice = in.nextInt();
		while (rowChoice < 1 && rowChoice > this.gridSize) {
			System.err.println("ERROR: Invalid row!");
			System.out.print("Choose row number from 1 to "+this.gridSize+": ");
			rowChoice = in.nextInt();
		}
		return rowChoice - 1;
	}
	
	private int getInputColumnIndex(Scanner in) {
		System.out.print("Choose column number from 1 to "+this.gridSize+": ");
		int columnChoice = in.nextInt();
		while (columnChoice < 1 && columnChoice > this.gridSize) {
			System.err.println("ERROR: Invalid column!");
			System.out.print("Choose column number from 1 to "+this.gridSize+": ");
			columnChoice = in.nextInt();
		}
		return columnChoice - 1;
	}
	
	public Coord2D getInputIndex(Scanner in) {
		System.out.println("Player "+this.currentPlayer.toString()+"'s turn...");
		int rowIndex = getInputRowIndex(in);
		int columnIndex = getInputColumnIndex(in);
		return new Coord2D(rowIndex, columnIndex);
	}
	
	private int getInputGridSize(Scanner in) {
		System.out.print("Choose the grid size you want to play (>= 3): ");
		int gridSize = in.nextInt();
		while (gridSize < 3) {
			System.err.println("ERROR: Invalid grid size!");
			System.out.print("Choose the grid size you want to play (>= 3): ");
			gridSize = in.nextInt();
		}
		return gridSize;
	}
	
	public void setGridSize(Scanner in) {
		System.out.print("Do you want to play with the default grid size (3)? (Y/N): ");
		String inputGridSize = in.next();
		if (inputGridSize.equals("Y")) {
			this.initNewGame(DEFAULT_GRID_SIZE); 
		} else if (inputGridSize.equals("N")) {
			this.initNewGame(getInputGridSize(in)); 
		} else {
			System.err.println("ERROR: Enter only Y or N");
			setGridSize(in);
		}
	}
	
	public void setInputMode(Scanner in) {
		System.out.println("Do you want to input Box-wise or Index wise? (B/I): ");
		String inputMode = in.next();
		if (inputMode.equals("B")) {
			this.isInputBoxWise = true; 
		} else if (inputMode.equals("I")) {
			this.isInputBoxWise = false; 
		} else {
			System.err.println("ERROR: Enter only B or I");
			setInputMode(in);
		}
	}
	
	public void start(Scanner in) {
		System.out.println("Welcome to Tic-Tac-Toe!\n");
		System.out.println("First player gets X, Second player gets O.");
		this.setGridSize(in);
		this.setInputMode(in);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		game.printState();
	}

}
