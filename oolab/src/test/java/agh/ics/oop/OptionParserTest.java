package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OptionParserTest {

    @Test
    public void parseTest() {
        OptionsParser parser = new OptionsParser();

        String[] input1 = {"f", "hello", "forward", "b", "x", "backward"};
        MoveDirection[] output1 = {MoveDirection.FORWARD, MoveDirection.FORWARD,
                                        MoveDirection.BACKWARD, MoveDirection.BACKWARD};

        String[] input2 = {"l", "r", "right", "test", "left", "hh"};
        MoveDirection[] output2 = {MoveDirection.LEFT, MoveDirection.RIGHT,
                MoveDirection.RIGHT, MoveDirection.LEFT};

        String[] input3 = {"hello", "java", "world"};
        MoveDirection[] output3 = {};

        String[] input4 = {};
        MoveDirection[] output4 = {};

        assertAll(
                () -> assertArrayEquals(parser.parse(input1), output1),
                () -> assertArrayEquals(parser.parse(input2), output2),
                () -> assertArrayEquals(parser.parse(input3), output3),
                () -> assertArrayEquals(parser.parse(input4), output4)
        );
    }
}
