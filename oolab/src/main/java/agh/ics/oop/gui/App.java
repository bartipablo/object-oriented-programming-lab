package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;


public class App extends Application {

    AbstractWorldMap map;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Label label;
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        Vector2D upperMapLimit = map.getUpperMapLimit();
        Vector2D lowerMapLimit = map.getLowerMapLimit();
        Label yx = new Label("y/x");
        grid.add(yx, 0, 0);
        grid.getColumnConstraints().add(new ColumnConstraints(60));
        grid.getRowConstraints().add(new RowConstraints(60));
        GridPane.setHalignment(yx, HPos.CENTER);

        int index = 1;
        for(int i = lowerMapLimit.x; i <= upperMapLimit.x; i++) {
            label = new Label(String.valueOf(i));
            grid.add(label, index, 0);
            grid.getColumnConstraints().add(new ColumnConstraints(60));
            GridPane.setHalignment(label, HPos.CENTER);
            index += 1;
        }

        index = 1;
        for (int i = lowerMapLimit.y; i <= upperMapLimit.y; i++) {
            label = new Label(String.valueOf(i));
            grid.add(label, 0, index);
            grid.getRowConstraints().add(new RowConstraints(60));
            GridPane.setHalignment(label, HPos.CENTER);
            index += 1;
        }

        for (int i = lowerMapLimit.x; i <= upperMapLimit.x; i++) {
            for (int j = lowerMapLimit.y; j <= upperMapLimit.y; j++) {
                Vector2D position = new Vector2D(i, j);
                if (map.isOccupied(position)) {
                    IMapElement object = (IMapElement) map.objectAt(position);
                    GuiElementBox guiElementBox = new GuiElementBox(object);
                    grid.add(guiElementBox.getVBox(), i - lowerMapLimit.x + 1, j - lowerMapLimit.y + 1);
                }

            }
        }
        Scene scene = new Scene(grid, 60 * (upperMapLimit.x - lowerMapLimit.x + 2) , 60 * (upperMapLimit.y - lowerMapLimit.y + 2) );
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    @Override
    public void init() {
        try {
            List<String> list = getParameters().getRaw();
            String[] array = new String[list.size()];
            for (int i = 0; i < list.size(); i++) array[i] = list.get(i);
            MoveDirection[] directions = new OptionsParser().parse(array);
            map = new GrassField(20);
            Vector2D[] positions = {new Vector2D(2, 2), new Vector2D(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        }
        catch (IllegalArgumentException | InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
