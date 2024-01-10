package tp1.logic.gameobjects;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.LaserInFlightException;
import tp1.exceptions.NoShockwaveException;
import tp1.exceptions.OffWorldException;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

import java.util.Objects;

public class UCMShip extends Ship {
    private static final Position INIT_POSITION = new Position(Game.DIM_X/2,Game.DIM_Y-1);
    private static final int LIFE = 3;
    private static final Move[] ALLOWED_MOVES = {Move.LEFT, Move.LLEFT, Move.RIGHT, Move.RRIGHT};
    private boolean canShoot;
    private boolean canShockwave;
    private boolean isDead;
    public UCMShip(GameWorld game){
        super(INIT_POSITION,LIFE,game);
        this.canShoot = true;
        this.canShockwave = false;
        this.isDead = false;
    }

    @Override
    public String getSymbol() {
        String str = Messages.UCMSHIP_SYMBOL;
        if(isDead)
            str = Messages.UCMSHIP_DEAD_SYMBOL;

        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UCMShip ucmShip = (UCMShip) o;
        return this.position.equals(ucmShip.position) && this.healthPoints == ucmShip.healthPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, healthPoints);
    }

    public boolean performShipMove(Move direction) throws OffWorldException {
        Position newPosition = this.position;
        newPosition = newPosition.changePosition(direction);
        boolean notInvalid = !newPosition.invalidPosition();
        if(notInvalid)
            this.position = newPosition;
        else throw new OffWorldException(Messages.OFF_WORLD_MESSAGE.formatted(direction,this.position.toString()));

        return notInvalid;
    }

    public NormalLaser shoot(Move direction) throws LaserInFlightException {
        if(canShoot) {
            this.canShoot = false;
            return new NormalLaser(direction, this.position, this.game);
        }
        throw new LaserInFlightException(Messages.LASER_ALREADY_SHOT);
    }

    public SuperLaser shootSuperLaser() throws LaserInFlightException {
        if(canShoot) {
            this.canShoot = false;
            return new SuperLaser(this.position, this.game);
        }
        throw new LaserInFlightException(Messages.LASER_ALREADY_SHOT);
    }

    public Shockwave performShockwave() throws NoShockwaveException {
        if(this.canShockwave){
            this.canShockwave = false;
            return new Shockwave(this.game);
        }
        throw new NoShockwaveException(Messages.SHOCKWAVE_UNAVAILABLE);
    }

    public void enableShockwave(){
        this.canShockwave = true;
    }

    public boolean canShootShockwave(){
        return this.canShockwave;
    }

    public void shootAvailableAgain(){
        this.canShoot = true;
    }

    @Override
    public boolean receiveAttack(EnemyWeapon weapon){
        this.healthPoints -= weapon.getDamage();
        boolean hasDied = this.healthPoints <= 0;
        if(hasDied)
            onDelete();

        return !hasDied;
    }

    @Override
    public boolean receiveAttack(ZombieAlien zombieAlien){
        boolean alive = isAlive();
        if(alive){
            this.healthPoints -= zombieAlien.getDamage();
        }
        //Object received attack because it was alive
        return alive;
    }

    @Override
    public boolean isAlive(){
        return true;
    }


    @Override
    public void onDelete() {
        this.isDead = true;
    }

    @Override
    public void performMove(Move direction){}

    @Override
    public boolean isInBorder() {
        return false;
    }

    public static String allowedMoves(String separator){
        StringBuilder buffer = new StringBuilder();
        buffer.append(ALLOWED_MOVES[0]);
        for(int i = 1; i < ALLOWED_MOVES.length; i++){
            buffer.append(separator).append(ALLOWED_MOVES[i]);
        }
        return buffer.toString();
    }

    @Override
    public void computerAction() {}

    @Override
    public void automaticMove() {}
}
