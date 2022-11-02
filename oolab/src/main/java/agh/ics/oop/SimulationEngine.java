package agh.ics.oop;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] moveDirectionsArray;
    private final IWorldMap mapInstance;
    private final Frame frame = new Frame();

    SimulationEngine(MoveDirection[] moveDirectionsArray,
                     IWorldMap mapInstance, Vector2D[] animalsPositionArray) {
        this.moveDirectionsArray  = moveDirectionsArray;
        this.mapInstance = mapInstance;
        for (Vector2D animalPosition : animalsPositionArray) {
            mapInstance.place(new Animal(mapInstance, animalPosition));
        }
    }

    public void run() throws InterruptedException {
        frame.setVisible(true);
        frame.updateFrame(mapInstance.toString());
        Animal[] animalsOnMapArray = mapInstance.getAnimalsOnMapArray();
        int i = 0;
        for (MoveDirection direction : moveDirectionsArray) {
            TimeUnit.MILLISECONDS.sleep(300);
            Animal animal = animalsOnMapArray[i % animalsOnMapArray.length];
            animal = mapInstance.moveAnimalOnMap(animal, direction);
            animalsOnMapArray[i % animalsOnMapArray.length] = animal;
            i++;
            frame.updateFrame(mapInstance.toString());
        }
    }


}