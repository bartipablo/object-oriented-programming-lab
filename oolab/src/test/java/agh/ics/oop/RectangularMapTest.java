package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {

    @Test
    public void canMoveToTest() {
        IWorldMap map = new RectangularMap(5, 5);
        map.place(new Animal(map, new Vector2D(2, 2)));
        assertAll(
                () -> assertTrue(map.canMoveTo(new Vector2D(1, 2))),
                () -> assertFalse(map.canMoveTo(new Vector2D(2, 2))),
                () -> assertFalse(map.canMoveTo(new Vector2D(4, 10))),
                () -> assertFalse(map.canMoveTo(new Vector2D(10, 4))),
                () -> assertFalse(map.canMoveTo(new Vector2D(10, 10)))
        );
    }

    @Test
    public void isOccupiedTest() {
        IWorldMap map = new RectangularMap(5, 5);
        map.place(new Animal(map, new Vector2D(2, 2)));
        assertAll(
                () -> assertTrue(map.isOccupied(new Vector2D(2, 2))),
                () -> assertFalse(map.isOccupied(new Vector2D(1, 1))),
                () -> assertFalse(map.isOccupied(new Vector2D(10, 10)))
        );
    }

    @Test
    public void placeTest() {
        IWorldMap map = new RectangularMap(5, 5);
        map.place(new Animal(map, new Vector2D(2, 2)));
        assertAll(
                () -> assertFalse(map.place(new Animal(map, new Vector2D(2, 2)))),
                () -> assertFalse(map.place(new Animal(map, new Vector2D(10, 10)))),
                () -> assertTrue(map.place(new Animal(map, new Vector2D(1, 1))))
        );
    }

    @Test
    public void removeFromMapTest() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(map, new Vector2D(1, 1));
        Animal animal2 = new Animal(map, new Vector2D(2, 2));
        map.place(animal1);
        map.place(animal2);
        assertAll(
                () -> assertTrue(map.removeFromMap(animal1)),
                () -> assertFalse(map.removeFromMap(new Animal(map, new Vector2D(3, 3)))),
                () -> assertTrue(map.canMoveTo(animal1.getPosition())),
                () -> assertFalse(map.canMoveTo(animal2.getPosition()))
        );
    }

    @Test
    public void moveAnimalOnMap() {
        IWorldMap map = new RectangularMap(5, 5);
        map.place(new Animal(map, new Vector2D(1, 1)));
        map.place(new Animal(map, new Vector2D(2, 2)));
        map.place(new Animal(map, new Vector2D(1, 3)));
        map.moveAnimalOnMap(new Animal(map, new Vector2D(2, 2)), MoveDirection.FORWARD);
        map.moveAnimalOnMap(new Animal(map, new Vector2D(2, 3)), MoveDirection.LEFT);
        map.moveAnimalOnMap(new Animal(map, new Vector2D(2,3), MapDirection.WEST), MoveDirection.FORWARD);
        assertAll(
                () -> assertTrue(map.isOccupied(new Vector2D(1, 3))),
                () -> assertTrue(map.isOccupied(new Vector2D(2, 3))),
                () -> assertTrue(map.isOccupied(new Vector2D(1, 1))),
                () -> assertFalse(map.isOccupied(new Vector2D(2, 2)))
        );
    }

    @Test
    public void getAnimalsOnMapTest() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map, new Vector2D(3, 3));
        map.place(animal1);
        map.place(animal2);
        Animal[] expectedArray = map.getAnimalsOnMapArray();
        Animal[] actualArray = {animal1, animal2};
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void objectAtTest() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(map, new Vector2D(1, 1));
        map.place(animal);
        Object testObject1 = animal;
        Object testObject2 = map.objectAt(new Vector2D(1, 1));
        Object testObject3 = map.objectAt(new Vector2D(3, 3));
        assertAll(
                () -> assertEquals(testObject1, testObject2),
                () -> assertNotEquals(testObject2, testObject3)
        );

    }

}