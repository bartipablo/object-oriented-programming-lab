package agh.ics.oop;

public class Grass extends AbstractWorldMapElement{

    Grass(Vector2D initialPosition) {
        position = initialPosition;
    }

    public String toString() {
        return "*";
    }


}
