package tp1.logic;

import tp1.logic.gameobjects.DestroyerAlien;

import java.util.Random;

public interface GameWorld {
    public void destroyerShoot(DestroyerAlien destroyerAlien);
    public void shootAvailableAgain();
    public void enableShockwave();
    public boolean canShockwave();
    public void addPoints(int i);
    public Random getRandom();
    public Level getLevel();
}
