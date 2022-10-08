package model;


import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// Represents a media—so a specific movie, manga, anime or anything thing from another form of media—having a name,
// rating and description
// Media class is based on the Thingy class found under model in
//// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class Media implements Writable {

    private String name;
    private int rating;

    //REQUIRES: 10 >= rating >= 0, as well as name must have a non-zero length
    //EFFECTS: Media is given name, a rating, and a description which may or may not highlight an aspect of the media
    //         consumed.
    public Media(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }


    //EFFECTS: returns media name
    public String getName() {
        return name;
    }

    //EFFECTS: returns media rating
    public int getRating() {
        return rating;
    }


    //EFFECTS: returns a string representation of Media
    public String toString() {
        return "name: " + name + ", rating = " + rating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rating", rating);
        return json;
    }

    //EFFECTS: Changes the way two media objects are compared
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Media media = (Media) o;
        return rating == media.rating && Objects.equals(name, media.name);
    }
}