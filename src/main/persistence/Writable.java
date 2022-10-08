package persistence;

import org.json.JSONObject;

// Writable interface is based on the Writable interface found under persistence in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
