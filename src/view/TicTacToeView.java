/**
 * 
 */
package view;
import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import controller.TicTacToeController;
import view.GridItem;
import utilities.Coord2D;

/**
 * @author Pranay Methuku
 *
 */
public class TicTacToeView implements ActionListener {
	
	/**
	 * Main frame/window of the game.
	 */
	private JFrame mainFrame;
	
	/**
	 * 2 dimensional grid containing GridItem objects of the game.
	 */
	private GridItem[][] grid;
	
	/**
	 * Controller registered with {@code this}.
	 */
	private TicTacToeController controller;

	/**
	 * Constructor for the GUI of the game.
	 * 
	 * @param n
	 * 			Dimensions of the game grid.
	 */
	public TicTacToeView (int n) {
		prepareGUI(n);
		activate();
	}
	
	/**
	 * Hooks up {@code this} to {@code controller}
	 * 
	 * @param controller
	 * 					MVC Controller for {@code this}
	 */
	public void registerController(TicTacToeController controller) {
		this.controller = controller;
	}
	
	/**
	 * @param n
	 */
	private void prepareGUI (int n) {
		mainFrame = new JFrame("Tic Tac Toe");
		mainFrame.setSize(400, 400);
		if (n >= 7) {
			mainFrame.setSize(600, 600);
		}
		mainFrame.setLayout(new GridLayout(n, n));
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	    }); 
		grid = new GridItem[n][n];
		
		for (int row = 0; row < n; row++) {
			for (int column = 0; column < n; column++) {
				grid[row][column] = new GridItem("", new Coord2D(row, column));
				grid[row][column].addActionListener(this);
				mainFrame.add(grid[row][column]);
			}
		}
	}
	
	/**
	 * Updates a given GridItem
	 * 
	 * @param coord
	 * @param text
	 */
	public void setGridItemView(Coord2D coord, String text, Color color) {
		grid[coord.getRow()][coord.getColumn()].setText(text);
		grid[coord.getRow()][coord.getColumn()].setBackground(color);
	}
	
	/**
	 * Set the main frame to be visible.
	 */
	private void activate() {
		mainFrame.setVisible(true);
	}
	
	/**
	 * 
	 */
	public void renderGameOver(String playerName) {
		JOptionPane.showMessageDialog(null, "GAME OVER! Player " + playerName + " wins!");
		System.exit(0);
	}
	
	/**
	 * 
	 */
	public void renderGameDraw() {
		JOptionPane.showMessageDialog(null, "GAME DRAW!");
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (GridItem.class.isInstance(source)) {
			this.controller.processGridItemClickEvent(((GridItem) source).getPos());
		}
	}
}
