import java.util.Scanner;

/**
 * @author Pranay Methuku
 *
 */
public class TicTacToe {

	/**
	 * 
	 */
	private int gridSize;
	/**
	 * 
	 */
	private boolean isInputBoxWise;
	/**
	 * 
	 */
	private static final int DEFAULT_GRID_SIZE = 3;
	/**
	 * 
	 */
	private Player currentPlayer;

	/**
	 * @author Pranay Methuku
	 *
	 */
	private enum Player {
		X, O;
	}

	/**
	 * @author Pranay Methuku
	 *
	 */
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
		 * @param row
		 *            the row to set
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
		 * @param column
		 *            the column to set
		 */
		public void setColumn(int column) {
			this.column = column;
		}

	}

	/**
	 * 
	 */
	private Player[][] grid;

	/**
	 * @param gridSize
	 */
	private void initNewGame(int gridSize) {
		this.grid = new Player[gridSize][gridSize];
		this.gridSize = gridSize;
		this.currentPlayer = Player.X;
	}

	/**
	 * 
	 */
	public TicTacToe() {
		initNewGame(DEFAULT_GRID_SIZE);
	}

	/**
	 * @param gridSize
	 */
	public TicTacToe(int gridSize) {
		initNewGame(gridSize);
	}

	/**
	 * 
	 */
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

	/**
	 * @return
	 */
	public boolean isGameDraw() {
		boolean isGameDraw = true;
		if (isGameOver()) {
			return false;
		} else {
			for (int row = 0; row < this.gridSize; row++) {
				for (int column = 0; column < this.gridSize; column++) {
					isGameDraw &= this.grid[row][column] != null;
					if (!isGameDraw) {
						break;
					}
				}
				if (!isGameDraw) {
					break;
				}
			}
		}
		return isGameDraw;
	}

	/**
	 * @param boxNum
	 * @return
	 */
	private Coord2D convertBoxToIndex(int boxNum) {
		int row = boxNum / this.gridSize;
		int column = (boxNum % this.gridSize) - 1;
		if (column == -1) {
			column += this.gridSize;
			row -= 1;
		}
		return new Coord2D(row, column);
	}

	/**
	 * @param in
	 * @return
	 */
	private Coord2D getInputBoxNumber(Scanner in) {
		System.out.println("Player " + this.currentPlayer.toString() + "'s turn...");
		System.out.print("Choose box from 1 to " + this.gridSize * this.gridSize + ": ");
		int playerChoice = in.nextInt();
		while (playerChoice < 1 && playerChoice > this.gridSize * this.gridSize) {
			System.out.println("ERROR: Invalid box!");
			System.err.print("Choose box from 1 to " + this.gridSize * this.gridSize + ": ");
			playerChoice = in.nextInt();
		}
		return convertBoxToIndex(playerChoice);
	}

	/**
	 * @param in
	 * @return
	 */
	private int getInputRowIndex(Scanner in) {
		System.out.print("Choose row number from 1 to " + this.gridSize + ": ");
		int rowChoice = in.nextInt();
		while (rowChoice < 1 && rowChoice > this.gridSize) {
			System.err.println("ERROR: Invalid row!");
			System.out.print("Choose row number from 1 to " + this.gridSize + ": ");
			rowChoice = in.nextInt();
		}
		return rowChoice - 1;
	}

	/**
	 * @param in
	 * @return
	 */
	private int getInputColumnIndex(Scanner in) {
		System.out.print("Choose column number from 1 to " + this.gridSize + ": ");
		int columnChoice = in.nextInt();
		while (columnChoice < 1 && columnChoice > this.gridSize) {
			System.err.println("ERROR: Invalid column!");
			System.out.print("Choose column number from 1 to " + this.gridSize + ": ");
			columnChoice = in.nextInt();
		}
		return columnChoice - 1;
	}

	/**
	 * @param in
	 * @return
	 */
	private Coord2D getInputIndex(Scanner in) {
		System.out.println("Player " + this.currentPlayer.toString() + "'s turn...");
		int rowIndex = getInputRowIndex(in);
		int columnIndex = getInputColumnIndex(in);
		return new Coord2D(rowIndex, columnIndex);
	}

	/**
	 * @param in
	 * @return
	 */
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

	/**
	 * @param in
	 */
	public void setGridSize(Scanner in) {
		System.out.print("Do you want to play with the default grid size (3)? (Y/N): ");
		String inputGridSize = in.next();
		if (inputGridSize.equals("Y") || inputGridSize.equals("y")) {
			this.initNewGame(DEFAULT_GRID_SIZE);
		} else if (inputGridSize.equals("N") || inputGridSize.equals("n")) {
			this.initNewGame(getInputGridSize(in));
		} else {
			System.err.println("ERROR: Enter only Y or N");
			setGridSize(in);
		}
	}

	/**
	 * @param in
	 */
	public void setInputMode(Scanner in) {
		System.out.print("Do you want to input Box-wise or Index wise? (B/I): ");
		String inputMode = in.next();
		if (inputMode.equals("B") || inputMode.equals("b")) {
			this.isInputBoxWise = true;
		} else if (inputMode.equals("I") || inputMode.equals("i")) {
			this.isInputBoxWise = false;
		} else {
			System.err.println("ERROR: Enter only B or I");
			setInputMode(in);
		}
	}

	/**
	 * @param in
	 * @return
	 */
	public Coord2D getPlayerInput(Scanner in) {
		Coord2D playerInput;
		if (this.isInputBoxWise) {
			playerInput = this.getInputBoxNumber(in);
		} else {
			playerInput = this.getInputIndex(in);
		}
		return playerInput;
	}

	/**
	 * 
	 */
	public void toggleCurrentPlayer() {
		if (this.currentPlayer == Player.X) {
			this.currentPlayer = Player.O;
		} else {
			this.currentPlayer = Player.X;
		}
	}

	/**
	 * @param coordinate
	 * @param in
	 */
	public void processInput(Coord2D coordinate, Scanner in) {
		if (this.grid[coordinate.getRow()][coordinate.getColumn()] == null) {
			this.grid[coordinate.getRow()][coordinate.getColumn()] = this.currentPlayer;
			this.toggleCurrentPlayer();
		} else {
			System.err.println("ERROR: Cannot overwrite on other player's mark!");
			this.processInput(this.getPlayerInput(in), in);
		}
	}

	/**
	 * @param in
	 */
	public void start(Scanner in) {
		System.out.println("Welcome to Tic-Tac-Toe!\n");
		System.out.println("First player gets X, Second player gets O.");
		this.setGridSize(in);
		this.setInputMode(in);
		this.printState();
		boolean isGameOver = this.isGameOver();
		boolean isGameDraw = this.isGameDraw();
		while (!isGameOver && !isGameDraw) {
			this.processInput(this.getPlayerInput(in), in);
			this.printState();
			isGameOver = this.isGameOver();
			isGameDraw = this.isGameDraw();
		}
		this.toggleCurrentPlayer();
		if (isGameDraw) {
			System.out.println("GAME DRAW!");
		} else {
			System.out.println("GAME OVER! Player " + this.currentPlayer.toString() + " wins!");
		}
		System.exit(0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		game.start(in);
	}

}
