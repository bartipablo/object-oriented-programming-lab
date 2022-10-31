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
        MapVisualizer mapVisualizer = new MapVisualizer(mapInstance);
        int i = 0;
        Vector2D[] arrayKeys = mapInstance.getKeys();
        for (MoveDirection moveDirection : moveDirectionsArray) {
            mapInstance.moveOnMap(arrayKeys[i % arrayKeys.length], moveDirection);
            i++;
            System.out.println(mapVisualizer.draw(new Vector2D(0, 0), new Vector2D(mapInstance.getWidth() - 1, mapInstance.getHeight() - 1)));
        }
    }
}
