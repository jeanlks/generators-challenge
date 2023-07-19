package com.challenge.generators.model;

import java.util.List;

@FunctionalInterface
/**
 * Operation class utilized for the various operations we can have like, sum,average,min, max and others.
 */
public interface Operation<T, G, D> {
    Double run(String timestamp, String generationName, List<Double> datasets);
}
