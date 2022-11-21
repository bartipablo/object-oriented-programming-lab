package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final Map<Vector2D, Animal> animalsOnMap = new HashMap<>();

    @Override
    public boolean isOccupied(Vector2D position) {
        return objectAt(position) != null;
    }

}
