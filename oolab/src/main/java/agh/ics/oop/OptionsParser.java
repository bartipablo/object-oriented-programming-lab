package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {

    public MoveDirection[] parse(String[] inputArray) {
        MoveDirection[] moveArray = new MoveDirection[inputArray.length];
        int i = 0;
        for (String argument : inputArray) {
            switch (argument) {
                case "forward", "f" -> {
                    moveArray[i] = MoveDirection.FORWARD;
                    i++;
                }
                case "backward", "b" -> {
                    moveArray[i] = MoveDirection.BACKWARD;
                    i++;
                }
                case "right", "r" -> {
                    moveArray[i] = MoveDirection.RIGHT;
                    i++;
                }
                case "left", "l" -> {
                    moveArray[i] = MoveDirection.LEFT;
                    i++;
                }
                default -> {throw new IllegalArgumentException(argument + " is not legal move specification");}
            }
        }
        if (i == 0) {
            return new MoveDirection[0];
        } else {
            return Arrays.copyOfRange(moveArray, 0, i);
        }
    }

}
