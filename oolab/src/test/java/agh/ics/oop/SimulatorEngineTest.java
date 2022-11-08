package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulatorEngineTest {
    @Test
    public void runTest() throws InterruptedException {
        Vector2D[] initialPositions = { new Vector2D(2,2), new Vector2D(3,4) };
        IWorldMap map = new RectangularMap(10, 5);

        MoveDirection[] actions = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT,
                MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD};

        IEngine engine = new SimulationEngine(actions, map, initialPositions);
        engine.run();

        // Sprawdzamy pozycje, które powinny być zajęte
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i == 0 && j == 3) || (i == 0 && j == 4)) {
                    assertTrue(map.isOccupied(new Vector2D(i, j)));
                } else {
                    assertFalse(map.isOccupied(new Vector2D(i, j)));
                }
            }
        }

        // Sprawdzamy orientacje zwierząt
        Animal animal1 = (Animal) map.objectAt(new Vector2D(0, 3));
        Animal animal2 = (Animal) map.objectAt(new Vector2D(0, 4));
        assertAll(
                () -> assertEquals(animal1.getDirection(), MapDirection.NORTH),
                () -> assertEquals(animal2.getDirection(), MapDirection.SOUTH)
        );
    }

}
