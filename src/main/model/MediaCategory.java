package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of media, or in other words a media category(Movies, Anime, Manga ...).
// MediaCategory is based on the IncidentQueue model found in
// https://github.students.cs.ubc.ca/CPSC210-2022S-T2/lab5_g4c7l.git and WorkRoom class found under model in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class MediaCategory implements Writable {

    private ArrayList<Media> category;
    private String title;

    //EFFECTS: creates an empty list of media category and assigned it a name to classify the genre of entertainment
    //         (movies, novels, Anime) it will store.
    public MediaCategory(String title) {
        category = new ArrayList<Media>();
        this.title = title;
    }

    //MODIFY: this
    //EFFECT: places a media in a media category if the media category already does not contain that specific media
    //        and returns true, otherwise returns false.
    public Boolean addMedia(Media media) {
        if (!(category.contains(media))) {
            category.add(media);
            sortCategory();
            EventLog.getInstance().logEvent(new Event("Added Media to a Media Category"));
            return true;
        }
        return false;
    }

    //MODIFY: this
    //EFFECT: Goes through the media category, sorting the media category based on rating from Highest to Lowest, and in
    //        alphabetical order (selection sort).
    private void sortCategory() {
        for (int i = 0; i < length() - 1; i++) {
            int maxElemIndex = i;

            for (int j = i + 1; j < length(); j++) {
                if (category.get(j).getRating() > category.get(maxElemIndex).getRating()) {
                    maxElemIndex = j;
                } else if (category.get(j).getRating() == category.get(maxElemIndex).getRating()) {
                    if (category.get(j).getName().compareTo(category.get(maxElemIndex).getName()) < 0) {
                        maxElemIndex = j;
                    }
                }
            }
            Collections.swap(category, i, maxElemIndex);
        }
    }

    //MODIFY: this
    //EFFECTS: removes a media from the media category if it stores it and returns true, otherwise returns false
    public Boolean removeMedia(Media m) {
        if (category.contains(m)) {
            category.remove(m);
            EventLog.getInstance().logEvent(new Event("Removed Media from a Media Category"));
            return true;
        }
        return false;
    }

    //EFFECTS: find and returns the rank of a media based on its name within a category. And If there is no media
    //         in the category with the given name, the method return the value -1.
    public int getRankOfMedia(String title) {
        int rank = 1;
        for (Media media : category) {
            if (title.equals(media.getName())) {
                return rank;
            } else {
                rank++;
            }
        }
        return -1;
    }

    //EFFECT: returns the number of media pieces in a category
    public int length() {
        return category.size();
    }

    //EFFECT: checks if the category is empty; if empty return true, otherwise false
    public boolean isEmpty() {
        return category.isEmpty();
    }

    //EFFECT: checks if the category contains the passed media; if it does return true, otherwise false
    public boolean contains(Media m) {
        return category.contains(m);
    }

    //EFFECTS: returns media name
    public String getTitle() {
        return title;
    }

    //EFFECTS: returns list of media
    public List<Media> getList() {
        return category;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("category", categoryToJson());
        return json;
    }

    // EFFECTS: returns media in MediaCategory as a JSON array
    private JSONArray categoryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Media c : category) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}