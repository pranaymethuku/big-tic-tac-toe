import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToe {

	private int gridSize;
	private static final int DEFAULT_GRID_SIZE = 3;
	private Player currentPlayer;

	private enum Player {
		X, O;
	}

	private Player[][] grid;

//	private class Player {
//		Players identifier;
//
//		private Player(Players identifier) {
//			this.identifier = identifier;
//		}
//	}
	
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

	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		TicTacToe game = new TicTacToe();
		game.printState();
	}

}
