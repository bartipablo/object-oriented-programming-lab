package agh.ics.oop;

import javax.print.DocFlavor;



public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2D position = new Vector2D(2, 2);

    public String toString() {
        return position.toString() + " " + direction ;
    }

    public boolean isAt(Vector2D vectorPosition) {
        return (this.position.x == vectorPosition.x) && (this.position.y == vectorPosition.y);
    }

    public void move(MoveDirection direction) {
        Vector2D newPosition = new Vector2D(this.position.x, this.position.y);
        switch (direction) {
            case RIGHT    -> this.direction = this.direction.next();
            case LEFT     -> this.direction = this.direction.previous();
            case FORWARD  -> newPosition = newPosition.add(this.direction.toUnitVector());
            case BACKWARD -> newPosition = newPosition.substract(this.direction.toUnitVector());
        }

        if (newPosition.x >= 0 && newPosition.x <= 4 && newPosition.y >= 0 && newPosition.y <= 4) {
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
