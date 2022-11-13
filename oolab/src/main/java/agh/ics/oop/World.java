package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class World {
    public static void main(String[] args) throws InterruptedException {

        OptionsParser parser = new OptionsParser();

        //simulation with input arguments:
        MoveDirection[] actions = parser.parse(args);
        Vector2D[] positions = { new Vector2D(2,2), new Vector2D(3,4) };
        IWorldMap map = new GrassField(10);
        IEngine engine = new SimulationEngine(actions, map, positions);
        engine.run();


        //simulation with random arguments:
        MoveDirection[] randomMoveDirection = MoveDirection.generateRandomMoveDirection(100);
        Vector2D[] animalPosition = {new Vector2D(2,2), new Vector2D(3,4), new Vector2D(0, 0)};
        IWorldMap simulationMap = new GrassField(10);
        IEngine simulationEngine = new SimulationEngine(randomMoveDirection, simulationMap, animalPosition);
        simulationEngine.run();
    }

}
