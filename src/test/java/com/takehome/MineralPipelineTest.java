package com.takehome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class MineralPipelineTest {

    @Test
    public void testSuccessPipeline() {
        MineralPipeline pipeline = new MineralPipeline("src/test/resources/test.csv");
        pipeline.execute();
    }

    @Test
    public void testFailPipeline() {
        MineralPipeline pipeline = new MineralPipeline("doesNotExist");
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> pipeline.execute());
        Assertions.assertTrue(exception.getCause() instanceof FileNotFoundException);
    }
}
