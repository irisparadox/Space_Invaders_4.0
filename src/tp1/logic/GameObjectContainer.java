package tp1.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp1.logic.gameobjects.GameObject;

public class GameObjectContainer {

    private List<GameObject> objects;
    private Iterator<GameObject> iterator;

    public GameObjectContainer() {
        objects = new ArrayList<>();
        iterator = null;
    }

    public void add(GameObject object) {
        objects.add(object);
    }

    public String positionToString(Position position){
        for (GameObject object : objects) {
            if(object.isInPosition(position))
                return object.toString();
        }
        return " ";
    }

    public void update(){
        computerActions();
        objectsInBorder();
        automaticMoves();
        objectsCollision();
        checkHealthState();
    }

    private void computerActions() {
        List<GameObject> objectsAux = new ArrayList<>(objects);
        for (GameObject object : objectsAux) {
            object.computerAction();
        }
    }

    private void objectsInBorder(){
        for(GameObject object : objects){
            object.isInBorder();
        }
    }

    private void automaticMoves() {
        for (GameObject object : objects) {
            object.automaticMove();
        }
    }

    private void objectsCollision(){
        for(GameObject object1 : objects){
            for (GameObject object2 : objects) {
                if(object1 != object2)
                    if (object1.performAttack(object2))
                        object1.onDelete();
            }
        }
    }

    private void checkHealthState(){
        iterator = objects.iterator();
        while(iterator.hasNext()) {
            GameObject object = iterator.next();
            if (!object.isAlive()) {
                object.onDelete();
                iterator.remove();
            }
        }
    }
}