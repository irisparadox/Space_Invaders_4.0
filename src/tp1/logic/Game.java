package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.GameModelException;
import tp1.exceptions.NotEnoughPointsException;
import tp1.logic.gameobjects.*;
import tp1.view.Messages;

import java.util.Random;

public class Game implements GameStatus, GameModel, GameWorld {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;

	private final Level LEVEL;
	private final long SEED;
	private int cycle;
	private int points;
	private boolean exit;
	private AlienManager manager;
	private GameObjectContainer container;
	private UCMShip ship;
	private Random random;

	public Game(Level level, long seed) throws GameModelException{
		this.LEVEL = level;
		this.SEED = seed;
		this.exit = false;
		init(null);
	}

	private void init(InitialConfiguration config) throws GameModelException{
		this.random = new Random();
		this.random.setSeed(this.SEED);
		this.cycle = 0;
		this.points = 0;
		this.manager = new AlienManager(this, this.LEVEL);
		this.container = new GameObjectContainer();
		this.container = this.manager.initialize(config);
		this.ship = new UCMShip(this);
		this.container.add(this.ship);
	}

	// Game Status Methods
	//##
	@Override
	public String positionToString(int x, int y) {
		Position position = new Position(x, y);
		return container.positionToString(position);
	}

	@Override
	public String infoToString() {
		return "Life: " + this.ship.getRemainingLife() + "\n" +
				Messages.SCORE + " " + this.points + "\n";
	}

	@Override
	public String stateToString() {
		return infoToString() + "Shockwave: " + shockwaveStatus() + "\n";
	}

	public String shockwaveStatus(){
		String str = "OFF";
		if(this.ship.canShootShockwave())
			str = "ON";

		return str;
	}

	@Override
	public boolean playerWin() {
		return this.manager.getRemainingAliens() == 0;
	}

	@Override
	public boolean aliensWin() {
		return this.ship.getRemainingLife() == 0 || this.manager.isInLowerBorder();
	}

	@Override
	public int getCycle() {
		return this.cycle;
	}

	@Override
	public int getRemainingAliens() {
		return this.manager.getRemainingAliens();
	}
	//##

	// Game Model Methods
	//##
	@Override
	public boolean move(Move move) throws GameModelException {
		return this.ship.performShipMove(move);
	}

	@Override
	public boolean shootLaser(Move direction) throws GameModelException {
		NormalLaser laser = this.ship.shoot(direction);
		boolean canShoot = false;
		if(laser != null) {
			this.container.add(laser);
			canShoot = true;
		}

		return canShoot;
	}

	@Override
	public boolean shootSuperLaser() throws GameModelException {
		boolean canShoot = false;
		if(this.points >= 5) {
			SuperLaser superLaser = this.ship.shootSuperLaser();
			if (superLaser != null) {
				this.container.add(superLaser);
				this.points -= 5;
				canShoot = true;
			}
		} else throw new NotEnoughPointsException(Messages.NOT_ENOUGH_POINTS_ERROR.formatted(this.points,5));

		return canShoot;
	}

	@Override
	public boolean performShockwaveAttack() throws GameModelException {
		Shockwave shockwave = this.ship.performShockwave();
		boolean attackPerformed = false;
		if(shockwave != null) {
			this.container.add(shockwave);
			this.manager.setUfoEnabled(false);
			attackPerformed = true;
		}
		return attackPerformed;
	}

	@Override
	public boolean isFinished(){
		return playerWin() || aliensWin() || this.exit;
	}

	@Override
	public void exit(){
		this.exit = true;
	}

	@Override
	public void reset(InitialConfiguration config) throws GameModelException {
		init(config);
	}
	//##

	// Game World Methods
	//##
	@Override
	public void destroyerShoot(DestroyerAlien destroyerAlien){
		container.add(destroyerAlien.getBomb());
	}

	@Override
	public void shootAvailableAgain(){
		this.ship.shootAvailableAgain();
	}

	@Override
	public void enableShockwave(){
		this.ship.enableShockwave();
	}

	@Override
	public boolean canShockwave(){return this.ship.canShootShockwave();}

	@Override
	public void addPoints(int n){
		this.points += n;
	}

	@Override
	public Random getRandom() {
		return this.random;
	}

	@Override
	public Level getLevel() {
		return this.LEVEL;
	}
	//##

	public void updateGame(){
		this.cycle++;
		this.manager.update();
		this.container.update();
		this.manager.pingPong();
	}
}
