package com.codecool.histogram;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {
    Histogram histogram;

    @Test
    public void testGenerateRangesPositiveParameter() {
        histogram = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(-1, -1));
    }

    @Test
    public void testGenerateRangesNegativeAmount() {
        histogram = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(1, -1));
    }

    @Test
    public void testGenerateRangesNegativeStep() {
        histogram = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(-1, 1));
    }

//    @Test
//    public void testAllWordInRange(){
//        histogram = new Histogram();
//        List<Range> s = histogram.generateRanges(3, 3);
//        histogram.generate("Harry Potter and the Sorcerer's Stone", ranges);
//    }

}
