package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.GameModelException;

public interface GameModel {
    public boolean move(Move move) throws GameModelException;
    public boolean shootLaser(Move direction) throws GameModelException;
    public boolean shootSuperLaser() throws GameModelException;
    public boolean performShockwaveAttack() throws GameModelException;
    public boolean isFinished();
    public void exit();
    public void reset(InitialConfiguration config) throws GameModelException;
}
