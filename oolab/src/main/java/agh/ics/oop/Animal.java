package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();
    private MapDirection direction;
    private Vector2D position;

    //constructors:-----------------------------------------------
    Animal(IWorldMap map) {
        this.map = map;
        position = new Vector2D(2, 2);
        direction = MapDirection.NORTH;
    }

    Animal(IWorldMap map, Vector2D initialPosition) {
        this.map = map;
        position = new Vector2D(initialPosition.x, initialPosition.y);
        direction = MapDirection.NORTH;
    }

    Animal(IWorldMap map, Vector2D initialPosition, MapDirection direction) {
        this.map = map;
        position = new Vector2D(initialPosition.x, initialPosition.y);
        this.direction = direction;
    }
    //-----------------------------------------------------------------

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public String toString() {
        return switch(direction) {
            case NORTH -> "N"; //"&#129081"; //⭡
            case EAST  -> "E"; //"&#129082"; //⭢
            case SOUTH -> "S"; //"&#129083"; //⭣
            case WEST  -> "W"; //"&#129080"; //⭠
        };
    }

    public boolean isAt(Vector2D position) {
        return (this.position.x == position.x) && (this.position.y == position.y);
    }

    public void move(MoveDirection moveDirection) {
        Vector2D newPosition = new Vector2D(position.x, position.y);
        MapDirection newDirection = direction;
        switch (moveDirection) {
            case RIGHT    -> newDirection = direction.next();
            case LEFT     -> newDirection = direction.previous();
            case FORWARD  -> newPosition = newPosition.add(direction.toUnitVector());
            case BACKWARD -> newPosition = newPosition.subtract(direction.toUnitVector());
        }
        if (map.canMoveTo(newPosition)) {
            positionChanged(position, newPosition);
            position = newPosition;
        }
        direction = newDirection;
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2D getPosition() {
        return position;
    }

    private void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    public String getImagePath() {
        return switch (direction) {
            case NORTH -> "src/main/resources/dogNorthDirection.png";
            case EAST  -> "src/main/resources/dogEastDirection.png";
            case SOUTH -> "src/main/resources/dogSouthDirection.png";
            case WEST  -> "src/main/resources/dogWestDirection.png";
        };
    }

}
