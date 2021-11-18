package com.takehome;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonPropertyOrder({ "number","type","tripNumber","quantity","mineral","price" })
public class Expedition implements Serializable {
    private Long number;
    private String type;
    private Long tripNumber;
    private Double quantity;
    private Mineral mineral;

    public Long getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public Long getTripNumber() {
        return tripNumber;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Mineral getMineral() {
        return mineral;
    }

    public Double getPrice() {
        return price;
    }

    private Double price;

    public enum Mineral {
        Chromium,
        Gold,
        Titanium
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static class MineralHelper {
        public static Map<Mineral, Double> mapMineralToQuantity(List<Expedition> expeditions) {
            return expeditions
                    .stream()
                    .collect(
                            Collectors.groupingByConcurrent(
                                    Expedition::getMineral,
                                    Collectors.summingDouble(Expedition::getQuantity)
                            )
                    );
        }
    }
}