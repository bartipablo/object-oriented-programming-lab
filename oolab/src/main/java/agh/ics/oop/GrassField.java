package agh.ics.oop;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2D, Grass> grassesOnMap = new HashMap<>();
    private final int amountGrassField;

    public GrassField(int initialAmountGrassField) {
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
            mapBoundary.add(animal.getPosition());
            if (grassesOnMap.get(animal.getPosition()) != null) {
                grassesOnMap.remove(animal.getPosition());
                setGrassOnRandomPosition();
            }
            return true;
        }
        throw new IllegalArgumentException("the animal cannot be placed in position " + animal.getPosition());
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
        mapBoundary.positionChanged(previousAnimalPosition, newAnimalPosition);
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
                mapBoundary.add(new Vector2D(x, y));
                return;
            }
        }
    }

    private void initialGrassesField(int amountGrassField) {
        List<Vector2D> grassesPosition = new ArrayList<>();
        generateRandomGrassesField(grassesPosition, amountGrassField);
        setGrassesOnMap(grassesPosition);
    }

    private void setGrassesOnMap(List<Vector2D> grassesPosition) {
        for (Vector2D grassPosition : grassesPosition) {
            Grass grass = new Grass(grassPosition);
            grassesOnMap.put(grassPosition, grass);
            mapBoundary.add(grassPosition);
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

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Vector2D upperLimit = mapBoundary.getUpperLimit();
        Vector2D lowerLimit = mapBoundary.getLowerLimit();
        return mapVisualizer.draw(lowerLimit, upperLimit);
    }

    @Override
    public Vector2D getUpperMapLimit() {
        return mapBoundary.getUpperLimit();
    }

    @Override
    public Vector2D getLowerMapLimit() {
        return mapBoundary.getLowerLimit();
    }

}
