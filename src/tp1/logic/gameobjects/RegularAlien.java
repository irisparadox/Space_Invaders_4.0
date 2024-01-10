package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * Regular Alien Entity
 * <p>
 * Extends: {@link AlienEntity}
 */
public class RegularAlien extends AlienEntity {
	private static final int LIFE = 2;

	/** Empty constructor for RegularAlien.
	 */
	protected RegularAlien(){
		super(null, 0, null, null);
	}
	/**
	 * Initializes the Regular Alien entity given a position. Note that its health points are constant
	 * in its initialization and will not be considered in this constructor.
	 * @param position The position of the entity.
	 */
	public RegularAlien(Position position, GameWorld game, AlienManager manager){
		super(position,LIFE,game, manager);
	}

	@Override
	public AlienEntity copy(Position position, GameWorld game, AlienManager manager){
		return new RegularAlien(position, game, manager);
	}

	@Override
	public String getSymbol() {
		return Messages.REGULAR_ALIEN_SYMBOL;
	}

	@Override
	public void onDelete() {
		this.game.addPoints(5);
		this.manager.onAlienKill();
	}
}