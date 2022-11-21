package agh.ics.oop;

public class Grass {

    private final Vector2D position;

    Grass(Vector2D initialPosition) {
        position = initialPosition;
    }

    public Vector2D getPosition() {
        return position;
    }

    public String toString() {
        return "*";
    }


}
