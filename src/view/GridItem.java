package view;
import javax.swing.*;

import utilities.Coord2D;

public class GridItem extends JButton {

	/**
	 * Auto-Generated Serializable ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Position of {@code this} in the Grid.
	 */
	private Coord2D pos; 

	/**
	 * Constructor for GridItem.
	 * 
	 * @param itemText
	 * @param coord
	 */
	public GridItem(String itemText, Coord2D coord) {
		super(itemText);
		this.setPos(coord);
	}

	/**
	 * @return the pos
	 */
	public Coord2D getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Coord2D pos) {
		this.pos = pos;
	}
	
}
