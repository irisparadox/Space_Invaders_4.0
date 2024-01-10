package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameItem {
    /**
     * Performs an attack over another GameItem.
     * @param other the other GameObject
     * @return attack could be performed
     */
    public boolean performAttack(GameItem other);

    /**
     * Receives an attack from an EnemyWeapon.
     * @param weapon the enemy weapon
     * @return attack could be received
     */
    public boolean receiveAttack(EnemyWeapon weapon);

    /**
     * Receives an attack from a UCMWeapon.
     * @param weapon the UCMWeapon.
     * @return attack could be received
     */
    public boolean receiveAttack(UCMWeapon weapon);

    public boolean receiveAttack(ZombieAlien zombieAlien);

    /**
     * Checks whether GameItem is alive.
     * @return true or false
     */
    public boolean isAlive();

    /**
     * Checks if GameItem is placed at a given position.
     * @param other the position
     * @return true or false
     */
    public boolean isInPosition(Position other);

    /**
     * Checks collision between two GameItem objects.
     * @param other
     * @return collision happened.
     */
    public boolean onCollision(GameItem other);
}
