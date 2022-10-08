package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.sun.glass.ui.Cursor.setVisible;

// Lists the ranking of a media in a JFrame.
// Ranking is based on the ListDemo project found in
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class Ranking extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private JList list;
    private DefaultListModel listModel;

    // MODIFIES: this
    // EFFECTS: sets up the JFrame title and runs the initializeGraphics method.
    public Ranking(List<String> names) {
        super("Ranking");
        initializeGraphics(names);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creates a JFrame with the specific specs and lists the media in a media category based on ranking.
    private void initializeGraphics(List<String> names) {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listModel = new DefaultListModel();

        for (String n: names) {
            listModel.addElement(n);
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }
}
