package persistence;

import model.ListOfMediaCategory;
import model.Media;
import model.MediaCategory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfMediaCategory loc = new ListOfMediaCategory("MediaBase");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfMediaCategory loc = new ListOfMediaCategory("MediaBase");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(loc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            loc = reader.read();
            assertEquals("MediaBase", loc.getName());
            assertEquals(0, loc.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ListOfMediaCategory loc = new ListOfMediaCategory("MediaBase");

            MediaCategory mc1 = new MediaCategory("dog");
            MediaCategory mc2 = new MediaCategory("cat");
            loc.addMediaCategory(mc1);
            loc.addMediaCategory(mc2);
            mc1.addMedia(new Media("a", 1));
            mc2.addMedia(new Media("b", 2));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(loc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            assertEquals("MediaBase", loc.getName());
            List<MediaCategory> listOfMediaCategory = loc.getMediaCategory();
            assertEquals(2, listOfMediaCategory.size());
            Media media1 = loc.getMediaCategory().get(0).getList().get(0);
            Media media2 = loc.getMediaCategory().get(1).getList().get(0);
            checkMediaCategory("a", 1, media1);
            checkMediaCategory("b", 2, media2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
