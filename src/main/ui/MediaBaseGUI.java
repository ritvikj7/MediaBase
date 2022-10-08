package ui;


import model.Event;
import model.EventLog;
import model.ListOfMediaCategory;
import model.Media;
import model.MediaCategory;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.PopUpMessage.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// The gui of MediaBase, allows users to add, remove, and view different medias and media categories

// MediaBaseUI is based on the TellerAppUI found in https://github.students.cs.ubc.ca/CPSC210/TellerApp.git and
// WorkRoomApp class found under ui in https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git and
// IntersectionGUI class found under ui in https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git and
// the DrawingEditor class found under ui in https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter.git

public class MediaBaseGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 510;
    public static final int XPOSTION = 6;
    public static final int PANELWIDTH = 362;
    public static final int DIFFERENCE = 6;
    private static final String JSON_STORE = "./data/workroom.json";

    private JPanel mediaCategoryPanel;
    private JPanel loadAndSavePanel;

    private JLabel superPetLogo;
    private JLabel onePieceLogo;

    private TextField categoryField;
    private TextField selectField;
    private TextField categoryFieldAdd;
    private TextField nameFieldAdd;
    private TextField rankingFieldAdd;
    private TextField categoryFieldRemove;
    private TextField nameFieldRemove;

    private ListOfMediaCategory loc; //loc stands for listOfCategories
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    // EFFECTS: initializes the different elements of the Media Base application.
    public MediaBaseGUI() {
        super("MediaBase");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        init();
        initializeGraphics();
        structurePanels();
        initializeMediaCategoryPanels();
        initializeLoadAndSavePanel();
        loggingEvents();
        setVisible(true);
    }

    // EFFECTS: print to the console all the events that have been logged since the application started
    private void loggingEvents() {
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event: EventLog.getInstance()) {
                    System.out.println(event.toString() + "\n");
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes list of category and names it MediaBase
    private void init() {
        loc = new ListOfMediaCategory("MediaBase");
    }

    // MODIFIES: this
    // EFFECTS: Sets-Up the JFrame with the specific specs and characteristics
    private void initializeGraphics() {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: Creates multiple different panels for different purposes and sets two media image to the JFrame.
    private void structurePanels() {

        int imageWidth = 195;
        int imageHeight = 215;
        int imageXPosition = WIDTH - imageWidth - 12;

        //Creates multiple JPanel for the JFrame
        Color colour;
        colour = new Color(47, 12, 12);
        mediaCategoryPanel = new JPanel();
        loadAndSavePanel = new JPanel();

        String sep = System.getProperty("file.separator");

        setImages(imageWidth, imageHeight, imageXPosition, sep);


        mediaCategoryPanel.setBackground(colour);
        mediaCategoryPanel.setBounds(12, 20, WIDTH - 225, 250 + 124);


        loadAndSavePanel.setBackground(colour);
        loadAndSavePanel.setBounds(12, 250 + 155, WIDTH - 225, 50);

        addAllJPanelAndJLabel();
    }

    // MODIFIES: this
    // EFFECTS: creates two images: one piece and super-pets images
    private void setImages(int imageWidth, int imageHeight, int imageXPosition, String sep) {
        // Adding the SuperPets Image to the JFrame
        ImageIcon superPetICon = new ImageIcon(new
                ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "super.png").getImage().getScaledInstance(imageWidth, imageHeight,
                Image.SCALE_SMOOTH));
        superPetLogo = new JLabel(superPetICon);
        superPetLogo.setBounds(imageXPosition, 20, imageWidth, imageHeight);


        // Adding the One Piece Image to the JFrame
        ImageIcon onePieceIcon = new ImageIcon(new
                ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "red.png").getImage().getScaledInstance(imageWidth, imageHeight,
                Image.SCALE_AREA_AVERAGING));
        onePieceLogo = new JLabel(onePieceIcon);
        onePieceLogo.setBounds(imageXPosition, 240, imageWidth, imageHeight);
    }


    // MODIFIES: this
    // EFFECTS: adds multiple different panels and sets two media image to the JFrame.
    private void addAllJPanelAndJLabel() {
        // Creates a border around the JPanels and media Images
        Border br = BorderFactory.createLineBorder(Color.BLACK);
        mediaCategoryPanel.setBorder(br);
        loadAndSavePanel.setBorder(br);
        superPetLogo.setBorder(br);
        onePieceLogo.setBorder(br);

        // Adds the JPanels and media Image to the JFrames
        this.add(mediaCategoryPanel);
        this.add(superPetLogo);
        this.add(onePieceLogo);
        this.add(loadAndSavePanel);
    }

    // MODIFIES: this
    // EFFECTS: Creates and then add the save and load button to a JPanel.
    private void initializeLoadAndSavePanel() {
        // Sets the Layout of loadAndSavePanel
        loadAndSavePanel.setLayout(new GridLayout(0, 2));

        // Creates the save and load button and assigns them commands
        JButton loadButton = new JButton("Load");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);

        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        // Adds the save and load button to loadAndSavePanel
        loadAndSavePanel.add(loadButton);
        loadAndSavePanel.add(saveButton);
    }


    // MODIFIES: this
    // EFFECTS: Splits the media category panel into three other panels for different purposes.
    private void initializeMediaCategoryPanels() {
        // Sets the Layout of mediaCategoryPanel
        mediaCategoryPanel.setLayout(null);

        //Creates multiple JPanel for the mediaCategoryPanel
        JPanel createAndViewPanel = new JPanel();
        JPanel addPanel = new JPanel();
        JPanel removePanel = new JPanel();
//        JPanel loadAndSavePanel = new JPanel();

        // Places the different JPanels on set position on the mediaCategoryPanel
        createAndViewPanel.setBounds(XPOSTION, DIFFERENCE, PANELWIDTH, 92);
        addPanel.setBounds(XPOSTION, DIFFERENCE + 92 + DIFFERENCE, PANELWIDTH, 140);
        removePanel.setBounds(XPOSTION, DIFFERENCE + 92 + DIFFERENCE + 140 + DIFFERENCE, PANELWIDTH, 115);
//        int loadAndSavePanelDifference = DIFFERENCE + 90 + DIFFERENCE + 140 + DIFFERENCE + 115 + DIFFERENCE;
//        loadAndSavePanel.setBounds(XPOSTION, loadAndSavePanelDifference, PANELWIDTH, 50);

        // Creates a border around the three JPanels
        Border br = BorderFactory.createLineBorder(Color.GRAY);
        createAndViewPanel.setBorder(br);
        addPanel.setBorder(br);
        removePanel.setBorder(br);
//        loadAndSavePanel.setBorder(br);

        // Adds the JPanels to the JFrames
        mediaCategoryPanel.add(createAndViewPanel);
        mediaCategoryPanel.add(addPanel);
        mediaCategoryPanel.add(removePanel);
//        mediaCategoryPanel.add(loadAndSavePanel);

        createAndViewPanel(createAndViewPanel);
        addPanel(addPanel);
        removePanel(removePanel);
//        initializeLoadAndSavePanel(loadAndSavePanel);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the remove panel by adding functionality
    private void removePanel(JPanel p) {
        p.setLayout(new FlowLayout());

        Label pickMediaCategoryLabel = new Label("Pick a Media Category: ");
        categoryFieldRemove = new TextField(20);

        Label pickMediaNameLabel = new Label("Media Name:                ");
        nameFieldRemove = new TextField(20);

        JButton removeButton = new JButton("Remove");
        removeButton.setActionCommand("remove");
        removeButton.addActionListener(this);

        p.add(pickMediaCategoryLabel);
        p.add(categoryFieldRemove);
        p.add(pickMediaNameLabel);
        p.add(nameFieldRemove);
        p.add(removeButton);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the add panel by adding functionality
    private void addPanel(JPanel p) {
        p.setLayout(new FlowLayout());

        Label pickMediaCategoryLabel = new Label("Pick a Media Category: ");
        categoryFieldAdd = new TextField(20);

        Label addMediaNameLabel = new Label("Media Name:                ");
        nameFieldAdd = new TextField(20);

        Label addRatingLabel = new Label("Media Rating:               ");
        rankingFieldAdd = new TextField(20);

        JButton createButton = new JButton("Add");
        createButton.setActionCommand("add");
        createButton.addActionListener(this);

        p.add(pickMediaCategoryLabel);
        p.add(categoryFieldAdd);
        p.add(addMediaNameLabel);
        p.add(nameFieldAdd);
        p.add(addRatingLabel);
        p.add(rankingFieldAdd);
        p.add(createButton);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the create and view panel by adding functionality
    private void createAndViewPanel(JPanel p) {
        p.setLayout(new FlowLayout());

        Label pickMediaCategoryNameLabel = new Label("Create Media Category:");
        categoryField = new TextField(12);

        JButton createButton = new JButton("Create");
        createButton.setActionCommand("create");
        createButton.addActionListener(this);

        p.add(pickMediaCategoryNameLabel);
        p.add(categoryField);
        p.add(createButton);


        Label selectMediaCategoryLabel = new Label("Select Media Category:");
        selectField = new TextField(12);

        JButton selectButton = new JButton("View");
        selectButton.setActionCommand("view");
        selectButton.addActionListener(this);

        p.add(selectMediaCategoryLabel);
        p.add(selectField);
        p.add(selectButton);
    }


    // EFFECTS: Listens to an event and based on that event performs an action.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("create")) {
            createMediaCategory();
            categoryField.setText("");
        } else if (e.getActionCommand().equals("view")) {
            selectMediaCategory();
            selectField.setText("");
        } else if (e.getActionCommand().equals("remove")) {
            removeMediaCategory();
            categoryFieldRemove.setText("");
            nameFieldRemove.setText("");
        } else if (e.getActionCommand().equals("add")) {
            addMediaCategory();
            categoryFieldAdd.setText("");
            nameFieldAdd.setText("");
            rankingFieldAdd.setText("");
        } else if (e.getActionCommand().equals("load")) {
            loadWorkRoom();
            EventLog.getInstance().clear();
        } else if (e.getActionCommand().equals("save")) {
            saveWorkRoom();
        }
    }


    // REQUIRES: categoryField.getText to have a non-zero length
    // MODIFIES: this
    // EFFECTS: creates a media category and adds it to a list of media category
    private void createMediaCategory() {
        MediaCategory personalCategory = new MediaCategory(categoryField.getText());
        loc.addMediaCategory(personalCategory);
        new CategoryCreatedPopUp();
    }

    // MODIFIES: Ranking
    // EFFECTS: Finds the ranking of all the media in a category (list of media) and displays it in a different tab
    private void selectMediaCategory() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < loc.size(); i++) {
            if (loc.getMediaCategory().get(i).getTitle().equals(selectField.getText())) {
                MediaCategory mc = loc.getMediaCategory().get(i);
                int j = 1;
                for (Media media : mc.getList()) {
                    names.add("Rank " + j + ": " + media.getName() + " (" + media.getRating() + ")");
                    j++;
                }
                new Ranking(names);
                return;
            }
        }
        new MediaCategoryErrorPopUp();
    }

    // MODIFIES: this
    // EFFECTS: creates a media and adds it to the media category picked or prints the error message in the case of an
    //          error.
    private void addMediaCategory() {
        Media newMedia = new Media(nameFieldAdd.getText(),
                Integer.valueOf(rankingFieldAdd.getText()));

        for (int i = 0; i < loc.size(); i++) {
            if (loc.getMediaCategory().get(i).getTitle().equals(categoryFieldAdd.getText())) {
                loc.getMediaCategory().get(i).addMedia(newMedia);
                new AddMediaPopUpMessage();
                return;
            }
        }
        new MediaCategoryErrorPopUp();
    }

    // MODIFIES: this
    // EFFECTS: remove a media from the media category picked, or prints the error message.
    private void removeMediaCategory() {
        for (int i = 0; i < loc.size(); i++) {
            if (loc.getMediaCategory().get(i).getTitle().equals(categoryFieldRemove.getText())) {
                for (int j = 0; j < loc.getMediaCategory().get(i).length(); j++) {
                    if (loc.getMediaCategory().get(i).getList().get(j).getName().equals(nameFieldRemove.getText())) {
                        loc.getMediaCategory().get(i).removeMedia(loc.getMediaCategory().get(i).getList().get(j));
                        new RemoveMediaPopUpMessage();
                        return;
                    }
                }
                new MediaErrorPopup();
                return;
            }
        }
        new MediaCategoryErrorPopUp();
    }

    // EFFECTS: saves the listOfMediaCategory to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(loc);
            jsonWriter.close();
            String message = " Saved " + loc.getName() + " to " + JSON_STORE;
            new SavePopUPMessage(message);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads listOfMediaCategory from file
    private void loadWorkRoom() {
        try {
            loc = jsonReader.read();
            String message = "Loaded " + loc.getName() + " from " + JSON_STORE;
            new LoadPopUpMessage(message);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}
