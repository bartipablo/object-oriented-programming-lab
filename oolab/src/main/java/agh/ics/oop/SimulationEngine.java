package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] moveDirectionsArray;
    private final IWorldMap mapInstance;
    private IWorldMap map;
    private List<Animal> animalsOnMap = new ArrayList<>();
    private final Frame frame = new Frame();

    SimulationEngine(MoveDirection[] moveDirectionsArray, IWorldMap mapInstance, Vector2D[] initialAnimalPositionOnMap) {
        this.moveDirectionsArray  = moveDirectionsArray;
        this.mapInstance = mapInstance;
        this.map = mapInstance.clone();
        for (Vector2D vector2D : initialAnimalPositionOnMap) {
            animalsOnMap.add(new Animal(mapInstance, vector2D));
        }
    }

    public void run() throws InterruptedException {
        frame.setVisible(true);
        int i = 0;
        int arrayLength = animalsOnMap.size();
        for (MoveDirection moveDirection : moveDirectionsArray) {
            boolean canMove = moveAnimal(map, animalsOnMap, i % arrayLength, moveDirection);
            if (canMove) {
                map = mapInstance.clone();
                updateAnimalList(map, animalsOnMap);
                setAnimalsOnMap(map, animalsOnMap);
                frame.updateFrame(map.toString());
                TimeUnit.MILLISECONDS.sleep(300);
            }
            i++;
        }
        frame.setVisible(false);
    }

    private void setAnimalsOnMap(IWorldMap map, List<Animal> animalsOnMap) {
        for (Animal animal : animalsOnMap) {
            map.place(animal);
        }
    }

    private boolean moveAnimal(IWorldMap map, List<Animal> animalsOnMap, int animalIndex, MoveDirection moveDirection) {
        Animal animal = animalsOnMap.get(animalIndex);
        if (moveDirection == MoveDirection.LEFT || moveDirection == MoveDirection.RIGHT || map.canMoveTo(animal.move(moveDirection).getPosition())) {
            animalsOnMap.set(animalIndex, animal.move(moveDirection));
            return true;
        }
        return false;
    }

    private void updateAnimalList(IWorldMap map, List<Animal> animalsOnMap) {
        animalsOnMap.replaceAll(animal1 -> animal1.changeMap(map));
    }

}