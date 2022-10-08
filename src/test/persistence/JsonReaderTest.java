package persistence;

import model.ListOfMediaCategory;
import model.Media;
import model.MediaCategory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfMediaCategory loc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            ListOfMediaCategory loc = reader.read();
            assertEquals("MediaBase", loc.getName());
            assertEquals(0, loc.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");

        try {
            ListOfMediaCategory loc = reader.read();
            assertEquals("MediaBase", loc.getName());
            List<MediaCategory> listOfMediaCategory = loc.getMediaCategory();
            assertEquals(2, listOfMediaCategory.size());
            Media media1 = loc.getMediaCategory().get(0).getList().get(0);
            Media media2 = loc.getMediaCategory().get(1).getList().get(0);
            checkMediaCategory("a", 1, media1);
            checkMediaCategory("b", 2, media2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
