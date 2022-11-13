package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GrassFieldTest {
    @Test
    public void canMoveToTest() {
        IWorldMap map = new GrassField(10);
        map.place(new Animal(map, new Vector2D(2, 2)));
        assertAll(
                () -> assertTrue(map.canMoveTo(new Vector2D(1, 2))),
                () -> assertTrue(map.canMoveTo(new Vector2D(100, 200))),
                () -> assertFalse(map.canMoveTo(new Vector2D(2, 2))),
                () -> assertFalse(map.canMoveTo(new Vector2D(-1, 3))),
                () -> assertFalse(map.canMoveTo(new Vector2D(3, -1))),
                () -> assertFalse(map.canMoveTo(new Vector2D(-1, -1)))
        );
    }

    @Test
    public void isOccupiedTest() {
        IWorldMap map = new GrassField(5);
        map.place(new Animal(map, new Vector2D(2, 2)));
        assertAll(
                () -> assertTrue(map.isOccupied(new Vector2D(2, 2)))
        );
    }

    @Test
    public void placeTest() {
        IWorldMap map = new GrassField(5);
        map.place(new Animal(map, new Vector2D(2, 2)));
        assertAll(
                () -> assertFalse(map.place(new Animal(map, new Vector2D(2, 2)))),
                () -> assertTrue(map.place(new Animal(map, new Vector2D(10, 10)))),
                () -> assertTrue(map.place(new Animal(map, new Vector2D(1, 1))))
        );
    }

    @Test
    public void objectAtTest() {
        IWorldMap map = new GrassField(5);
        Animal animal1 = new Animal(map, new Vector2D(1, 1));
        Animal animal2 = new Animal(map, new Vector2D(3, 4));
        map.place(animal1);
        map.place(animal2);
        Animal animal3 = (Animal) map.objectAt(new Vector2D(1, 1));
        Animal animal4 = (Animal) map.objectAt(new Vector2D(3, 4));
        assertAll(
                () -> assertEquals(animal1, animal3),
                () -> assertEquals(animal2, animal4),
                () -> assertNotEquals(animal1, animal4),
                () -> assertNotEquals(animal2, animal3)
        );
    }

    @Test
    public void updateKeyInAnimalMapTest() {
        IWorldMap map = new GrassField(5);
        map.place(new Animal(map, new Vector2D(2, 2)));
        map.updateKeyInAnimalMap(new Vector2D(2, 2), new Vector2D(3, 3));
        assertAll(
                () -> assertTrue(map.isOccupied(new Vector2D(3, 3))),
                () -> assertFalse(map.isOccupied(new Vector2D(2, 2))),
                () -> assertFalse(map.isOccupied(new Vector2D(1, 1)))
        );
    }
}
