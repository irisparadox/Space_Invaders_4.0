package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class ZombieAlien extends AlienEntity {
    private static final int HEALTH_POINTS = 3;
    private static final int DAMAGE = 1;
    private boolean isDead;

    /** Empty constructor for ZombieAlien.
     */
    protected ZombieAlien(){
        super(null, 0, null, null);
        isDead = false;
    }
    /**
     * Initializes an AlienEntity given its position, and its health points.
     *
     * @param position     The position of the entity.
     * @param game
     * @param manager
     */
    public ZombieAlien(Position position, GameWorld game, AlienManager manager) {
        super(position, HEALTH_POINTS, game, manager);
        isDead = false;
    }

    @Override
    protected AlienEntity copy(Position position, GameWorld game, AlienManager manager) {
        return new ZombieAlien(position,game,manager);
    }

    @Override
    public boolean isInBorder(){
        boolean inBorder = true;
        if(this.position.lowerBound() && this.healthPoints > 0)
            this.manager.setInLowerBorder();
        else if(this.position.borderBound())
            this.manager.shipOnBorder();
        else
            inBorder = false;

        return inBorder;
    }

    @Override
    public void automaticMove() {
        if(this.healthPoints > 0) {
            if (this.manager.readyToMove()) {
                performMove(this.manager.getDirection());

                //We make sure again that any Alien is entering the lower border
                if (this.position.lowerBound())
                    this.manager.setInLowerBorder();
            }
        } else {
            performMove(Move.DOWN);
        }
    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon){
        boolean alive = this.healthPoints > 0;
        if(alive)
            this.healthPoints -= weapon.getDamage();
        return alive;
    }

    @Override
    public boolean receiveAttack(EnemyWeapon weapon){
        boolean alive = this.healthPoints > 0;
        if(alive)
            this.healthPoints -= weapon.getDamage();
        return alive;
    }

    @Override
    public boolean performAttack(GameItem other){
        boolean hasAttacked = false;
        if(this.healthPoints <= 0 && this.onCollision(other)) {
            handleZombieDeath();
            isDead = true;
            hasAttacked = other.receiveAttack(this);
        }

        return hasAttacked;
    }

    @Override
    public int getDamage(){
        return DAMAGE;
    }

    @Override
    public boolean isAlive(){
        if(this.position.invalidPosition()) {
            handleZombieDeath();
            isDead = true;
        }
        return !isDead;
    }

    private void handleZombieDeath(){
        this.manager.onAlienKill();
        this.game.addPoints(7);
    }

    @Override
    protected String getSymbol() {
        return Messages.ZOMBIE_ALIEN_SYMBOL;
    }

    @Override
    public void onDelete() {
    }

    @Override
    public boolean onCollision(GameItem other) {
        return other.isInPosition(this.position);
    }
}
