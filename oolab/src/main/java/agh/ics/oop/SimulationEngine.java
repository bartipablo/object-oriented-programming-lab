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
        frame.setVisible(true);
    }

    public void run() throws InterruptedException {
        int i = 0;
        int arrayLength = animalsOnMap.size();
        for (MoveDirection moveDirection : moveDirectionsArray) {
            TimeUnit.MILLISECONDS.sleep(400);
            boolean wasMoved = moveAnimal(mapInstance, animalsOnMap, i % arrayLength, moveDirection);
            if (wasMoved) {
                frame.updateFrame(mapInstance.toString());
            }
            i++;
        }
    }

    private void setAnimalsOnMap(IWorldMap map, List<Animal> animalsOnMap) {
        for (Animal animal : animalsOnMap) {
            map.place(animal);
        }
    }

    private boolean moveAnimal(IWorldMap map, List<Animal> animalsOnMap, int animalIndex, MoveDirection moveDirection) {
        Animal animalToMove = animalsOnMap.get(animalIndex);
        Vector2D previousPosition = animalToMove.getPosition();
        animalToMove.move(moveDirection);
        if (moveDirection == MoveDirection.LEFT || moveDirection == MoveDirection.RIGHT) {
            return true;
        }
        if (map.canMoveTo(animalToMove.getPosition())) {
            map.changeAnimalPosition(previousPosition, animalToMove.getPosition());
            return true;
        }
        animalToMove.move(moveDirection.oppositeMoveDirection());
        return false;
    }

}