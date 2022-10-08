package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class MediaCategoryTest {

    public MediaCategory movies;
    public Media movie1;
    public Media movie2;
    public Media movie3;

    @BeforeEach
    public void setup() {
        movies = new MediaCategory("Movies");

        movie1 = new Media("Black Panther", 10);
        movie2 = new Media("American Psycho", 7);
        movie3 = new Media("Gladiator", 9);
    }

    @Test
    public void testMediaCategory() {
        assertEquals(0, movies.length());
        assertTrue(movies.isEmpty());
        assertEquals("Movies", movies.getTitle());
    }

    @Test
    public void testAddMedia() {
        // add movie1
        assertEquals(0, movies.length());
        assertTrue(movies.isEmpty());
        assertTrue(movies.addMedia(movie1));
        assertTrue(movies.contains(movie1));
        assertEquals(1, movies.length());

        // add movie2
        assertTrue(movies.addMedia(movie2));
        assertTrue(movies.contains(movie2));
        assertEquals(2, movies.length());

        // add movie3
        assertTrue(movies.addMedia(movie3));
        assertTrue(movies.contains(movie3));
        assertEquals(3, movies.length());


        //check whether they are placed according to their ranking
        assertEquals(0, movies.getList().indexOf(movie1));
        assertEquals(2, movies.getList().indexOf(movie2));
        assertEquals(1, movies.getList().indexOf(movie3));
    }

    @Test
    public void testAddAlreadyAddedMedia() {
        // add movie1
        assertEquals(0, movies.length());
        assertTrue(movies.isEmpty());
        assertTrue(movies.addMedia(movie1));
        assertTrue(movies.contains(movie1));
        assertEquals(1, movies.length());

        // add movie2
        assertTrue(movies.addMedia(movie2));
        assertTrue(movies.contains(movie2));
        assertEquals(2, movies.length());

        // add movie1 again
        assertFalse(movies.addMedia(movie1));
        assertTrue(movies.contains(movie1));
        assertEquals(2, movies.length());

        //check whether they are placed according to their ranking
        assertEquals(0, movies.getList().indexOf(movie1));
        assertEquals(1, movies.getList().indexOf(movie2));
    }

    @Test
    public void testSortCategoryAlphabetically(){
        //Sort is a helper functions, so it is hard to test it:
        Media movieA = new Media("Black Panther", 9);
        Media movieB = new Media("American Psycho", 9);
        Media movieC = new Media("Gladiator", 9);

        // add the three movies
        assertEquals(0, movies.length());
        assertTrue(movies.isEmpty());
        assertTrue(movies.addMedia(movieA));
        assertTrue(movies.addMedia(movieB));
        assertTrue(movies.addMedia(movieC));
        assertEquals(3, movies.length());

        //check whether they are sorted according to their rating, which in this case is the same, so in alphabetical
        // order.
        assertEquals(1, movies.getList().indexOf(movieA));
        assertEquals(0, movies.getList().indexOf(movieB));
        assertEquals(2, movies.getList().indexOf(movieC));
    }


    @Test
    public void testRemoveMedia() {
        //add three different movie in movies.
        assertEquals(0, movies.length());
        assertTrue(movies.isEmpty());
        movies.addMedia(movie1);
        movies.addMedia(movie2);
        movies.addMedia(movie3);
        assertEquals(3, movies.length());

        //removes movie1
        assertTrue(movies.removeMedia(movie1));
        assertEquals(2, movies.length());
        assertFalse(movies.contains(movie1));

        //checks that movie 2 and 3 are still contained in movies
        assertTrue(movies.contains(movie2));
        assertTrue(movies.contains(movie3));

        //removes movie2
        assertTrue(movies.removeMedia(movie2));
        assertEquals(1, movies.length());
        assertFalse(movies.contains(movie2));

        //checks that movie 3 is still contained in movies
        assertTrue(movies.contains(movie3));

        //removes movie3
        assertTrue(movies.removeMedia(movie3));
        assertEquals(0, movies.length());
        assertFalse(movies.contains(movie3));
    }

    @Test
    public void testRemoveEmptyMedia() {
        //add a movie in movies.
        assertEquals(0, movies.length());
        assertTrue(movies.isEmpty());
        movies.addMedia(movie1);
        assertEquals(1, movies.length());
        assertTrue(movies.contains(movie1));

        //remove movie1
        assertTrue(movies.removeMedia(movie1));
        assertEquals(0, movies.length());
        assertFalse(movies.contains(movie1));
        assertTrue(movies.isEmpty());

        //remove movie1 again
        assertFalse(movies.removeMedia(movie1));
        assertEquals(0, movies.length());
        assertFalse(movies.contains(movie1));
        assertTrue(movies.isEmpty());
    }


    @Test
    public void testGetRankOfMedia() {
        // add three movie in the Movies category
        assertEquals(0, movies.length());
        assertTrue(movies.isEmpty());
        movies.addMedia(movie1);
        movies.addMedia(movie2);
        movies.addMedia(movie3);
        assertEquals(3, movies.length());

        assertEquals(1, movies.getRankOfMedia("Black Panther"));
        assertEquals(3, movies.getRankOfMedia("American Psycho"));
        assertEquals(2, movies.getRankOfMedia("Gladiator"));
        assertEquals(-1, movies.getRankOfMedia("The Dark Knight"));
    }

}