package com.challenge.generators.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MaxOperation implements Operation<List<Double>> {
    @Override
    public Double run(List<Double> datasets) {
        return datasets.stream().max(Double::compare).orElse(0.0);
    }
}
