package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OptionParserTest {

    @Test
    public void parseTest() {
        OptionsParser parser = new OptionsParser();

        String[] input1 = {"f", "forward", "b", "backward"};
        MoveDirection[] output1 = {MoveDirection.FORWARD, MoveDirection.FORWARD,
                                        MoveDirection.BACKWARD, MoveDirection.BACKWARD};

        String[] input2 = {"l", "r", "right", "left", "backward"};
        MoveDirection[] output2 = {MoveDirection.LEFT, MoveDirection.RIGHT,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD};

        String[] input3 = {};
        MoveDirection[] output3 = {};

        String[] input4 = {"left", "right", "r", "x"};
        String[] input5 = {"elo"};


        assertAll(
                () -> assertArrayEquals(parser.parse(input1), output1),
                () -> assertArrayEquals(parser.parse(input2), output2),
                () -> assertArrayEquals(parser.parse(input3), output3),
                () -> assertThrows(IllegalArgumentException.class, () -> { parser.parse(input4); }),
                () -> assertThrows(IllegalArgumentException.class, () -> { parser.parse(input5); })
        );

        //message test
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { parser.parse(input4); });
        assert exception.getMessage().contains("x is not legal move specification");
    }
}
