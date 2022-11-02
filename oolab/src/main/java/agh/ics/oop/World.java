package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class World {
    public static void main(String[] args) throws InterruptedException {

        OptionsParser parser = new OptionsParser();

        MoveDirection[] actions = parser.parse(args);
        Vector2D[] positions = { new Vector2D(2,2), new Vector2D(3,4) };
        IWorldMap map = new RectangularMap(10, 9);

        IEngine engine = new SimulationEngine(actions, map, positions);

        //input arguments:
        engine.run();

        //simulation:

        Random rand = new Random();
        MoveDirection[] randArguments = new MoveDirection[1000];
        for (int i = 0; i < 1000; i++) {
            int randNumber = rand.nextInt(4);
            switch (randNumber) {
                case 0 -> randArguments[i] = MoveDirection.FORWARD;
                case 1 -> randArguments[i] = MoveDirection.RIGHT;
                case 2 -> randArguments[i] = MoveDirection.BACKWARD;
                case 3 -> randArguments[i] = MoveDirection.LEFT;
            }
        }
        map = new RectangularMap(10, 9);
        engine = new SimulationEngine(randArguments, map, positions);
        engine.run();
        }

    public static void showActions(MoveDirection[] actions){
        for (MoveDirection action : actions){
            switch (action) {
                case FORWARD  -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT    -> System.out.println("Zwierzak skręca w prawo");
                case LEFT     -> System.out.println("Zwierzak skręca w lewo");
                default       -> System.out.println("Nieznany ruch");
            }
        }
    }
}
