package agh.ics.oop;

import java.util.Scanner;

public class World {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start");

        String userArguments = scanner.nextLine();
        Direction[] actions = changeStringToEnumArray(userArguments);
        run(actions);

        System.out.println("Stop");
    }

    public static void run(Direction[] actions){
        for (Direction action : actions){
            switch (action) {
                case FORWARD  -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT    -> System.out.println("Zwierzak skręca w prawo");
                case LEFT     -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }

    public static Direction[] changeStringToEnumArray(String stringArgument){
        Direction[] enumArguments = new Direction[stringArgument.length()];

        for (int i = 0; i < stringArgument.length(); i++){
            switch(stringArgument.charAt(i)){
                case 'f' -> enumArguments[i] = Direction.FORWARD;
                case 'b' -> enumArguments[i] = Direction.BACKWARD;
                case 'r' -> enumArguments[i] = Direction.RIGHT;
                case 'l' -> enumArguments[i] = Direction.LEFT;
                default  -> enumArguments[i] = Direction.NONE;
            }
        }
        return enumArguments;
    }
}
