package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationEngineTest {

    // Test simulation engine with rectangular map
    @Test
    public void runRectangularMapTest() throws InterruptedException {
        Vector2D[] initialPositions = { new Vector2D(2,2), new Vector2D(3,4) };
        IWorldMap map = new RectangularMap(10, 5);

        MoveDirection[] actions = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT,
                MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD};

        IEngine engine = new SimulationEngine(actions, map, initialPositions);
        engine.run();

        // occupied position test
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i == 0 && j == 3) || (i == 0 && j == 4)) {
                    assertTrue(map.isOccupied(new Vector2D(i, j)));
                } else {
                    assertFalse(map.isOccupied(new Vector2D(i, j)));
                }
            }
        }

        // direction test
        Animal animal1 = (Animal) map.objectAt(new Vector2D(0, 3));
        Animal animal2 = (Animal) map.objectAt(new Vector2D(0, 4));
        assertAll(
                () -> assertEquals(animal1.getDirection(), MapDirection.NORTH),
                () -> assertEquals(animal2.getDirection(), MapDirection.SOUTH)
        );
    }


    // test simulation engine with grass-field
    @Test
    public void runGrassFieldTest() throws InterruptedException {
        Vector2D[] initialPositions = { new Vector2D(1,2), new Vector2D(2,2) };
        IWorldMap map = new GrassField(4);

        MoveDirection[] actions = {MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};

        IEngine engine = new SimulationEngine(actions, map, initialPositions);
        engine.run();

        // occupied position
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                if ((i == 0 && j == 3) || (i == 8 && j == 6)) {
                    assertFalse(map.canMoveTo(new Vector2D(i, j)));
                } else {
                    assertTrue(map.canMoveTo(new Vector2D(i, j)));
                }
            }
        }

        // direction test
        Animal animal1 = (Animal) map.objectAt(new Vector2D(0, 3));
        Animal animal2 = (Animal) map.objectAt(new Vector2D(8, 6));
        assertAll(
                () -> assertEquals(animal1.getDirection(), MapDirection.NORTH),
                () -> assertEquals(animal2.getDirection(), MapDirection.EAST)
        );
    }

}
