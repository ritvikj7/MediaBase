package persistence;

import model.Media;
import model.MediaCategory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkMediaCategory(String title, int rating, Media media) {
        assertEquals(title, media.getName());
        assertEquals(rating, media.getRating());
    }
}

