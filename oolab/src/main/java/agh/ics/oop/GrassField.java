package agh.ics.oop;

import javax.swing.text.Position;
import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField implements IWorldMap {

    private final int amountGrassField;
    private final Map<Vector2D, Animal> animalsOnMap = new HashMap<>();
    private final Map<Vector2D, Grass> grassesOnMap = new HashMap<>();

    GrassField(int initialAmountGrassField) {
        amountGrassField = initialAmountGrassField;
        initialGrassesField(initialAmountGrassField, grassesOnMap);
    }

    // implementation methods from interface ----------------------
    @Override
    public boolean canMoveTo(Vector2D position) {
        if (position.x < 0 || position.y < 0){
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
        return false;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return animalsOnMap.get(position) != null;
    }

    @Override
    public Object objectAt(Vector2D position) {
        if (animalsOnMap.get(position) != null) {
            return animalsOnMap.get(position);
        }
        if (grassesOnMap.get(position) != null) {
            return grassesOnMap.get(position);
        }
        return null;
    }

    @Override
    public IWorldMap clone() {
        return null;
    }
    // ------------------------------------------------------------

    // Grass initializer -------------------------------------------
    private void initialGrassesField(int amountGrassField, Map<Vector2D, Grass> grassesOnMap) {
        List<Vector2D> grassesPosition = new ArrayList<>();
        generateRandomGrassesField(grassesPosition, amountGrassField);
        setGrassesOnMap(grassesPosition);
    }

    private void setGrassesOnMap(List<Vector2D> grassesPosition) {
        for (Vector2D grassPosition : grassesPosition) {
            Grass grass = new Grass(grassPosition);
            grassesOnMap.put(grassPosition, grass);
        }
    }

    private void generateRandomGrassesField(List<Vector2D> grassesPosition, int amountGrassField) {
        int positionLimit = (int) sqrt(amountGrassField * 10);
        Random rn = new Random();
        int i = 0;
        while (i < amountGrassField) {
            Vector2D vector2D = new Vector2D(rn.nextInt(0, positionLimit), rn.nextInt(0, positionLimit));
            if (!grassesPosition.contains(vector2D)) {
                grassesPosition.add(vector2D);
                i++;
            }
        }
    }
    // ---------------------------------------------------------------------

    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Vector2D upperLimit = calculateMapLimit();
        Vector2D lowerLimit = new Vector2D(0, 0);
        return mapVisualizer.draw(lowerLimit, upperLimit);
    }

    private Vector2D calculateMapLimit() {
        int[] mapSizeLimit = {0, 0};
        Vector2D[] grassesPosition = animalsOnMap.keySet().toArray(new Vector2D[0]);
        Vector2D[] animalsPosition = animalsOnMap.keySet().toArray(new Vector2D[0]);
        for (Vector2D grassPosition : grassesPosition) {
            if (grassPosition.x > mapSizeLimit[0]) mapSizeLimit[0] = grassPosition.x;
            if (grassPosition.y > mapSizeLimit[1]) mapSizeLimit[1] = grassPosition.y;
            }
        for (Vector2D animalPosition : animalsPosition) {
            if (animalPosition.x > mapSizeLimit[0]) mapSizeLimit[0] = animalPosition.x;
            if (animalPosition.y > mapSizeLimit[1]) mapSizeLimit[1] = animalPosition.y;
        }
        return new Vector2D(mapSizeLimit[0], mapSizeLimit[1]);
    }
}
