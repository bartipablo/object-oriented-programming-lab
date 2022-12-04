package agh.ics.oop;

public class Grass implements IMapElement {

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

    public String getImagePath() {
        return "src/main/resources/grass.png";
    }


}
