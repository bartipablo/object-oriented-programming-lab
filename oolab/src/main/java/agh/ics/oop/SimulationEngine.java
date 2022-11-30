package agh.ics.oop;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] moveDirectionsArray;
    private final IWorldMap mapInstance;
    private final List<Animal> animalsOnMapList = new ArrayList<>();
    private final Frame frame = new Frame();

    public SimulationEngine(MoveDirection[] moveDirectionsArray, IWorldMap mapInstance, Vector2D[] initialAnimalPositionOnMap) {
        this.moveDirectionsArray  = moveDirectionsArray;
        this.mapInstance = mapInstance;
        initialAnimals(initialAnimalPositionOnMap);
        setAnimalsOnMap(mapInstance, animalsOnMapList);
    }

    public void run() {
        int i = 0;
        for (MoveDirection moveDirection : moveDirectionsArray) {
            Animal animalToMove = animalsOnMapList.get(i % animalsOnMapList.size());
            animalToMove.move(moveDirection);
            i++;
        }
    }

    private void setAnimalsOnMap(IWorldMap map, List<Animal> animalsOnMap) {
        for (Animal animal : animalsOnMap) {
            map.place(animal);
        }
    }

    private void initialAnimals(Vector2D[] initialAnimalPosition) {
        for (Vector2D vector2D : initialAnimalPosition) {
            Animal animal = new Animal(mapInstance, vector2D);
            animal.addObserver((IPositionChangeObserver) mapInstance);
            animalsOnMapList.add(animal);
        }
    }

}