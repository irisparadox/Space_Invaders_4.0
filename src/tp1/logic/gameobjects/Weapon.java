package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {
    protected int damage;
    /**
     * Initializes a Weapon  given its position, health, damage and game.
     *
     * @param position     the position of the weapon
     * @param healthPoints the health of the weapon
     * @param damage       the damage which the weapon deals
     * @param game         the game where the weapon lies
     */
    public Weapon(Position position, int healthPoints, int damage, GameWorld game) {
        super(position, healthPoints, game);
        this.damage = damage;
    }

    @Override
    public String toString(){
        return this.getSymbol();
    }

    @Override
    public boolean isInBorder(){
        return false;
    }

    @Override
    public int getRemainingLife(){
        return this.healthPoints;
    }

    @Override
    public int getDamage(){
        return this.damage;
    }

    @Override
    public void computerAction(){

    }

    protected boolean outOfBounds(){
        return this.position.invalidPosition();
    }
}
