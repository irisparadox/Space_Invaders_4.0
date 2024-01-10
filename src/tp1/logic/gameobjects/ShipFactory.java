package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;

import java.util.Arrays;
import java.util.List;

public class ShipFactory {
    private static final List<AlienEntity> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
            new RegularAlien(),
            new DestroyerAlien(),
            new ExplosiveAlien(),
            new ZombieAlien()
    );

    public static AlienEntity spawnAlienShip(String input, GameWorld game, Position pos, AlienManager am) {
        for (AlienEntity alienShip : AVAILABLE_ALIEN_SHIPS) {
            if (alienShip.getSymbol().equals(input)) {
                return alienShip.copy(pos, game, am);
            }
        }

        return null;
    }
}
