package persistence;

import model.ListOfMediaCategory;
import model.Media;
import model.MediaCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads list of media category from JSON data stored in file
// JsonReader class is based on the JsonReader class found under persistence in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfMediaCategory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfMediaCategory(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list of media category from JSON object and returns it
    private ListOfMediaCategory parseListOfMediaCategory(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ListOfMediaCategory loc = new ListOfMediaCategory(name);
        addThingies(loc, jsonObject);
        return loc;
    }

    // MODIFIES: loc
    // EFFECTS: parses media-categories from JSON object and adds it to ListOfMediaCategory
    private void addThingies(ListOfMediaCategory loc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("categories");
        for (Object json : jsonArray) {
            JSONObject nextCategory = (JSONObject) json;
            addMediaCategory(loc, nextCategory);
        }
    }

    // MODIFIES: loc
    // EFFECTS: parses media-category from JSON object and adds it to ListOfMediaCategory
    private void addMediaCategory(ListOfMediaCategory loc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("category");
        String name = jsonObject.getString("title");
        MediaCategory mediaCategory = new MediaCategory(name);
        loc.addMediaCategory(mediaCategory);
        for (Object json : jsonArray) {
            JSONObject nextMedia = (JSONObject) json;
            addMedia(mediaCategory, nextMedia);
        }
    }

    // MODIFIES: mc
    // EFFECTS: parses media from JSON object and adds it to media-category
    private void addMedia(MediaCategory mc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rating = jsonObject.getInt("rating");
        Media media = new Media(name, rating);
        mc.addMedia(media);
    }
}

