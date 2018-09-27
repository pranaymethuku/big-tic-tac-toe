package util;

/**
 * Tuple representation for 2D coordinates.
 * @author Pranay Methuku
 *
 */
public class Coord2D {
	/**
	 * Field for the horizontal X coordinate.
	 */
	private int row;

	/**
	 * Field for the horizontal Y coordinate.
	 */
	private int column;
	
	/**
	 * Constructor for {@link Coord2D}
	 * 
	 * @param row
	 * @param column
	 */
	public Coord2D(int row, int column) {
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
