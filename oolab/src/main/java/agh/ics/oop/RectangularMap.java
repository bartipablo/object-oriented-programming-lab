package agh.ics.oop;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;
    private final MapVisualizer  mapVisualizer = new MapVisualizer(this);

    RectangularMap(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2D position) {
        if (position.x < 0 || position.y < 0 || position.x >= width || position.y >= height){
            return false;
        }
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animalsOnMap.put(animal.getPosition(), animal);
            return true;
        }
        throw new IllegalArgumentException("the animal cannot be placed in position " + animal.getPosition());
    }

    @Override
    public Object objectAt(Vector2D position) {
        return animalsOnMap.get(position);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(new Vector2D(0, 0), new Vector2D(width - 1, height -1));
    }

    @Override
    public void positionChanged(Vector2D previousAnimalPosition, Vector2D newAnimalPosition) {
        Animal animal = animalsOnMap.get(previousAnimalPosition);
        animalsOnMap.remove(previousAnimalPosition);
        animalsOnMap.put(newAnimalPosition, animal);
    }

    @Override
    public Vector2D getUpperMapLimit() {
        return new Vector2D(width, height);
    }

    @Override
    public Vector2D getLowerMapLimit() {
        return new Vector2D(0, 0);
    }

}