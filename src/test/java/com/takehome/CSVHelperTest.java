package com.takehome;

import com.fasterxml.jackson.dataformat.csv.CsvReadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class CSVHelperTest {
    private final String BASE_PATH = "src/test/resources";
    @Test
    public void testNonExistCSV(){
        Assertions.assertThrows(IOException.class, () -> CSVHelper.readFile("doesNotExist"));
    }

    @Test
    public void testValidCSV() throws IOException {
        List<Expedition> list = CSVHelper.readFile(BASE_PATH+"/test.csv");
        Assertions.assertEquals(7, list.size());
        Assertions.assertEquals(Expedition.Mineral.Chromium, list.get(0).getMineral());
        Assertions.assertEquals(7.0, list.get(6).getQuantity());
    }

    @Test
    public void testEmptyCSV() throws IOException {
        List<Expedition> list = CSVHelper.readFile(BASE_PATH+"/empty.csv");
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testInvalidCSV() throws IOException {
        Assertions.assertThrows(CsvReadException.class, () -> CSVHelper.readFile(BASE_PATH+"/invalid.csv"));
    }

    @Test
    public void testIncompleteCSV() throws IOException {
        List<Expedition> list =  CSVHelper.readFile(BASE_PATH+"/incomplete.csv");
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(1L, list.get(0).getNumber());
        Assertions.assertNull(list.get(0).getMineral());
    }
}
