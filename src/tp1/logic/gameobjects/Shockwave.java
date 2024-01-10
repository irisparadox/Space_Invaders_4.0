package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public class Shockwave extends UCMWeapon {
    private static final int DAMAGE = 1;
    private static final int HEALTH_POINTS = 1;
    /**
     * Initializes an UCMWeapon given its position, health, damage and game.
     *
     * @param position     the position of the weapon
     * @param healthPoints the health of the weapon
     * @param damage       the damage which the weapon deals
     * @param game         the game where the weapon lies
     */
    public Shockwave(GameWorld game) {
        super(new Position(), HEALTH_POINTS, DAMAGE, game);
    }

    @Override
    protected String getSymbol() {
        return " ";
    }

    @Override
    public boolean onCollision(GameItem other) {
        return true;
    }

    @Override
    public void onDelete() {
    }

    @Override
    public void performMove(Move direction) {

    }

    /**
     * Performs the movement of the GameObject automatically.
     */
    @Override
    public void automaticMove() {

    }

    @Override
    protected boolean outOfBounds(){
        return false;
    }
}
