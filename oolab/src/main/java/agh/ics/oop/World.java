package agh.ics.oop;

public class World {
    public static void main(String[] args){
        OptionParser parser = new OptionParser();
        MoveDirection[] actions = parser.parse(args);
        Animal animal = new Animal();

        System.out.println("Start");

        run(actions);
        for (MoveDirection action: actions) {
            animal.move(action);
        }

        System.out.println("Stop");
    }

    public static void run(MoveDirection[] actions){
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
