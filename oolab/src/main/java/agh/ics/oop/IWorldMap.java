package agh.ics.oop;

public interface IWorldMap {
    boolean canMoveTo(Vector2D position);

    boolean place(Animal animal);

    boolean isOccupied(Vector2D position);

    Object objectAt(Vector2D position);

}