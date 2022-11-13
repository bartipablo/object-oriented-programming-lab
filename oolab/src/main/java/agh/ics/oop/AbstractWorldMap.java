package agh.ics.oop;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap  {
    protected final Map<Vector2D, Animal> animalsOnMap = new HashMap<>();

    public abstract boolean canMoveTo(Vector2D position);

    public abstract boolean place(Animal animal);

    public abstract boolean isOccupied(Vector2D position);

    public abstract Object objectAt(Vector2D position);

    public abstract String toString();

    public abstract void updateKeyInAnimalMap(Vector2D previousAnimalPosition, Vector2D newAnimalPosition);
}
