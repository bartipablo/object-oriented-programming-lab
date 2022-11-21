package agh.ics.oop;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2D, Grass> grassesOnMap = new HashMap<>();

    private final int amountGrassField;

    GrassField(int initialAmountGrassField) {
        amountGrassField = initialAmountGrassField;
        initialGrassesField(initialAmountGrassField);
    }


    @Override
    public boolean canMoveTo(Vector2D position) {
        return position.x >= 0 && position.y >= 0 && animalsOnMap.get(position) == null;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animalsOnMap.put(animal.getPosition(), animal);
            if (grassesOnMap.get(animal.getPosition()) != null) {
                grassesOnMap.remove(animal.getPosition());
                setGrassOnRandomPosition();
            }
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2D position) {
        if (animalsOnMap.get(position) != null) {
            return animalsOnMap.get(position);
        }
        return grassesOnMap.get(position);
    }

    @Override
    public void positionChanged(Vector2D previousAnimalPosition, Vector2D newAnimalPosition) {
        Animal animal = animalsOnMap.get(previousAnimalPosition);
        animalsOnMap.remove(previousAnimalPosition);
        animalsOnMap.put(newAnimalPosition, animal);
        if (grassesOnMap.get(newAnimalPosition) != null) {
            grassesOnMap.remove(newAnimalPosition);
            setGrassOnRandomPosition();
        }
    }

    private void setGrassOnRandomPosition() {
        int mapSizeLimit = (int) sqrt(amountGrassField * 10);
        Random random = new Random();
        while (true) {
            int x = random.nextInt(0, mapSizeLimit + 1);
            int y = random.nextInt(0, mapSizeLimit + 1);
            if (!isOccupied(new Vector2D(x, y))) {
                Grass grass = new Grass(new Vector2D(x, y));
                grassesOnMap.put(new Vector2D(x, y), grass);
                return;
            }
        }
    }

    // Grass initializer -------------------------------------------
    private void initialGrassesField(int amountGrassField) {
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

    // toString ------------------------------------------------------------
    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Vector2D upperLimit = calculateMapLimit();
        Vector2D lowerLimit = new Vector2D(0, 0);
        return mapVisualizer.draw(lowerLimit, upperLimit);
    }

    private Vector2D calculateMapLimit() {
        int[] mapSizeLimit = {0, 0};
        Vector2D[] grassesPosition = grassesOnMap.keySet().toArray(new Vector2D[0]);
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
    //-------------------------------------------------------------

}
