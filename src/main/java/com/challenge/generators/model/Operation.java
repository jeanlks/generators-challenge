package com.challenge.generators.model;

import java.util.List;

@FunctionalInterface
/**
 * Operation class utilized for the various operations we can have like, sum,average,min, max and others.
 */
public interface Operation<D> {
    Double run(D datasets);
}
