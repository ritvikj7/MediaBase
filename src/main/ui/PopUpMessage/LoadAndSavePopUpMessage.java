package ui.PopUpMessage;

import javax.swing.*;
import java.awt.*;

public abstract class LoadAndSavePopUpMessage extends JFrame{

    public static final int WIDTH = 500;
    public static final int HEIGHT = 200;

    // initializes the different elements of the media error popup message.
    public LoadAndSavePopUpMessage(String message) {
        initializeGraphics();
        popUpWindowMessage(message);
        this.getContentPane().setBackground(Color.BLACK);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creates a JFrame with the specific specs
    private void initializeGraphics() {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: Adds Image and label to a JFrame
    public abstract void popUpWindowMessage(String message);
}
