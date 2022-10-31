package agh.ics.oop;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] moveDirectionsArray;
    private final IWorldMap mapInstance;
    private Vector2D[] animalsPositionArray;

    SimulationEngine(MoveDirection[] moveDirectionsArray,
                     IWorldMap mapInstance, Vector2D[] animalsPositionArray) {
        this.moveDirectionsArray  = moveDirectionsArray;
        this.mapInstance = mapInstance;
        this.animalsPositionArray = animalsPositionArray;
        for (Vector2D animalPosition : animalsPositionArray) {
            mapInstance.place(new Animal(mapInstance, animalPosition));
        }
    }

    public void run() {
        int arrayLength = moveDirectionsArray.length;
        int i = 0;
        Vector2D[] arrayKeys = mapInstance.getKeys();
        for (MoveDirection moveDirection : moveDirectionsArray) {
            mapInstance.moveOnMap(arrayKeys[i % arrayLength], moveDirection);
            i++;
        }
    }
}
