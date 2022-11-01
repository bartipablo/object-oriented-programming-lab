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
        Map<Vector2D, Animal> animalsMap = mapInstance.getMap();
        Animal[] animalsAtMapArray = animalsMap.values().toArray(new Animal[0]);
        int i = 0;
        for (MoveDirection direction : moveDirectionsArray) {
            Animal animal = animalsAtMapArray[i % animalsAtMapArray.length];
            animalsMap.remove(animal.getPosition());
            animal.move(direction);
            animalsMap.put(animal.getPosition(), animal);
            i++;
            System.out.println(mapInstance.drawMap());
        }
    }


}
