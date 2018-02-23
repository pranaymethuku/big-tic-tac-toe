package coord;

/**
	 * Tuple representation for 2D coordinates.
	 * @author Pranay Methuku
	 *
	 */
	public class Coord2D {
		private int row;
		private int column;

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
