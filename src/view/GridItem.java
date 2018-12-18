package view;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.*;
import util.Coord2D;

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
		this.setFont(new Font("Serif",Font.BOLD,30));
		this.setBackground(new Color(40, 40, 40));
		this.setPos(coord);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
