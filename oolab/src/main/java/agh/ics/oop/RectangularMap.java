package agh.ics.oop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RectangularMap implements IWorldMap {
    private final int width;
    private final int height;

    private final Map<Vector2D, Animal> animalsMap = new HashMap<>();

    private final MapVisualizer mapVisualizer;


    RectangularMap(int width, int height) {
        this.width  = width;
        this.height = height;
        mapVisualizer = new MapVisualizer(this);
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

    public boolean isOccupied(Vector2D position) {
        return animalsMap.get(position) != null;
    }

    public Object objectAt(Vector2D position) {
        return animalsMap.get(position);
    }

    public Map<Vector2D, Animal> getMap() {
        return animalsMap;
    }

    public String drawMap() {
        return mapVisualizer.draw(new Vector2D(0, 0), new Vector2D(width - 1, height - 1));
    }
}