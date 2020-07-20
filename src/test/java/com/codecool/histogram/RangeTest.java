package com.codecool.histogram;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {

    Range range;

    @Test
    public void testConstructorParameters() {
        assertThrows(IllegalArgumentException.class, ()->range = new Range(-1, 5));
        assertThrows(IllegalArgumentException.class, ()->range = new Range(3, 2));
    }

    @Test
    public void testIsInRange() {
        range = new Range(3, 5);
        assertTrue(range.isInRange("Word"));
        assertTrue(range.isInRange("Wor"));
        assertTrue(range.isInRange("Wordd"));
        assertFalse(range.isInRange("WordIsNotInRange"));
    }

    @Test
    public void testToString() {
        range = new Range(3, 5);
        assertEquals("3  - 5 ", range.toString());
    }
}
