package agh.ics.oop;

import java.util.Map;

public interface IWorldMap {

    boolean canMoveTo(Vector2D position);

    //Place the animal on the map.
    boolean place(Animal animal);

    boolean removeFromMap(Animal animal);

    boolean isOccupied(Vector2D position);

    Animal moveAnimalOnMap(Animal animal, MoveDirection moveDirection);
    Animal[] getAnimalsOnMapArray();

    Object objectAt(Vector2D position);

    void updateMap();

    void activateGUI();
}
