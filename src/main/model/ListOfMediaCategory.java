package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of media categories
// ListOfMediaCategory class is based on WorkRoom class found under model in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class ListOfMediaCategory implements Writable {
    private ArrayList<MediaCategory> listOfMediaCategory;
    private String name;

    //EFFECTS: creates an empty list of media category and assigns it a name.
    public ListOfMediaCategory(String name) {
        listOfMediaCategory = new ArrayList<>();
        this.name = name;
    }

    //EFFECTS: returns media category name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds a media category to a list of media category if the list does not contain the media category.
    public Boolean addMediaCategory(MediaCategory mc) {
        String name = mc.getTitle();
        for (MediaCategory mc2 : listOfMediaCategory) {
            if (name.equals(mc2.getTitle())) {
                return false;
            }
        }
        listOfMediaCategory.add(mc);
        EventLog.getInstance().logEvent(new Event("Created a new Media Category"));
        return true;
    }

    // EFFECTS: returns a list of media category
    public ArrayList<MediaCategory> getMediaCategory() {
        return listOfMediaCategory;
    }

    // EFFECTS: returns number of media category in the list of media category
    public int size() {
        return listOfMediaCategory.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("categories", categoryToJson());
        return json;
    }

    // EFFECTS: returns media categories in the list of media category as a JSON array
    private JSONArray categoryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (MediaCategory c : listOfMediaCategory) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
