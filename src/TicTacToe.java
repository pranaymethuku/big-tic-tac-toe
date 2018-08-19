import controller.TicTacToeController;
import model.TicTacToeModel;
import view.TicTacToeView;

/**
 * Classic Tic-Tac-Toe game with dynamic functionalities,
 * parameterized by Grid Size.
 * @author Pranay Methuku
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
		int gridSize = 7;
		TicTacToeModel model = new TicTacToeModel(gridSize);
		TicTacToeView view = new TicTacToeView(gridSize);
		TicTacToeController controller = new TicTacToeController(model, view);
		
		view.registerController(controller);
	}

}
