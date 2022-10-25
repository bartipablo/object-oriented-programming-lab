package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    @Test
    public void toStringTest() {
        Animal testObject = new Animal();
        assertEquals(testObject.toString(), "(2, 2) Północ");

        testObject.move(MoveDirection.RIGHT);
        assertEquals(testObject.toString(), "(2, 2) Wschód");

        testObject.move(MoveDirection.RIGHT);
        assertEquals(testObject.toString(), "(2, 2) Południe");

        testObject.move(MoveDirection.RIGHT);
        assertEquals(testObject.toString(), "(2, 2) Zachód");
    }

    @Test
    public void isAtTest() {
        Animal testObject = new Animal();
        assertAll(
                () -> assertTrue(testObject.isAt(new Vector2D(2, 2))),
                () -> assertFalse(testObject.isAt(new Vector2D(2, 1))),
                () -> assertFalse(testObject.isAt(new Vector2D(1, 2))),
                () -> assertFalse(testObject.isAt(new Vector2D(1, 1)))
        );
    }


    @Test
    public void moveDirectionTest() {
        Animal testObject = new Animal();

        MapDirection[] testArguemnts1 = {MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        for (int i = 0; i < 4; i++) {
            assertEquals(testObject.getDirection(), testArguemnts1[i]);
            testObject.move(MoveDirection.RIGHT);
        }
        assertEquals(testObject.getDirection(), MapDirection.NORTH);

        MapDirection[] testArguemnts2 = {MapDirection.NORTH, MapDirection.WEST, MapDirection.SOUTH, MapDirection.EAST};
        for (int i = 0; i < 4; i++) {
            assertEquals(testObject.getDirection(), testArguemnts2[i]);
            testObject.move(MoveDirection.LEFT);
        }
        assertEquals(testObject.getDirection(), MapDirection.NORTH);
    }

    @Test
    public void movePostionTest() {
        Animal testObject = new Animal();

        assertEquals(testObject.getPosition(), new Vector2D(2, 2)); //direction: north
        testObject.move(MoveDirection.FORWARD);
        assertEquals(testObject.getPosition(), new Vector2D(2, 3));
        testObject.move(MoveDirection.BACKWARD);
        assertEquals(testObject.getPosition(), new Vector2D(2, 2));

        testObject.move(MoveDirection.RIGHT); //direction: east
        testObject.move(MoveDirection.FORWARD);
        assertEquals(testObject.getPosition(), new Vector2D(3, 2));
        testObject.move(MoveDirection.BACKWARD);
        assertEquals(testObject.getPosition(), new Vector2D(2, 2));

        testObject.move(MoveDirection.RIGHT); //direction: south
        testObject.move(MoveDirection.FORWARD);
        assertEquals(testObject.getPosition(), new Vector2D(2, 1));
        testObject.move(MoveDirection.BACKWARD);
        assertEquals(testObject.getPosition(), new Vector2D(2, 2));

        testObject.move(MoveDirection.RIGHT);//direction: west
        testObject.move(MoveDirection.FORWARD);
        assertEquals(testObject.getPosition(), new Vector2D(1, 2));
        testObject.move(MoveDirection.BACKWARD);
        assertEquals(testObject.getPosition(), new Vector2D(2, 2));

        testObject.move(MoveDirection.FORWARD); //left limit, direction: west
        testObject.move(MoveDirection.FORWARD);
        assertEquals(testObject.getPosition(), new Vector2D(0, 2));
        testObject.move(MoveDirection.FORWARD);
        assertEquals(testObject.getPosition(), new Vector2D(0, 2));

        testObject.move(MoveDirection.BACKWARD); //right limit, direction: west
        testObject.move(MoveDirection.BACKWARD);
        testObject.move(MoveDirection.BACKWARD);
        testObject.move(MoveDirection.BACKWARD);
        assertEquals(testObject.getPosition(), new Vector2D(4, 2));
        testObject.move(MoveDirection.BACKWARD);
        assertEquals(testObject.getPosition(), new Vector2D(4, 2));

        testObject.move(MoveDirection.RIGHT); //upper limit, direction: north
        testObject.move(MoveDirection.FORWARD);
        testObject.move(MoveDirection.FORWARD);
        assertEquals(testObject.getPosition(), new Vector2D(4, 4));
        testObject.move(MoveDirection.FORWARD);
        assertEquals(testObject.getPosition(), new Vector2D(4, 4));

        testObject.move(MoveDirection.BACKWARD); //down limit, direction: north
        testObject.move(MoveDirection.BACKWARD);
        testObject.move(MoveDirection.BACKWARD);
        testObject.move(MoveDirection.BACKWARD);
        assertEquals(testObject.getPosition(), new Vector2D(4, 0));
        testObject.move(MoveDirection.BACKWARD);
        assertEquals(testObject.getPosition(), new Vector2D(4, 0));
    }
}
