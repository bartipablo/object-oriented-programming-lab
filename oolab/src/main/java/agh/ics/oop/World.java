package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class World {
    public static void main(String[] args) throws InterruptedException {

        OptionsParser parser = new OptionsParser();

        MoveDirection[] actions = parser.parse(args);
        Vector2D[] positions = { new Vector2D(2,2), new Vector2D(3,4) };
        IWorldMap map = new RectangularMap(10, 5);


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
