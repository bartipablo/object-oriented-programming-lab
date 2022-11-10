package agh.ics.oop;

public class Grass {
    Vector2D position;


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
