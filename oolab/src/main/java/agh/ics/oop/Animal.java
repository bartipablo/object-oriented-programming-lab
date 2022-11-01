package agh.ics.oop;

import javax.print.DocFlavor;



public class Animal {
    private final IWorldMap map;
    private final MapDirection direction;
    private final Vector2D position;

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

    public String toString() {
        return switch(direction) {
            case NORTH -> "&#129081"; //⭡
            case EAST  -> "&#129082"; //⭢
            case SOUTH -> "&#129083"; //⭣
            case WEST  -> "&#129080"; //⭠
        };
    }

    public boolean isAt(Vector2D position) {
        return (this.position.x == position.x) && (this.position.y == position.y);
    }

    public Animal move(MoveDirection moveDirection) {
        Vector2D newPosition = new Vector2D(position.x, position.y);
        MapDirection newDirection = direction;
        switch (moveDirection) {
            case RIGHT    -> newDirection = direction.next();
            case LEFT     -> newDirection = direction.previous();
            case FORWARD  -> newPosition = newPosition.add(direction.toUnitVector());
            case BACKWARD -> newPosition = newPosition.substract(direction.toUnitVector());
        }
        if (map.canMoveTo(newPosition)) {
            return new Animal(map, newPosition, newDirection);
        }
        return new Animal(map, position, newDirection);
    }

    public Animal changePosition(Vector2D newPosition) {
        return new Animal(map, newPosition, direction);
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2D getPosition() {
        return position;
    }

}
