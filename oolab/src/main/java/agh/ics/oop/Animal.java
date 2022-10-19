package agh.ics.oop;

import javax.print.DocFlavor;



public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2D position = new Vector2D(2, 2);

    public String toString() {
        return "(" + this.position.x + ", " + this.position.y + ") " + direction ;
    }

    public boolean isAt(Vector2D vectorPosition) {
        return (this.position.x == vectorPosition.x) && (this.position.y == vectorPosition.y);
    }

    public void move(MoveDirection direction) {
        switch(direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT  -> this.direction = this.direction.previous();

    }


}
