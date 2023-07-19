package com.challenge.generators.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MaxOperation implements Operation<String, String, List<Double>> {
    @Override
    public void run(String timestamp, String generationName, List<Double> datasets) {
        Double maxValue = datasets.stream().max(Double::compare).orElse(0.0);
        log.info(timestamp+" - " +generationName+" - "+maxValue);
    }
}
