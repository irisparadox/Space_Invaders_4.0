package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

import java.util.Objects;

public abstract class Laser extends UCMWeapon{
	protected Move dir;

	public Laser(Position position, int healthPoints, int damage, Move direction, GameWorld game){
		super(position,healthPoints,damage,game);
		this.dir = direction;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Laser laser = (Laser) o;
		return dir == laser.dir && this.position.equals(laser.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dir, position);
	}

	@Override
	public void onDelete() {
		game.shootAvailableAgain();
	}

	/**
	 * Performs the movement of the Laser automatically.
	 */
	@Override
	public void automaticMove() {
		performMove(this.dir);
		if(outOfBounds()) {
			this.healthPoints = 0;
			this.onDelete();
		}
	}

	@Override
	public boolean receiveAttack(EnemyWeapon weapon){
		this.healthPoints -= weapon.getDamage();
		return isAlive();
	}

	@Override
	public void performMove(Move direction) {
		this.position = this.position.changePosition(direction);
	}

	@Override
	public boolean onCollision(GameItem other) {
		return other.isInPosition(this.position);
	}
}
