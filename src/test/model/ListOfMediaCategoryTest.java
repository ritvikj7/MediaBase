package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfMediaCategoryTest {

    public ListOfMediaCategory listOfMediaCategory;
    public MediaCategory movies;
    public MediaCategory manhwa;
    public MediaCategory manga;

    @BeforeEach
    public void setup() {
        listOfMediaCategory = new ListOfMediaCategory("MediaBase");
        movies = new MediaCategory("Movies");
        manhwa = new MediaCategory("Manhwa");
        manga = new MediaCategory("Manga");
    }

    @Test
    public void testListOfMediaCategory() {
        assertEquals(0, listOfMediaCategory.size());
        assertEquals("MediaBase", listOfMediaCategory.getName());
    }

    @Test
    public void testAddOneMediaCategory(){
        assertEquals(0, listOfMediaCategory.size());
        assertTrue(listOfMediaCategory.addMediaCategory(movies));
        assertEquals(1, listOfMediaCategory.size());
    }

    @Test
    public void testAddMultipleMediaCategory(){
        assertEquals(0, listOfMediaCategory.size());
        assertTrue(listOfMediaCategory.addMediaCategory(movies));
        assertEquals(1, listOfMediaCategory.size());
        assertTrue(listOfMediaCategory.addMediaCategory(manga));
        assertEquals(2, listOfMediaCategory.size());
        assertTrue(listOfMediaCategory.addMediaCategory(manhwa));
        assertEquals(3, listOfMediaCategory.size());
    }

    @Test
    public void testAddDuplicateMediaCategory(){
        assertEquals(0, listOfMediaCategory.size());
        assertTrue(listOfMediaCategory.addMediaCategory(movies));
        assertEquals(1, listOfMediaCategory.size());

        assertFalse(listOfMediaCategory.addMediaCategory(movies));
        assertEquals(1, listOfMediaCategory.size());
    }
}
