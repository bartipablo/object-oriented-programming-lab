package agh.ics.oop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RectangularMap implements IWorldMap {
    public final int width;
    public final int height;

    private final Map<Vector2D, Animal> animalsAtPosition = new HashMap<>();


    RectangularMap(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    public boolean canMoveTo(Vector2D position) {
        if (position.x < 0 || position.y < 0 || position.x >= width || position.y >= height){
            return false;
        } else {
            return !isOccupied(position);
        }
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animalsAtPosition.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2D position) {
        return animalsAtPosition.get(position) != null;
    }

    public Object objectAt(Vector2D position) {
        return animalsAtPosition.get(position);
    }

    public void moveOnMap(Vector2D position, MoveDirection direction) {
        Animal animal = animalsAtPosition.get(position);
        animalsAtPosition.remove(position);
        animal.move(direction);
        animalsAtPosition.put(animal.getPosition(), animal);
    }

    public Vector2D[] getKeys() {
        return animalsAtPosition.keySet().toArray(new Vector2D[0]);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
