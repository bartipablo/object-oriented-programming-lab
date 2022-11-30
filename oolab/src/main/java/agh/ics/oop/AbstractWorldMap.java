package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final Map<Vector2D, Animal> animalsOnMap = new HashMap<>();

    protected final MapBoundary mapBoundary = new MapBoundary();

    @Override
    public boolean isOccupied(Vector2D position) {
        return objectAt(position) != null;
    }

    public abstract Vector2D getLowerMapLimit();
    public abstract Vector2D getUpperMapLimit();

}
