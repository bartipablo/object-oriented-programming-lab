package agh.ics.oop;
import java.util.Random;


public enum MoveDirection {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT;

    public MoveDirection oppositeMoveDirection() {
        return switch (this) {
            case FORWARD  -> BACKWARD;
            case BACKWARD -> FORWARD;
            case RIGHT    -> LEFT;
            case LEFT     -> RIGHT;
        };
    }

    public static MoveDirection[] generateRandomMoveDirection(int amount) {
        MoveDirection[] randomMoveDirection = new MoveDirection[amount];
        Random random = new Random();
        for(int i = 0; i < amount; i++) {
            switch (random.nextInt(0,6)) {
                case 0, 1, 2 -> randomMoveDirection[i] = MoveDirection.FORWARD;
                case 3       -> randomMoveDirection[i] = MoveDirection.BACKWARD;
                case 4       -> randomMoveDirection[i] = MoveDirection.LEFT;
                case 5       -> randomMoveDirection[i] = MoveDirection.RIGHT;
            }
        }
        return randomMoveDirection;
    }
}
