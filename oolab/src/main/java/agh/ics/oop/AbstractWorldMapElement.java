package agh.ics.oop;

public abstract class AbstractWorldMapElement implements IMapElement {
    Vector2D position;

    public Vector2D getPosition() {
        return position;
    }

    public abstract String toString();

}
