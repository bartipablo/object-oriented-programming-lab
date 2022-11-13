package agh.ics.oop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Vector2DTest {

    @Test
    public void equalsTest() {
        Vector2D testVector = new Vector2D(1, 2);
        assertAll(
                () -> assertTrue(testVector.equals(new Vector2D(1, 2))),
                () -> assertFalse(testVector.equals(new Vector2D(2, 1))),
                () -> assertFalse(testVector.equals(null)),
                () -> assertFalse(testVector.equals(new Object()))
        );
    }

    @Test
    public void toStringTest() {
        Vector2D testVector = new Vector2D(1, 2);
        assertEquals(testVector.toString(), "(1, 2)");
    }

    @Test
    public void precedesTest() {
        Vector2D testVector = new Vector2D(5, 10);
        assertAll(
                () -> assertTrue(testVector.precedes(new Vector2D(10, 20))),
                () -> assertTrue(testVector.precedes(new Vector2D(5, 10))),
                () -> assertFalse(testVector.precedes(new Vector2D(1, 20))),
                () -> assertFalse(testVector.precedes(new Vector2D(10, 1))),
                () -> assertFalse(testVector.precedes(new Vector2D(1, 2)))
        );
    }

    @Test
    public void followsTest() {
        Vector2D testVector = new Vector2D(5, 10);
        assertAll(
                () -> assertTrue(testVector.follows(new Vector2D(1, 2))),
                () -> assertTrue(testVector.follows(new Vector2D(5, 10))),
                () -> assertFalse(testVector.follows(new Vector2D(10, 1))),
                () -> assertFalse(testVector.follows(new Vector2D(1, 20))),
                () -> assertFalse(testVector.follows(new Vector2D(10, 20)))
        );
    }

    @Test
    public void upperRightTest() {
        Vector2D testVector = new Vector2D(5, 10);
        assertAll(
                () -> assertEquals(testVector.upperRight(new Vector2D(1, 2)), new Vector2D(5, 10)),
                () -> assertEquals(testVector.upperRight(new Vector2D(1, 20)), new Vector2D(5, 20)),
                () -> assertEquals(testVector.upperRight(new Vector2D(10, 2)), new Vector2D(10, 10)),
                () -> assertEquals(testVector.upperRight(new Vector2D(10, 20)), new Vector2D(10, 20))
        );
    }

    @Test
    public void lowerLeftTest() {
        Vector2D testVector = new Vector2D(5, 10);
        assertAll(
                () -> assertEquals(testVector.lowerLeft(new Vector2D(1, 2)), new Vector2D(1, 2)),
                () -> assertEquals(testVector.lowerLeft(new Vector2D(1, 20)), new Vector2D(1, 10)),
                () -> assertEquals(testVector.lowerLeft(new Vector2D(10, 2)), new Vector2D(5, 2)),
                () -> assertEquals(testVector.lowerLeft(new Vector2D(10, 20)), new Vector2D(5, 10))

        );
    }

    @Test
    public void addTest() {
        Vector2D testVector1 = new Vector2D(1, 2);
        Vector2D testVector2 = new Vector2D(3, 5);
        assertEquals(testVector1.add(testVector2), new Vector2D(4, 7));

    }

    @Test
    public void subtractTest() {
        Vector2D testVector1 = new Vector2D(5, 10);
        Vector2D testVector2 = new Vector2D(7, 3);
        assertEquals(testVector1.subtract(testVector2), new Vector2D(-2, 7));
    }

    @Test
    public void oppositeTest() {
        Vector2D testVector = new Vector2D(-3, 5);
        assertEquals(testVector.opposite(), new Vector2D(3, -5));
    }

    @Test
    public void hashCodeTest() {
        Vector2D testVector1 = new Vector2D(11, 24);
        Vector2D testVector2 = new Vector2D(11, 24);
        Vector2D testVector3 = new Vector2D(13, 7);
        assertEquals(testVector1.hashCode(), testVector2.hashCode());
        assertNotEquals(testVector1.hashCode(), testVector3.hashCode());
    }

}
