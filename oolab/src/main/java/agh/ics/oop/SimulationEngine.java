package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] moveDirectionsArray;
    private IWorldMap mapInstance;
    private IWorldMap map;
    private final List<Animal> animalsOnMap = new ArrayList<>();
    private final Frame frame = new Frame();

    SimulationEngine(MoveDirection[] moveDirectionsArray, IWorldMap mapInstance, Vector2D[] initialAnimalPositionOnMap) {
        this.moveDirectionsArray  = moveDirectionsArray;
        this.mapInstance = mapInstance;
        this.map = mapInstance.clone();
        for (Vector2D vector2D : initialAnimalPositionOnMap) {
            animalsOnMap.add(new Animal(map, vector2D));
        }
        setAnimalsOnMap(map, animalsOnMap);
    }

    public void run() throws InterruptedException {
        frame.updateFrame(map.toString());
        frame.setVisible(true);
        int i = 0;
        int arrayLength = animalsOnMap.size();
        for (MoveDirection moveDirection : moveDirectionsArray) {
            TimeUnit.MILLISECONDS.sleep(400);
            boolean canMove = moveAnimalInArray(map, animalsOnMap, i % arrayLength, moveDirection);
            if (canMove) {
                map = mapInstance.clone();
                setAnimalsOnMap(map, animalsOnMap);
                frame.updateFrame(map.toString());
            }
            i++;
        }
        setAnimalsOnMap(mapInstance, animalsOnMap);
    }

    private void setAnimalsOnMap(IWorldMap map, List<Animal> animalsOnMap) {
        for (Animal animal : animalsOnMap) {
            map.place(animal);
        }
    }

    private boolean moveAnimalInArray(IWorldMap map, List<Animal> animalsOnMap, int animalIndex, MoveDirection moveDirection) {
        Animal animal = animalsOnMap.get(animalIndex);
        if (moveDirection == MoveDirection.LEFT || moveDirection == MoveDirection.RIGHT || map.canMoveTo(animal.move(moveDirection).getPosition())) {
            animalsOnMap.set(animalIndex, animal.move(moveDirection));
            return true;
        }
        return false;
    }

}