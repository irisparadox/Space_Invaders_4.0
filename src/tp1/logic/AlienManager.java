package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.InitializationException;
import tp1.logic.gameobjects.*;
import tp1.view.Messages;


/**
 * 
 * Manages alien initialization and
 * used by aliens to coordinate movement
 *
 */
public class AlienManager {
	private GameWorld game;
	private Level level;
	private Move lastDirection;
	private Move direction;
	private int remainingAliens;
	private boolean inBorderCycle;
	private boolean inLowerBorder;
	private boolean ufoEnabled;
	private boolean readyToMove;
	private int cyclesToMove;

	public AlienManager(GameWorld game, Level level){
		this.game = game;
		this.level = level;
		this.direction = Move.LEFT;
		this.lastDirection = Move.LEFT;
		this.remainingAliens = level.getNumDestroyerAliens() + level.getNumRegularAliens();
		this.inBorderCycle = false;
		this.inLowerBorder = false;
		this.ufoEnabled = true;
		this.readyToMove = false;
		this.cyclesToMove = 0;
	}

	/**
	 * Initializes the GameObjectContainer which will be used to contain all the objects.
	 *
	 * @param conf Initial configuration (if null, use standard initialization based on level)
	 * @return the initialized container
	 */
	public GameObjectContainer initialize(InitialConfiguration conf) throws InitializationException {
		GameObjectContainer container = new GameObjectContainer();

		if (conf == null || conf == InitialConfiguration.NONE) {
			initializeRegularAliens(container);
			initializeDestroyerAliens(container);
		} else {
			initializeFromConfiguration(container, conf);
		}

		initializeUfo(container);

		return container;
	}

	private void initializeFromConfiguration(GameObjectContainer container, InitialConfiguration conf) throws InitializationException {
		this.remainingAliens = 0;
		for (String description : conf.getShipDescription()) {
			String[] words = description.split(" ");
			if (words.length == 3) {
				try {
					Position position = new Position(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
					if(position.invalidPosition())
						throw new InitializationException(Messages.OFF_WORLD_POSITION.formatted(position));
					AlienEntity alienShip = ShipFactory.spawnAlienShip(words[0], game, position, this);
					if (alienShip != null) {
						this.remainingAliens++;
						container.add(alienShip);
					} else throw new InitializationException(Messages.UNKNOWN_SHIP.formatted(words[0]));
				} catch (NumberFormatException e){
					throw new InitializationException(Messages.INVALID_POSITION.formatted(words[1],words[2]));
				}
			} else throw new InitializationException(Messages.INCORRECT_ENTRY.formatted(description));
		}
	}

	/**
	 * Initializes in container all the RegularAlien aliens placing them correctly in the board,
	 * given the actual container.
	 * @param container the container
	 */
	private void initializeRegularAliens(GameObjectContainer container) {
		int col = Game.DIM_X/2 - level.getNumRegularAliens()/2;
		for(int i = 0; i < level.getNumRowsRegularAliens(); i++) {
			for (int j = 0; j < level.getNumRegularAliens() / level.getNumRowsRegularAliens(); j++) {
				container.add(new RegularAlien(new Position(col + j, i + 1), game, this));
			}
		}
	}

	/**
	 * Initializes in container all the DestroyerAlien aliens placing them correctly in the board,
	 * given the actual container.
	 * @param container the container
	 */
	private void initializeDestroyerAliens(GameObjectContainer container) {
		int col = Game.DIM_X/2 - level.getNumDestroyerAliens()/2;
		int factor = 0;
		if(level.getNumRowsRegularAliens() > 1)
			factor = 1;
		for(int i = 0; i < level.getNumDestroyerAliens(); i++){
			container.add(new DestroyerAlien(new Position(col + i, 2 + factor), game, this));
		}
	}

	private void initializeUfo(GameObjectContainer container){
		container.add(new Ufo(new Position(Game.DIM_X, 0),this.game, this));
	}

	public void update(){
		if(cyclesToMove == this.level.getNumCyclesToMoveOneCell()){
			readyToMove = true;
			cyclesToMove = 1;
		} else {
			cyclesToMove++;
			readyToMove = false;
		}
	}

	public boolean readyToMove(){
		return this.readyToMove;
	}

	public void shipOnBorder(){
		if(!this.inBorderCycle) {
			this.lastDirection = this.direction;
			this.direction = Move.DOWN;
			this.inBorderCycle = true;
		}
	}

	public void setInLowerBorder(){
		this.inLowerBorder = true;
	}

	public boolean isInLowerBorder(){
		return inLowerBorder;
	}

	private void resetBorderState(){
		if(!this.direction.equals(Move.DOWN))
			this.inBorderCycle = false;
	}

	public void pingPong(){
		if(readyToMove()) {
			resetBorderState();
			if (this.inBorderCycle) {
				this.direction = this.lastDirection.pingPong();
			}
		}
	}

	public void onAlienKill(){
		this.remainingAliens--;
	}

	public Move getDirection(){
		return this.direction;
	}

	public boolean isUfoEnabled(){
		return this.ufoEnabled;
	}

	public void setUfoEnabled(boolean value){
		this.ufoEnabled = value;
	}

	public int getRemainingAliens(){
		return this.remainingAliens;
	}
}
