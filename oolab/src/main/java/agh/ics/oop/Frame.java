package agh.ics.oop;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    JLabel label;
    Frame() {
        this.setTitle("World");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 750);
        this.getContentPane().setBackground(Color.green);
        label = new JLabel();
        this.add(label);
    }

    public void updateFrame(String text) {
        this.remove(label);
        label = new JLabel();
        label.setText(text);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font(Font.SERIF, Font.BOLD,40));
        this.add(label);
    }

}
