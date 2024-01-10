package tp1.logic.gameobjects;

import tp1.logic.*;

import java.util.Objects;

/**
 * Generic class for Alien type entities.
 */
public abstract class AlienEntity extends EnemyShip{
    protected AlienManager manager;
    /**
     * Initializes an AlienEntity given its position, and its health points.
     * @param position The position of the entity.
     * @param healthPoints The health of the entity.
     */
    public AlienEntity(Position position, int healthPoints, GameWorld game, AlienManager manager){
        super(position, healthPoints, game);
        this.manager = manager;
    }

    protected abstract AlienEntity copy(Position position, GameWorld game, AlienManager manager);

    /**
     * Equals override. Checks if given AlienEntity has the same amount of health points and the same position.
     * @param o other AlienEntity.
     * @return true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlienEntity that = (AlienEntity) o;
        return healthPoints == that.healthPoints && this.position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, healthPoints);
    }

    /**
     * Checks whether entity is placed in border.
     * @return true or false
     */
    @Override
    public boolean isInBorder(){
        boolean inBorder = true;
        if(isInLowerBorder())
            this.manager.setInLowerBorder();
        else if(isInLateralBorder())
            this.manager.shipOnBorder();
        else
            inBorder = false;

        return inBorder;
    }
    private boolean isInLateralBorder() {
        return this.position.borderBound();
    }

    private boolean isInLowerBorder(){
        return this.position.lowerBound();
    }

    /**
     * Performs the movement of the AlienEntity automatically.
     */
    @Override
    public void automaticMove() {
        if(this.manager.readyToMove()){
            performMove(this.manager.getDirection());

            //We make sure again that any Alien is entering the lower border
            if(this.isInLowerBorder())
                this.manager.setInLowerBorder();
        }
    }

    @Override
    public void performMove(Move direction){
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
    public boolean receiveAttack(EnemyWeapon weapon){
        boolean alive = isAlive();
        if(alive)
            this.healthPoints -= weapon.getDamage();
        return alive;
    }

    @Override
    public void computerAction(){

    }
}
