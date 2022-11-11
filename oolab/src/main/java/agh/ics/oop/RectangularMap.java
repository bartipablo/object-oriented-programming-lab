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

    public boolean isOccupied(Vector2D position) {
        return animalsOnMap.get(position) != null;
    }

    public boolean canMoveTo(Vector2D position) {
        if (position.x < 0 || position.y < 0 || position.x >= width || position.y >= height){
            return false;
        }
        return !isOccupied(position);
    }


    public Object objectAt(Vector2D position) {
        return animalsOnMap.get(position);
    }

    public String toString() {
        return mapVisualizer.draw(new Vector2D(0, 0), new Vector2D(width - 1, height -1));
    }

}