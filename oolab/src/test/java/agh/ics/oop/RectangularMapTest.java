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