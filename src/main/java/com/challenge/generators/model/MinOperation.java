package com.challenge.generators.model;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MinOperation implements Operation<List<Double>>{
    @Override
    public Double run(List<Double> datasets) {
        return datasets.stream().min(Double::compare).orElse(0.0);
    }
}
