package agh.ics.oop;


public class Animal {
    private final IWorldMap map;
    private MapDirection direction;
    private Vector2D position;

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

}
