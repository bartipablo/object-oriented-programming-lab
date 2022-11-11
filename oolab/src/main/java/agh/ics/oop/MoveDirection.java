package agh.ics.oop;

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
}
