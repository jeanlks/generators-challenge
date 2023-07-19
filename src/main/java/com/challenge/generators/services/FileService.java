package com.challenge.generators.services;


import com.challenge.generators.model.ReaderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileService implements ReaderStrategy {

    private final ResourceLoader resourceLoader;


    public FileService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @Override
    public String readFile(String filePath) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filePath);
        Path path = resource.getFile().toPath();
        return Files.readString(path, StandardCharsets.UTF_8);
    }
}