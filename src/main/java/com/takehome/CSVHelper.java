package com.takehome;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVHelper {
    public static List<Expedition> readFile(String path) throws IOException {
        Reader reader = new FileReader(path);
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Expedition.class).withHeader();
        MappingIterator<Expedition> iterator = mapper.readerFor(Expedition.class).with(schema).readValues(reader);
        return iterator.readAll();
    }


    private static String generateFileName(){
        String name = "minerals";
        String extension = ".csv";
        return name + "-" + new Date().getTime() + extension;
    }

    public static void writeFile(Map<Expedition.Mineral, Double> map, String path) throws IOException {
        if (path == null || path.isBlank())
            path = generateFileName();

        Writer writer = new FileWriter(path);
        writer.append("Mineral")
                .append(',')
                .append("Quantity")
                .append("\n");

        if (map != null)
            for (Map.Entry<Expedition.Mineral, Double> entry : map.entrySet()) {
                writer.append(entry.getKey().toString())
                        .append(',')
                        .append(entry.getValue().toString())
                        .append("\n");
            }
        writer.flush();
        System.out.println("Wrote to file: " + path);
    }
}
