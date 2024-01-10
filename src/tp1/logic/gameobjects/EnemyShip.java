package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public abstract class EnemyShip extends Ship {
    /**
     * Initializes a Ship given its position, health points and game.
     *
     * @param position     the position of the GameObject
     * @param healthPoints the health of the GameObject
     * @param game         the game where the GameObject lies.
     */
    public EnemyShip(Position position, int healthPoints, GameWorld game) {
        super(position, healthPoints, game);
    }

    @Override
    public String toString(){
        return Messages.status(this.getSymbol(),this.healthPoints);
    }
}
