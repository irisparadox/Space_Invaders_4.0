package tp1.logic.gameobjects;

import tp1.logic.Move;
import tp1.view.Messages;

public class Explosion extends EnemyWeapon{

    /**
     * Initializes a Weapon  given its position, health, damage and game.
     *
     * @param position     the position of the weapon
     * @param healthPoints the health of the weapon
     * @param damage       the damage which the weapon deals
     * @param game         the game where the weapon lies
     */
    public Explosion() {
        super(null, 0, 1, null);
    }

    @Override
    protected String getSymbol() {
        return Messages.EXPLOSIVE_SYMBOL;
    }

    @Override
    public void onDelete() {

    }

    /**
     * Performs the movement of the GameObject automatically.
     */
    @Override
    public void automaticMove() {

    }

    @Override
    public void performMove(Move direction) {

    }
}
