package agh.ics.oop;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap  {
    protected final Map<Vector2D, Animal> animalsOnMap = new HashMap<>();

    public abstract boolean canMoveTo(Vector2D position);

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animalsOnMap.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    public abstract boolean isOccupied(Vector2D position);

    public abstract Object objectAt(Vector2D position);

    public abstract String toString();

    public void changeAnimalPosition(Vector2D previousAnimalPosition, Vector2D newAnimalPosition) {
        Animal animal = animalsOnMap.get(previousAnimalPosition);
        animalsOnMap.remove(previousAnimalPosition);
        animalsOnMap.put(newAnimalPosition, animal);
    }
}
