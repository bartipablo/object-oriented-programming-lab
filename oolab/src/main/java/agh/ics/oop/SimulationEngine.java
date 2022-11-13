package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] moveDirectionsArray;
    private final IWorldMap mapInstance;
    private final List<Animal> animalsOnMap = new ArrayList<>();
    private final Frame frame = new Frame();

    SimulationEngine(MoveDirection[] moveDirectionsArray, IWorldMap mapInstance, Vector2D[] initialAnimalPositionOnMap) {
        this.moveDirectionsArray  = moveDirectionsArray;
        this.mapInstance = mapInstance;
        for (Vector2D vector2D : initialAnimalPositionOnMap) {
            animalsOnMap.add(new Animal(mapInstance, vector2D));
        }
        setAnimalsOnMap(mapInstance, animalsOnMap);
        frame.updateFrame(mapInstance.toString());
        frame.setVisible(true);
    }

    public void run() throws InterruptedException {
        int i = 0;
        int arrayLength = animalsOnMap.size();
        for (MoveDirection moveDirection : moveDirectionsArray) {
            TimeUnit.MILLISECONDS.sleep(400);
            moveAnimal(i % arrayLength, moveDirection);
            frame.updateFrame(mapInstance.toString());
            i++;
        }
    }

    private void setAnimalsOnMap(IWorldMap map, List<Animal> animalsOnMap) {
        for (Animal animal : animalsOnMap) {
            map.place(animal);
        }
    }

    private void moveAnimal(int animalIndex, MoveDirection moveDirection) {
        Animal animal = animalsOnMap.get(animalIndex);
        Vector2D previousAnimalPosition = animal.getPosition();
        animal.move(moveDirection);
        Vector2D newAnimalPosition = animal.getPosition();
        mapInstance.changeAnimalPosition(previousAnimalPosition, newAnimalPosition);
    }


}