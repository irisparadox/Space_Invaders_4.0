package tp1.logic;

import tp1.view.Messages;

import java.util.Objects;

/**
 * Immutable class to encapsulate and manipulate positions in the game board.
 */
public final class Position {

	private final int col;
	private final int row;
	/**
	 * Initializes a Position to coordinates x = 0 and y = 0.
	 */
	public Position(){
		this.col = 0;
		this.row = 0;
	}

	/**
	 * Initializes a Position given its x and y coordinates.
	 * @param i the x coordinate
	 * @param j the y coordinate
	 */
	public Position(int i, int j){
		this.col = i;
		this.row = j;
	}

	/**
	 * Converts the x and y coordinates to a string.
	 * @return String: (x, y)
	 */
	@Override
	public String toString(){
		return Messages.POSITION.formatted(this.col,this.row);
	}

	/**
	 * Equals override. Checks if given Object has the same x coordinate and y coordinate.
	 * @param o other object
	 * @return true or false
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return col == position.col && row == position.row;
	}

	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}

	/**
	 * Changes the position given a Move.
	 * @param move the direction
	 * @return the desired new Position
	 */
	public Position changePosition(Move move){
		return new Position(this.col + move.getX(), this.row + move.getY());
	}

	/**
	 * Checks if position is in border.
	 * @return true or false
	 */
	public boolean borderBound(){
		return (this.col == 0 || this.col == Game.DIM_X-1);
	}

	/**
	 * Checks if position is in lower border.
	 * @return true or false
	 */
	public boolean lowerBound(){
		return (this.row == Game.DIM_Y-1);
	}

	/**
	 * Checks if position is out of bounds.
	 * @return true or false
	 */
	public boolean invalidPosition(){
		return !(this.col >= 0 && this.col < Game.DIM_X &&
				 this.row >= 0 && this.row < Game.DIM_Y);
	}
}
