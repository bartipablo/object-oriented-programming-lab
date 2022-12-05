package agh.ics.oop;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine, Runnable {
    private final MoveDirection[] moveDirectionsArray;
    private final IWorldMap mapInstance;
    private final List<Animal> animalsOnMapList = new ArrayList<>();
    private final List<IGuiObserver> observers = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moveDirectionsArray, IWorldMap mapInstance, Vector2D[] initialAnimalPositionOnMap) {
        this.moveDirectionsArray  = moveDirectionsArray;
        this.mapInstance = mapInstance;
        initialAnimals(initialAnimalPositionOnMap);
        setAnimalsOnMap(mapInstance, animalsOnMapList);
    }

    @Override
    public void run() {
        int i = 0;
        for (MoveDirection moveDirection : moveDirectionsArray) {
            Animal animalToMove = animalsOnMapList.get(i % animalsOnMapList.size());
            animalToMove.move(moveDirection);
            try {
                informObserversAboutChanges();
            } catch (FileNotFoundException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }

    public void addObserver(IGuiObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IGuiObserver observer) {
        observers.remove(observer);
    }

    public void informObserversAboutChanges() throws FileNotFoundException, InterruptedException {
        for (IGuiObserver observer : observers) {
            observer.positionChanged();
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