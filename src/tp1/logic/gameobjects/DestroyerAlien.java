package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * Destroyer Alien Entity
 * <p>
 * Extends: {@link AlienEntity}
 */
public class DestroyerAlien extends AlienEntity {
    private static final int LIFE = 1;
    private boolean canShoot;
    private Bomb bomb;

    /** Empty constructor for DestroyerAlien.
     */
    protected DestroyerAlien(){
        super(null, 0, null, null);
        this.canShoot = true;
        this.bomb = null;
    }
    /**
     * Initializes the Destroyer Alien entity given a position. Note that its health points are constant
     * in its initialization and will not be considered in this constructor.
     * @param position the position of the entity
     * @param game the game where the DestroyerAlien lies
     */
    public DestroyerAlien(Position position, GameWorld game, AlienManager manager){
        super(position,LIFE,game,manager);
        this.canShoot = true;
        this.bomb = null;
    }

    public Bomb getBomb(){
        return this.bomb;
    }

    @Override
    public AlienEntity copy(Position position, GameWorld game, AlienManager manager){
        return new DestroyerAlien(position, game, manager);
    }

    @Override
    public String getSymbol() {
        return Messages.DESTROYER_ALIEN_SYMBOL;
    }

    @Override
    public void onDelete() {
        this.game.addPoints(10);
        this.manager.onAlienKill();
    }

    @Override
    public void computerAction(){
        if(this.bomb != null && !this.bomb.isAlive()) {
            this.bomb = null;
            this.canShoot = true;
        }
        
        if(this.canShoot && canGenerateRandomBomb()){
            this.bomb = new Bomb(this.position,this.game);
            this.canShoot = false;
            game.destroyerShoot(this);
        }
    }

    private boolean canGenerateRandomBomb(){
        return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
    }
}
