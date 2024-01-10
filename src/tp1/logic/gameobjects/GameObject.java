package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {
    protected Position position;
    protected int healthPoints;
    protected GameWorld game;

    /**
     * Initializes a GameObject given its position, health points and game.
     * @param position the position of the GameObject
     * @param healthPoints the health of the GameObject
     * @param game the game where the GameObject lies
     */
    public GameObject(Position position, int healthPoints, GameWorld game){
        this.position = position;
        this.healthPoints = healthPoints;
        this.game = game;
    }

    /**
     * Performs an attack over another GameObject.
     *
     * @param other the other GameObject
     * @return attack could be performed
     */
    @Override
    public boolean performAttack(GameItem other) {
        return false;
    }

    /**
     * Receives an attack from an EnemyWeapon.
     *
     * @param weapon the enemy weapon
     * @return attack could be received
     */
    @Override
    public boolean receiveAttack(EnemyWeapon weapon) {
        return false;
    }

    /**
     * Receives an attack from a UCMWeapon.
     *
     * @param weapon the UCMWeapon.
     * @return attack could be received
     */
    @Override
    public boolean receiveAttack(UCMWeapon weapon) {
        return false;
    }

    @Override
    public boolean receiveAttack(ZombieAlien zombieAlien){
        return false;
    }

    /**
     * Checks whether GameObject is alive.
     *
     * @return true or false
     */
    @Override
    public boolean isAlive() {
        return this.healthPoints > 0;
    }

    /**
     * Checks if GameObject is placed at a given position.
     *
     * @param other the position
     * @return true or false
     */
    @Override
    public boolean isInPosition(Position other) {
        return this.isAlive() && this.position.equals(other);
    }

    @Override
    public boolean onCollision(GameItem other) {
        return false;
    }

    protected abstract String getSymbol();
    protected abstract int getDamage();
    public abstract int getRemainingLife();

    public abstract void onDelete();

    /**
     * Performs the movement of the GameObject automatically.
     */
    public abstract void automaticMove();

    public abstract void performMove(Move direction);

    public abstract boolean isInBorder();

    public abstract void computerAction();
}
