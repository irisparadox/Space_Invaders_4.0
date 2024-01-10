package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {

    private static final int HEALTH = 1;
    private static final int DAMAGE = 1;
    private final Move DIRECTION = Move.DOWN;
    /**
     * Initializes a Weapon  given its position, health, damage and game.
     *
     * @param position     the position of the weapon
     * @param game         the game where the weapon lies
     */
    public Bomb(Position position, GameWorld game) {
        super(position, HEALTH, DAMAGE, game);
    }

    @Override
    protected String getSymbol() {
        return Messages.BOMB_SYMBOL;
    }

    @Override
    public void onDelete() {

    }

    /**
     * Performs the movement of the GameObject automatically.
     */
    @Override
    public void automaticMove() {
        performMove(this.DIRECTION);
        if(outOfBounds()) {
            this.healthPoints = 0;
        }
    }

    @Override
    public void performMove(Move direction) {
        this.position = this.position.changePosition(direction);
    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon){
        boolean alive = isAlive();
        if(alive)
            this.healthPoints -= weapon.getDamage();

        return alive;
    }

    @Override
    public boolean onCollision(GameItem other) {
        return other.isInPosition(this.position);
    }
}
