package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.view.Messages;


public class Ufo extends EnemyShip {

	private boolean enabled;
	private Move direction;
	private AlienManager manager;
	private final Position INITIAL_POSITION = new Position(Game.DIM_X, 0);

	public Ufo(Position position, GameWorld game, AlienManager manager){
		super(position, 1, game);
		this.direction = Move.LEFT;
		this.manager = manager;
	}

	public void computerAction() {
		if(!enabled && canGenerateRandomUfo() && !game.canShockwave())
			enable();
		else if(this.position.invalidPosition())
			this.enabled = false;

		this.manager.setUfoEnabled(true);
	}

	private void enable() {
		this.position = INITIAL_POSITION;
		this.enabled = true;
	}

	@Override
	public String toString(){
		String str = " ";
		if(this.enabled)
			str = Messages.status(this.getSymbol(), this.healthPoints);

		return str;
	}

	@Override
	protected String getSymbol() {
		return Messages.UFO_SYMBOL;
	}

	@Override
	public void onDelete() {
		this.game.addPoints(25);
		game.enableShockwave();
	}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		if(enabled) {
			this.enabled = false;
			onDelete();
		}

		return enabled;
	}

	@Override
	public boolean isAlive(){
		return true;
	}

	/**
	 * Performs the movement of the GameObject automatically.
	 */
	@Override
	public void automaticMove() {
		if(enabled) {
			performMove(this.direction);
		}
	}

	@Override
	public void performMove(Move direction) {
		this.position = this.position.changePosition(direction);
	}

	@Override
	public boolean isInBorder() {
		return this.position.invalidPosition();
	}

	/**
	 * Checks if the game should generate an ufo.
	 *
	 * @return <code>true</code> if an ufo should be generated.
	 */
	private boolean canGenerateRandomUfo(){
		return this.manager.isUfoEnabled() && game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
	}

}
