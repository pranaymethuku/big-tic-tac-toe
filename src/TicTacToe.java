import controller.TicTacToeController;
import model.TicTacToeModel;
import view.TicTacToeView;

/**
 * Classic Tic-Tac-Toe game with dynamic functionalities,
 * parameterized by Grid Size.
 * @author Pranay Methuku
 * @since February 11, 2018 - 
 * 		Dynamic sized CLI Tic Tac Toe 
 * 		Very inefficient (O(n^2) overall space, O(n^2) time after each turn) for large grid size
 * @updated August 18, 2018 -
 * 		Converted interface to desktop app, this time using MVC pattern
 * 		Improved time complexity to O(n) after each turn, retaining space.
 *
 * @updated September 15, 2018 -
 * 		Improved time complexity to O(1) after each turn, retaining space.
 *
 */
public final class TicTacToe {
	
	/**
	 * Private constructor so this utility class cannot be instantiated.
	 */
	private TicTacToe () {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int gridSize = 3;
		TicTacToeModel model = new TicTacToeModel(gridSize);
		TicTacToeView view = new TicTacToeView(gridSize);
		TicTacToeController controller = new TicTacToeController(model, view);
		
		view.registerController(controller);
	}

}
