package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediaTest {
    public Media movie1;
    public Media movie2;

    @BeforeEach
    public void setup() {
        movie1 = new Media("American Psycho", 8);
        movie2 = new Media("Gladiator", 10);
    }

    @Test
    public void testMedia() {
        assertEquals("American Psycho", movie1.getName());
        assertEquals(8, movie1.getRating());
    }

    @Test
    public void testToString() {
        assertEquals("name: American Psycho, rating = 8", movie1.toString());
    }

    @Test
    public void testEquals() {
        assertTrue(movie1.equals(movie1));
        assertFalse(movie1.equals(movie2));
    }
}