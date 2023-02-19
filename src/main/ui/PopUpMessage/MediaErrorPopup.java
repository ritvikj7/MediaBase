package ui.PopUpMessage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// Sets up a pop-up window which indicates that the media was typed wrong or does not yet exist
public class MediaErrorPopup extends PopUpMessage {

    private JLabel logo;

    // initializes the different elements of the media error popup message.
    public MediaErrorPopup() {
        super();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Adds Image and label to a JFrame
    public void popUpWindowMessage() {
        String sep = System.getProperty("file.separator");
        ImageIcon icon = new ImageIcon(new
                ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "batman.png").getImage().getScaledInstance(500,
                100,Image.SCALE_AREA_AVERAGING));
        logo = new JLabel(icon);
        logo.setBounds(0, 90, 500, 100);

        JLabel label1 = new JLabel();
        label1.setText(" ERROR: Invalid Media Title");
        label1.setBounds(50, 25, 400, 50);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 33));

        label1.setForeground(Color.WHITE);
        Border br = BorderFactory.createLineBorder(new Color(49, 10, 10));
        label1.setBorder(br);

        this.add(label1);
        this.add(logo);
    }
}
