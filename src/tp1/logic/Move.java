package tp1.logic;

import tp1.view.Messages;

/**
 * Represents the allowed movements in the game
 *
 */
public enum Move {
	LEFT(-1,0), LLEFT(-2,0), RIGHT(1,0), RRIGHT(2,0), DOWN(0,1), UP(0,-1), LUP(-1,-1), RUP(1,-1), NONE(0,0);

	private int x;
	private int y;

	private Move(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static Move valueOfIgnoreCase(String param) throws IllegalArgumentException{
		for(Move move : Move.values()){
			if(move.name().equalsIgnoreCase(param))
				return move;
		}
		throw new IllegalArgumentException(Messages.INCORRECT_MOVE_MESSAGE.formatted(param));
	}

	public Move pingPong(){
		Move desiredDirection = Move.LEFT;
		if(this.equals(Move.LEFT))
			desiredDirection = Move.RIGHT;

		return desiredDirection;
	}
}
