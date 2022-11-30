package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;

public class MapBoundary implements IPositionChangeObserver {
    private final SortedSet<Vector2D> xSet = new TreeSet<>(Comparator.comparingInt(vector2D -> vector2D.x));
    private final SortedSet<Vector2D> ySet = new TreeSet<>(Comparator.comparingInt(vector2D -> vector2D.y));

    @Override
    public void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        remove(oldPosition);
        add(newPosition);
    }

    public void remove(Vector2D vector2D) {
        xSet.remove(vector2D);
        ySet.remove(vector2D);
    }

    public void add(Vector2D vector2D) {
        xSet.add(vector2D);
        ySet.add(vector2D);
    }

    public Vector2D getUpperLimit() {
        return new Vector2D(xSet.last().x, ySet.last().y);
    }

    public Vector2D getLowerLimit() {
        return new Vector2D(xSet.first().x, ySet.first().y);
    }


}

