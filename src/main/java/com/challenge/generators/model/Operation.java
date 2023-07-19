package com.challenge.generators.model;

import java.util.List;

@FunctionalInterface
public interface Operation<T, G, D> {
    void run(String timestamp, String generationName, List<Double> datasets);
}
