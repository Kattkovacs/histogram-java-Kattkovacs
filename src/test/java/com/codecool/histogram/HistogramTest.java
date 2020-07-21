package com.codecool.histogram;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {
    Histogram histogram;

    @Test
    public void testGenerateRangesPositiveParameter(){
        histogram = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(-1,-1));
    }
    @Test
    public void testGenerateRangesNegativeAmount(){
        histogram = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(1,-1));
    }
    @Test
    public void testGenerateRangesNegativeStep(){
        histogram = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(-1,1));
    }

//    class TestHistogram extends Histogram {
//        @Override
//        public int hashCode() {
//            return super.hashCode();
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) return true;
//            if (obj == null || getClass() != obj.getClass()) return false;
//            Map<Range, Integer> thisMap = this.getHistogram();
//            Map<Range, Integer> equalsMap = ((Histogram) obj).getHistogram();
//            if (thisMap.size() != equalsMap.size()) return false;
//            for (Range range: thisMap.keySet()) {
//                if (!thisMap.get(range).equals(equalsMap.get(range))) return false;
//            }
//            return true;
//        }
//    }

    @Test
    public void testAllWordInRange(){
        histogram = new Histogram();
        List<Range> ranges = histogram.generateRanges(3, 3);
        Map<Range, Integer> testMap = histogram.generate("Harry Potter and the Sorcerer's Stone", ranges);
        Map<Range, Integer> assertMap = new HashMap<>();

        assertMap.put(new Range(1, 3), 2);
        assertMap.put(new Range(4, 6), 3);
        assertMap.put(new Range(7, 9), 1);

        assertEquals(assertMap, testMap);
    }

    @Test
    public void testWordOutOfRange(){
        histogram = new Histogram();
        List<Range> ranges = histogram.generateRanges(3, 2);
        Map<Range, Integer> testMap = histogram.generate("Harry Potter and the Sorcerer's Stone", ranges);
        Map<Range, Integer> assertMap = new HashMap<>();

        assertMap.put(new Range(1, 3), 2);
        assertMap.put(new Range(4, 6), 3);

        assertEquals(assertMap, testMap);
    }

    @Test
    public void testEmptyText(){
        histogram = new Histogram();
        List<Range> ranges = histogram.generateRanges(3, 2);
        Map<Range, Integer> testMap = histogram.generate("", ranges);
        Map<Range, Integer> assertMap = new HashMap<>();

        assertEquals(assertMap, testMap);
    }

    @Test
    public void testNullText(){
        histogram = new Histogram();
        List<Range> ranges = histogram.generateRanges(3, 2);

        assertThrows(IllegalArgumentException.class, () -> histogram.generate(null, ranges));
    }

    @Test
    public void testNullRanges(){
        histogram = new Histogram();
        List<Range> ranges = histogram.generateRanges(3, 2);

        assertThrows(IllegalArgumentException.class, () -> histogram.generate("Harry Potter and the Sorcerer's Stone", null));
    }

    @Test
    public void testGenerateMultipleTimes(){
        histogram = new Histogram();
        List<Range> ranges = histogram.generateRanges(3, 2);
        Map<Range, Integer> testMap = histogram.generate("Harry Potter and the Sorcerer's Stone", ranges);
        Map<Range, Integer> assertMap = new HashMap<>();

        assertMap.put(new Range(1, 3), 2);
        assertMap.put(new Range(4, 6), 3);

        assertEquals(assertMap, testMap);

        testMap = histogram.generate("Harry Potter and the Sorcerer's Stone", ranges);
        assertMap = new HashMap<>();

        assertMap.put(new Range(1, 3), 2);
        assertMap.put(new Range(4, 6), 3);

        assertEquals(assertMap, testMap);
    }

    @Test
    public void testCallingBeforeGenerate() {
        histogram = new Histogram();
        assertEquals(new HashMap<Range, Integer>(), histogram.getHistogram());
    }

    @Test
    public void testCallingAfterGenerate() {
        histogram = new Histogram();
        List<Range> ranges = histogram.generateRanges(3, 3);
        histogram.generate("Harry Potter and the Sorcerer's Stone", ranges);
        Map<Range, Integer> assertMap = new HashMap<>();

        assertMap.put(new Range(1, 3), 2);
        assertMap.put(new Range(4, 6), 3);
        assertMap.put(new Range(7, 9), 1);

        assertEquals(assertMap, histogram.getHistogram());
    }

    @Test
    public void testCallingAfterMultipleGenerate() {
        histogram = new Histogram();
        List<Range> ranges = histogram.generateRanges(3, 3);
        histogram.generate("Harry Potter and the Chambers of Secret", ranges);
        histogram.generate("Harry Potter", ranges);
        histogram.generate("Harry Potter and the Sorcerer's Stone", ranges);
        Map<Range, Integer> assertMap = new HashMap<>();

        assertMap.put(new Range(1, 3), 2);
        assertMap.put(new Range(4, 6), 3);
        assertMap.put(new Range(7, 9), 1);

        assertEquals(assertMap, histogram.getHistogram());
    }

    @Test
    public void testToStringBeforeGenerate() {
        histogram = new Histogram();
        assertEquals("", histogram.toString());
    }

    @Test
    public void testToStringAfterGenerate() {
        histogram = new Histogram();
        List<Range> ranges = histogram.generateRanges(3, 3);
        histogram.generate("Harry Potter and the Sorcerer's Stone", ranges);

        String result = "4  - 6 | ***\n" + "1  - 3 | **\n" + "7  - 9 | *\n";

        assertEquals(result, histogram.toString());
    }

}