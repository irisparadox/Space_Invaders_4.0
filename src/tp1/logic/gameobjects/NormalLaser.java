package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 *
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class NormalLaser extends Laser {
    private static final int HEALTH_POINTS = 1;
    private static final Move[] ALLOWED_MOVES = {Move.UP, Move.LUP, Move.RUP};
    private static final int DAMAGE = 1;
    public NormalLaser(Move direction, Position position, GameWorld game) {
        super(position, HEALTH_POINTS, DAMAGE, direction, game);
    }

    @Override
    public String getSymbol(){
        String aux = Messages.UP_LASER_SYMBOL;
        if(this.dir == Move.LUP)
            aux = Messages.LUP_LASER_SYMBOL;
        else if (this.dir == Move.RUP)
            aux = Messages.RUP_LASER_SYMBOL;

        return aux;
    }

    public static String allowedMoves(String separator){
        StringBuilder buffer = new StringBuilder();
        buffer.append(ALLOWED_MOVES[0]);
        for(int i = 1; i < ALLOWED_MOVES.length; i++){
            buffer.append(separator).append(ALLOWED_MOVES[i]);
        }
        return buffer.toString();
    }
}
