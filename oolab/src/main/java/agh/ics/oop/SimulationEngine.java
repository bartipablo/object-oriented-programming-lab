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
    }

    public void run() throws InterruptedException {
        frame.setVisible(true);
        int i = 0;
        for (MoveDirection moveDirection : moveDirectionsArray) {
            TimeUnit.MILLISECONDS.sleep(400);
            Animal animalToMove = animalsOnMap.get(i % animalsOnMap.size());
            animalToMove.move(moveDirection);
            frame.updateFrame(mapInstance.toString());
            i++;
        }
        TimeUnit.MILLISECONDS.sleep(400);
    }

    private void setAnimalsOnMap(IWorldMap map, List<Animal> animalsOnMap) {
        for (Animal animal : animalsOnMap) {
            map.place(animal);
        }
    }

}