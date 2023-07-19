package com.challenge.generators.model;

import java.io.IOException;

public interface ReaderStrategy {
    String readFile(String path) throws IOException;
}
