package com.challenge.generators.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Info {
    private List<List<Double>> datasets;
    private List<Generator> generators;
}
