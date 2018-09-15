package controller;

import model.TicTacToeModel;
import model.TicTacToePlayer;
import utilities.Coord2D;
import view.TicTacToeView;

/**
 * @author Pranay Methuku
 *
 */
public class TicTacToeController {
	
	/**
	 * Model registered with {@code this}.
	 */
	private TicTacToeModel model;
	
	/**
	 * View registered with {@code this}.
	 */
	private TicTacToeView view;
	
	/**
	 * @param model
	 * @param view
	 */
	public TicTacToeController (TicTacToeModel model, TicTacToeView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * @param coord
	 */
	private void updateGridItemView (Coord2D coord, TicTacToePlayer player) {
		this.view.setGridItemView(coord, player.getName(), player.getColor());
	}

	/**
	 * @param coord
	 */
	public void processGridItemClickEvent(Coord2D coord) {
		if (this.model.getGridItem(coord) == null) {
			TicTacToePlayer player = this.model.setGridItem(coord);
			updateGridItemView(coord, player);
			updateGameTerminationState(coord);
			this.model.toggleCurrentPlayer();
		}
	}	

	/**
	 * @param lastTurn
	 */
	private void updateGameTerminationState(Coord2D lastTurn) {
		this.model.updateGameOver(lastTurn);
		this.model.updateGameDraw();
		if (this.model.isGameOver()) {
			this.view.renderGameOver(this.model.getCurrentPlayer().getName());
		} else if (this.model.isGameDraw()){
			this.view.renderGameDraw();
		}
	}
	
}
