package ui.PopUpMessage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// Sets up a pop-up window which indicates to the user that the media base has successfully loaded from file
public class LoadPopUpMessage extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 200;

    private JLabel logo;

    // initializes the different elements of the load pop up message.
    public LoadPopUpMessage(String message) {
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
    private void popUpWindowMessage(String message) {
        String sep = System.getProperty("file.separator");
        ImageIcon icon = new ImageIcon(new
                ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "batman.png").getImage().getScaledInstance(500,
                100,Image.SCALE_AREA_AVERAGING));
        logo = new JLabel(icon);
        logo.setBounds(0, 90, 500, 100);

        JLabel label1 = new JLabel();
        label1.setText(message);
        label1.setBounds(40, 25, 420, 50);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        label1.setForeground(Color.WHITE);
        Border br = BorderFactory.createLineBorder(new Color(49, 10, 10));
        label1.setBorder(br);

        this.add(label1);
        this.add(logo);
    }
}
