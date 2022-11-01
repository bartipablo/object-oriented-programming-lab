package agh.ics.oop;

public class World {
    public static void main(String[] args){
        OptionsParser parser = new OptionsParser();

        MoveDirection[] actions = parser.parse(args);
        Vector2D[] positions = { new Vector2D(2,2), new Vector2D(3,4) };
        IWorldMap map = new RectangularMap(10, 5);

        IEngine engine = new SimulationEngine(actions, map, positions);

        System.out.println("Początkowe położenie: ");
        System.out.println(map.drawMap());
        System.out.println("Lista ruchów do wykonania: ");
        showActions(actions);
        System.out.println("Start");
        engine.run();
        System.out.println("Stop");
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
