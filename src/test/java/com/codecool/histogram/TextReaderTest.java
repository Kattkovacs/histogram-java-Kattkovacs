package com.codecool.histogram;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TextReaderTest {
    TextReader textReader;

    @Test
    public void testFileNotFoundException() {
        textReader = new TextReader("src/test/resources/not_exist.txt");
        assertThrows(FileNotFoundException.class, ()->textReader.read());
    }

    @Test
    public void testFileIsEmpty() throws IOException{
        textReader = new TextReader("src/test/resources/empty.txt");
        assertEquals(0, textReader.read().length());
    }

    @Test
    public void testFileIsOneLine() throws IOException{
        textReader = new TextReader("src/test/resources/test.txt");
        assertEquals(38, textReader.read().length());
    }

    @Test
    public void testFileIsMultipleLine() throws IOException{
        textReader = new TextReader("src/test/resources/text.txt");
        assertEquals(1342, textReader.read().length());
    }
    
}
