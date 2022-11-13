package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveDirectionTest {

    @Test
    public void oppositeMoveDirectionTest() {
        MoveDirection moveDirection1 = MoveDirection.FORWARD;
        MoveDirection moveDirection2 = MoveDirection.RIGHT;
        MoveDirection moveDirection3 = MoveDirection.BACKWARD;
        MoveDirection moveDirection4 = MoveDirection.LEFT;
        assertAll(
                () -> assertEquals(moveDirection1.oppositeMoveDirection(), MoveDirection.BACKWARD),
                () -> assertEquals(moveDirection2.oppositeMoveDirection(), MoveDirection.LEFT),
                () -> assertEquals(moveDirection3.oppositeMoveDirection(), MoveDirection.FORWARD),
                () -> assertEquals(moveDirection4.oppositeMoveDirection(), MoveDirection.RIGHT)
        );
    }
}
