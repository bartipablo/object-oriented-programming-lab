package agh.ics.oop;

public interface IWorldMap {

    //return True if the object can move to that position.
    boolean canMoveTo(Vector2D position);

    //Place a animal on the map.
    //return True if the animal was placed. The animal cannot be placed if the map is already occupied.
    boolean place(Animal animal);

    //return true if given position on the map is occupied.
    boolean isOccupied(Vector2D position);

    //return an object at a given position.
    Object objectAt(Vector2D position);

    void moveOnMap(Vector2D position, MoveDirection direction);

    Vector2D[] getKeys();
}
