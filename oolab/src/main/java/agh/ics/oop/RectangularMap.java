package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements IWorldMap {
    private final int width;
    private final int height;

    private final Map<Vector2D, Animal> animalsMap = new HashMap<>();


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
            animalsMap.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    public boolean removeFromMap(Animal animal) {
        if (animalsMap.get(animal.getPosition()) != null) {
            animalsMap.remove(animal.getPosition());
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2D position) {
        return animalsMap.get(position) != null;
    }

    public Object objectAt(Vector2D position) {
        return animalsMap.get(position);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
