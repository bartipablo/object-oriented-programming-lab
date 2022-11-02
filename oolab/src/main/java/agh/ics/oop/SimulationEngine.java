package agh.ics.oop;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] moveDirectionsArray;
    private final IWorldMap mapInstance;
    private final Frame frame = new Frame();

    SimulationEngine(MoveDirection[] moveDirectionsArray, IWorldMap mapInstance, Vector2D[] animalsPositionArray) {
        this.moveDirectionsArray  = moveDirectionsArray;
        this.mapInstance = mapInstance;
        for (Vector2D animalPosition : animalsPositionArray) {
            mapInstance.place(new Animal(mapInstance, animalPosition));
        }
        frame.setVisible(true);
        frame.updateFrame(mapInstance.toString());
    }

    public void run() throws InterruptedException {
        Animal[] animalsOnMapArray = mapInstance.getAnimalsOnMapArray();
        int i = 0;
        for (MoveDirection direction : moveDirectionsArray) {
            TimeUnit.MILLISECONDS.sleep(300);
            simulateMove(direction, animalsOnMapArray, i);
            i++;
            frame.updateFrame(mapInstance.toString());
        }
        TimeUnit.MILLISECONDS.sleep(300);
    }

    private void simulateMove(MoveDirection direction, Animal[] animalsOnMapArray, int arrayIndex) {
        Animal animal = animalsOnMapArray[arrayIndex % animalsOnMapArray.length];
        animal = mapInstance.moveAnimalOnMap(animal, direction);
        animalsOnMapArray[arrayIndex % animalsOnMapArray.length] = animal;
    }


}