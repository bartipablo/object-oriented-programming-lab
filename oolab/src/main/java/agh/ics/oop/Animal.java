package agh.ics.oop;

import javax.print.DocFlavor;



public class Animal {
    private IWorldMap map;
    private MapDirection direction = MapDirection.NORTH;
    private Vector2D position;

    Animal(IWorldMap map) {
        this.map = map;
        position = new Vector2D(2, 2);
    }

    Animal(IWorldMap map, Vector2D initialPosition) {
        this.map = map;
        position = new Vector2D(initialPosition.x, initialPosition.y);
    }


    public String toString() {
        return switch(direction) {
            case NORTH -> "N";
            case EAST  -> "E";
            case SOUTH -> "S";
            case WEST  -> "W";
        };
    }

    public boolean isAt(Vector2D vectorPosition) {
        return (this.position.x == vectorPosition.x) && (this.position.y == vectorPosition.y);
    }

    public void move(MoveDirection direction) {
        Vector2D newPosition = new Vector2D(this.position.x, this.position.y);
        switch (direction) {
            case RIGHT    -> {
                this.direction = this.direction.next();
                return;
            }
            case LEFT     -> {
                this.direction = this.direction.previous();
                return;
            }
            case FORWARD  -> newPosition = newPosition.add(this.direction.toUnitVector());
            case BACKWARD -> newPosition = newPosition.substract(this.direction.toUnitVector());
        }
        if (map.canMoveTo(newPosition)) {
            this.position = newPosition;
        }
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public Vector2D getPosition() {
        return this.position;
    }

}
