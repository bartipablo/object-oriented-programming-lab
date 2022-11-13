package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class World {
    public static void main(String[] args) throws InterruptedException {

        OptionsParser parser = new OptionsParser();

        MoveDirection[] actions = parser.parse(args);
        Vector2D[] positions = { new Vector2D(2,2), new Vector2D(3,4) };
        IWorldMap map = new GrassField(10);

        IEngine engine = new SimulationEngine(actions, map, positions);

        //input arguments:
        //engine.run();

        //simulation:
        Random rand = new Random();
        MoveDirection[] randArguments = new MoveDirection[1000];
        for (int i = 0; i < 1000; i++) {
            int randNumber = rand.nextInt(6);
            switch (randNumber) {
                case 0, 1, 4 -> randArguments[i] = MoveDirection.FORWARD;
                case 2 -> randArguments[i] = MoveDirection.RIGHT;
                case 3 -> randArguments[i] = MoveDirection.BACKWARD;
                case 5 -> randArguments[i] = MoveDirection.LEFT;
            }
        }
        map = new GrassField(20);
        engine = new SimulationEngine(randArguments, map, positions);
        engine.run();

    }

}
