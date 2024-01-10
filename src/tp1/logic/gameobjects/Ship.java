package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class Ship extends GameObject {

    /**
     * Initializes a Ship given its position, health points and game.
     *
     * @param position     the position of the GameObject
     * @param healthPoints the health of the GameObject
     * @param game         the game where the GameObject lies
     */
    public Ship(Position position, int healthPoints, GameWorld game) {
        super(position, healthPoints, game);
    }

    @Override
    public String toString(){
        return this.getSymbol();
    }

    @Override
    public int getRemainingLife(){
        return this.healthPoints;
    }

    @Override
    protected int getDamage() {
        return 0;
    }
}
