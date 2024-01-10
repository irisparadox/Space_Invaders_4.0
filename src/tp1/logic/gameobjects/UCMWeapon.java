package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon {
    /**
     * Initializes an UCMWeapon given its position, health, damage and game.
     *
     * @param position     the position of the weapon
     * @param healthPoints the health of the weapon
     * @param damage       the damage which the weapon deals
     * @param game         the game where the weapon lies
     */
    public UCMWeapon(Position position, int healthPoints, int damage, GameWorld game) {
        super(position, healthPoints, damage, game);
    }

    @Override
    public boolean performAttack(GameItem other){
        boolean hasAttacked = false;
        if(this.onCollision(other)) {
            this.healthPoints = 0;
            hasAttacked = other.receiveAttack(this);
        }

        return hasAttacked;
    }
}
