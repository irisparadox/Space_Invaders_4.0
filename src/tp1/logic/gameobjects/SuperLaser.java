package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class SuperLaser extends Laser {
    private static final int HEALTH_POINTS = 1;
    private static final int DAMAGE = 2;
    private static final Move DIR = Move.UP;
    public SuperLaser(Position position, GameWorld game) {
        super(position, HEALTH_POINTS, DAMAGE, DIR, game);
    }

    @Override
    public String getSymbol(){
        return Messages.SUPERLASER_SYMBOL;
    }
}
