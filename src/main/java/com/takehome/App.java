package com.takehome;

import java.io.IOException;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            printUsage();
        } else {
            String inputFilePath = args[0];

            MineralPipeline pipeline = new MineralPipeline(inputFilePath);
            pipeline.execute();
        }
    }

    private static void printUsage() {
        System.out.println("Uage: java -jar <path_to_jar> <path_to_input_file>");
    }
}
