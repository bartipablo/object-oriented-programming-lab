package agh.ics.oop;

import java.util.Map;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] moveDirectionsArray;
    private final IWorldMap mapInstance;

    SimulationEngine(MoveDirection[] moveDirectionsArray,
                     IWorldMap mapInstance, Vector2D[] animalsPositionArray) {
        this.moveDirectionsArray  = moveDirectionsArray;
        this.mapInstance = mapInstance;
        for (Vector2D animalPosition : animalsPositionArray) {
            mapInstance.place(new Animal(mapInstance, animalPosition));
        }
    }

    public void run() {
        Animal[] animalsOnMapArray = mapInstance.getAnimalsOnMapArray();
        int i = 0;
        for (MoveDirection direction : moveDirectionsArray) {
            Animal animal = animalsOnMapArray[i % animalsOnMapArray.length];
            animal = mapInstance.moveAnimalOnMap(animal, direction);
            animalsOnMapArray[i % animalsOnMapArray.length] = animal;
            i++;
            System.out.println(mapInstance.drawMap());
        }
    }


}
