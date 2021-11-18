package com.takehome;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MineralPipeline {
    private final String inputFile;

    public MineralPipeline(String inputFile) {
        this.inputFile = inputFile;
    }

    public void execute() {
        Pipeline<String, Integer> pipeline = new Pipeline<>(new ReadInputStep())
                .pipe(new ProcessInputStep())
                .pipe(new WriteOutputStep());

        pipeline.execute(inputFile);
    }

    public static class ReadInputStep implements Pipeline.Step<String, List<Expedition>>{
        public List<Expedition> process(String inputFile) {
            try {
                return CSVHelper.readFile(inputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class ProcessInputStep implements Pipeline.Step<List<Expedition>, Map<Expedition.Mineral, Double>>{
        public Map<Expedition.Mineral, Double> process(List<Expedition> list) {
            return Expedition.MineralHelper.mapMineralToQuantity(list);
        }
    }

    public static class WriteOutputStep implements Pipeline.Step<Map<Expedition.Mineral, Double>, Integer> {
        public Integer process(Map<Expedition.Mineral, Double> mineralsToQuantity) {
            return process(mineralsToQuantity, null);
        }

        public Integer process(Map<Expedition.Mineral, Double> mineralsToQuantity, String outputFile) {
            try {
                CSVHelper.writeFile(mineralsToQuantity, outputFile);
                return 0;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
