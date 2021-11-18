package com.takehome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExpeditionTest {
    @Test
    public void testToString() {
        Expedition expedition = new Expedition();
        Assertions.assertEquals(
                "{\"number\":null,\"type\":null,\"tripNumber\":null,\"quantity\":null,\"mineral\":null,\"price\":null}",
                expedition.toString());
    }

    @Test
    public void mapEmptyList() {
        Assertions.assertTrue(Expedition.MineralHelper.mapMineralToQuantity(List.of()).isEmpty());
    }

    @Test
    public void mapValidList() throws IOException {
        List<Expedition> list = CSVHelper.readFile("src/test/resources/test.csv");
        Map<Expedition.Mineral, Double> map = Expedition.MineralHelper.mapMineralToQuantity(list);
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(12, map.get(Expedition.Mineral.Chromium));
        Assertions.assertEquals(3, map.get(Expedition.Mineral.Gold));
        Assertions.assertEquals(9, map.get(Expedition.Mineral.Titanium));
    }
}
