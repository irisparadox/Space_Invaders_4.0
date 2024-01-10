package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

import static tp1.logic.Move.*;

public class ExplosiveAlien extends AlienEntity {
    private static final int HEALTH_POINTS = 2;
    private static final Move[] DIRECTIONS_X = {NONE,RIGHT,RIGHT,RIGHT,NONE,LEFT,LEFT,LEFT};
    private static final Move[] DIRECTIONS_Y = {UP,UP,NONE,DOWN,DOWN,DOWN,NONE,UP};
    private boolean hasAttacked;
    private boolean canAttack;

    protected ExplosiveAlien(){
        super(null,0,null,null);
        this.hasAttacked = false;
        this.canAttack = false;
    }

    /**
     * Initializes an AlienEntity given its position, and its health points.
     *
     * @param position     The position of the entity.
     * @param game
     * @param manager
     */
    public ExplosiveAlien(Position position, GameWorld game, AlienManager manager) {
        super(position, HEALTH_POINTS, game, manager);
        this.hasAttacked = false;
        this.canAttack = false;
    }

    @Override
    protected AlienEntity copy(Position position, GameWorld game, AlienManager manager) {
        return new ExplosiveAlien(position,game,manager);
    }

    @Override
    public boolean performAttack(GameItem other){
        if(canAttack && isAdyacent(other)){
            other.receiveAttack(new Explosion());
            this.hasAttacked = true;
        }

        return false;
    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon){
        this.healthPoints -= weapon.getDamage();
        boolean receivedAttack = true;
        if(this.healthPoints <= 0){
            receivedAttack = false;
            this.canAttack = true;
        }

        return receivedAttack;
    }

    private boolean isAdyacent(GameItem other){
        Position nextPos = this.position;
        int i = 0;
        while(i < 8 && !other.isInPosition(nextPos)) {
            nextPos = this.position.changePosition(DIRECTIONS_X[i]);
            nextPos = nextPos.changePosition(DIRECTIONS_Y[i]);
            i++;
        }

        return i < 8;
    }

    @Override
    public boolean isAlive(){
        return this.healthPoints > 0 || !this.hasAttacked;
    }

    @Override
    protected String getSymbol() {
        return Messages.EXPLOSIVE_ALIEN_SYMBOL;
    }

    @Override
    public String toString(){
        return canAttack ? Messages.EXPLOSIVE_SYMBOL : Messages.status(this.getSymbol(),this.healthPoints);
    }

    @Override
    public void onDelete() {
        this.manager.onAlienKill();
        this.game.addPoints(12);
    }
}
