package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] moveDirectionsArray;
    private IWorldMap mapInstance;

    private MapVisualizer mapVisualizer;

    private final List<Animal> animalsOnMap = new ArrayList<>();
    private  final Frame frame;
    private final int mapHeight;
    private final int mapWidth;

    SimulationEngine(MoveDirection[] moveDirectionsArray, IWorldMap mapInstance, Vector2D[] animalsPositionArray) {
        this.moveDirectionsArray  = moveDirectionsArray;
        this.mapInstance = mapInstance;
        mapWidth  = mapInstance.getWidth();
        mapHeight = mapInstance.getHeight();
        for (Vector2D animalPosition : animalsPositionArray) {
            Animal animal = new Animal(mapInstance, animalPosition);
            if (mapInstance.place(animal)) {
                animalsOnMap.add(animal);
            }
        }
        frame = new Frame();
    }


    public void removeAnimalsFromMap(List<Animal> animalsOnMap) {
        for(Animal animal: animalsOnMap) {
            mapInstance.removeFromMap(animal);
        }
    }
    private void addAnimalsOnMap(List<Animal> animalsOnMap) {
        for (Animal animal : animalsOnMap) {
            mapInstance.place(animal);
        }
    }

    public void run() throws InterruptedException {
        frame.setVisible(true);
        int listSize = animalsOnMap.size();
        int i = 0;
        for (MoveDirection moveDirection : moveDirectionsArray) {
            MapVisualizer mapVisualizer = new MapVisualizer(mapInstance);
            frame.updateFrame(mapVisualizer.draw(new Vector2D(0, 0), new Vector2D(mapWidth - 1, mapHeight - 1)));
            Animal animal = animalsOnMap.get(i % listSize);
            if (moveDirection == MoveDirection.LEFT || moveDirection == MoveDirection.RIGHT ||
                    mapInstance.canMoveTo(animal.move(moveDirection).getPosition())) {
                removeAnimalsFromMap(animalsOnMap);
                animalsOnMap.set(i % listSize, animal.move(moveDirection));
                addAnimalsOnMap(animalsOnMap);
            }
            TimeUnit.MILLISECONDS.sleep(300);
            i++;
        }
        frame.updateFrame(mapVisualizer.draw(new Vector2D(0, 0), new Vector2D(mapWidth - 1, mapHeight - 1)));
    }


}
