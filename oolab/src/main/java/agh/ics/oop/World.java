package agh.ics.oop;

public class World {
    public static void main(String[] args){
        Direction[] actions = changeStringToEnumArray(args);

        System.out.println("Start");
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
                default       -> System.out.println("Nieznany ruch");
            }
        }
    }

    public static Direction[] changeStringToEnumArray(String[] args){
        Direction[] EnumArray = new Direction[args.length];
        for (int i = 0; i < args.length; i++){
            switch(args[i]){
                case "f" -> EnumArray[i] = Direction.FORWARD;
                case "b" -> EnumArray[i] = Direction.BACKWARD;
                case "l" -> EnumArray[i] = Direction.LEFT;
                case "r" -> EnumArray[i] = Direction.RIGHT;
                default  -> EnumArray[i] = Direction.NONE;
            }
        }
        return EnumArray;
    }



}
