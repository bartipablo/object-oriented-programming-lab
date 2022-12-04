package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.Grass;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private final VBox vBox = new VBox();

    GuiElementBox(IMapElement element) {
        try {
            Image image = new Image(new FileInputStream(element.getImagePath()));
            ImageView imageView = new ImageView(image);
            Label label = new Label();
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);

            if (element instanceof Animal animal) {
                label.setText(animal.getPosition().toString());
            } else if (element instanceof Grass) {
                label.setText("Grass");
            }

            vBox.getChildren().addAll(imageView, label);
            vBox.setAlignment(Pos.BASELINE_CENTER);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public VBox getVBox() {
        return vBox;
    }

}
