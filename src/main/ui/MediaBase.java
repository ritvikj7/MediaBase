package ui;

import model.ListOfMediaCategory;
import model.Media;
import model.MediaCategory;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static javax.swing.UIManager.get;

// Media Base application to keep track of your own personalized watched list... not a GUI, for console only.

// MediaBaseUI is based on the TellerAppUI found in https://github.students.cs.ubc.ca/CPSC210/TellerApp.git and
// WorkRoomApp class found under ui in https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class MediaBase {

    //
    private static final String JSON_STORE = "./data/workroom.json";

    private ListOfMediaCategory loc; //loc stands for listOfCategories
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private int integer; // can ultimately delete this
    private int rating;
    private int rank = 1;
    private List<String> names = new ArrayList<>();


    // runs the media base application
    public MediaBase() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMediaBase();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMediaBase() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes category
    private void init() {
        loc = new ListOfMediaCategory("MediaBase");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create media category");
        System.out.println("\tp -> pick media category");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            createMediaCategory();
        } else if (command.equals("p")) {
            selectMediaCategory();
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: creates a media category and adds it to a list of media category
    private void createMediaCategory() {
        System.out.print("Enter category name: ");
        String str = input.next();
        MediaCategory personalCategory = new MediaCategory(str);
        loc.addMediaCategory(personalCategory);
        System.out.print("Category Created Successfully!");
    }


    // EFFECTS: Picks a media category to do an operation on
    private void selectMediaCategory() {
        System.out.print("From the listed categories, ");
        // Display the categories
        for (MediaCategory mediaCategory : loc.getMediaCategory()) {
            System.out.print("[" + mediaCategory.getTitle() + "]");
        }
        System.out.print(", what category do you want to choose: ");
        String mediaCategory = input.next();
        selectOptions(mediaCategory);
    }


    // EFFECTS: prompts user to pick one of three options: Add Media, Remove Media, Get Media.
    private void selectOptions(String mediaCategory) {
        String selection = "";  // force entry into loop

        while (!(selection.equals("a") || selection.equals("r") || selection.equals("g"))) {
            System.out.println("a for Add Media");
            System.out.println("r for Remove Media");
            System.out.println("g for Get Media");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("a")) {
            addMedia(mediaCategory);
            System.out.print(loc.getMediaCategory().get(integer).getList());
        } else if (selection.equals("r")) {
            removeMedia(mediaCategory);
            System.out.print(loc.getMediaCategory().get(integer).getList());
        } else if (selection.equals("g")) {
            selectMedia(mediaCategory);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a media and adds it to a media category selected in selectMediaCategory or prints the error
    //          message.
    private void addMedia(String mediaCategory) {
        System.out.print("What is the media name: ");
        String str1 = input.next();
        System.out.print("What is the media rating: ");
        String int2 = input.next();

        Media newMedia = new Media(str1, Integer.valueOf(int2));

        for (int i = 0; i < loc.size(); i++) {
            if (loc.getMediaCategory().get(i).getTitle().equals(mediaCategory)) {
                loc.getMediaCategory().get(i).addMedia(newMedia);
                System.out.println("Media Created Successfully");
                this.integer = i;
                return;
            }
        }
        System.out.println("ERROR: Media not created");

    }

    // MODIFIES: this
    // EFFECTS: remove a media from a media category selected in selectMediaCategory, or prints the error message.
    private void removeMedia(String mediaCategory) {
        System.out.print("What was the media name: ");
        String str1 = input.next();

        for (int i = 0; i < loc.size(); i++) {
            if (loc.getMediaCategory().get(i).getTitle().equals(mediaCategory)) {
                for (int j = 0; j < loc.getMediaCategory().get(i).length(); j++) {
                    if (loc.getMediaCategory().get(i).getList().get(j).getName().equals(str1)) {
                        loc.getMediaCategory().get(i).removeMedia(loc.getMediaCategory().get(i).getList().get(j));
                        System.out.println("Media removed Successfully");
                        return;
                    }
                }
                System.out.println("ERROR: Media not removed or media did not exist");
            }
        }
    }


    // EFFECTS: prompts user to pick one of three options to do on media: view rank, view rating, view description.
    private void selectMedia(String mediaCategory) {
        String selection = "";  // force entry into loop

        while (!(selection.equals("rank") || selection.equals("rate") || selection.equals("d"))) {
            System.out.println("type rank to View Ranking");
            System.out.println("type rate to View Rating");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("rate")) {
            retrieveRating(mediaCategory);
//            System.out.print(rating);
        } else if (selection.equals("rank")) {
            retrieveRanking(mediaCategory);
        }
    }

    // EFFECTS: Finds and prints the rating of the media that users indicated.
    private void retrieveRating(String mediaCategory) {
        System.out.print("What is the media name: ");
        String str1 = input.next();

        for (int i = 0; i < loc.size(); i++) {
            if (loc.getMediaCategory().get(i).getTitle().equals(mediaCategory)) {
                for (int j = 0; j < loc.getMediaCategory().get(i).length(); j++) {
                    if (loc.getMediaCategory().get(i).getList().get(j).getName().equals(str1)) {
                        System.out.println("You rating for " + str1 + " is:");
                        rating = loc.getMediaCategory().get(i).getList().get(j).getRating();
                        System.out.print(rating);
                        return;
                    }
                }
            }
        }
        System.out.println("Unable to retrieve rating");
    }


    // EFFECTS: Finds the ranking of all the media in a category (list of media).
    private void retrieveRanking(String mediaCategory) {
        for (int i = 0; i < loc.size(); i++) {
            if (loc.getMediaCategory().get(i).getTitle().equals(mediaCategory)) {
                MediaCategory mc = loc.getMediaCategory().get(i);
                int j = 1;
                for (Media media : mc.getList()) {
                    names.add("Rank " + j + ": " + media.getName());
                    j++;
                }
            }
        }
        System.out.println(names);
        return;
    }


    // EFFECTS: saves the listOfMediaCategory to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(loc);
            jsonWriter.close();
            System.out.println("Saved " + loc.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads listOfMediaCategory from file
    private void loadWorkRoom() {
        try {
            loc = jsonReader.read();
            System.out.println("Loaded " + loc.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}