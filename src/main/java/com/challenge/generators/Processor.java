package com.challenge.generators;

import com.challenge.generators.model.Generator;
import com.challenge.generators.model.Info;
import com.challenge.generators.services.FileService;
import com.challenge.generators.services.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class Processor {


    private final JsonService jsonService;
    private final FileService fileService;



    private final OperationRunner operationRunner;

    @Autowired
    public Processor(JsonService jsonService, FileService fileService, OperationRunner operationRunner){
        this.jsonService = jsonService;
        this.fileService = fileService;
        this.operationRunner = operationRunner;
    }


    @Async("threadPoolTaskExecutor")
    public void run() {
        try {
            String jsonString = fileService.readFileFromResources("data.json");
            Info info = jsonService.parseJson(jsonString);
            log.info(info.toString());
            for (Generator generator : info.getGenerators()) {
                this.operationRunner.process(generator, info);
            }
        } catch (IOException e) {
            log.error("Error while trying to read file: "+e);
            throw new RuntimeException(e);
        } catch (Exception e){
            log.error("Error while trying to process: "+e);
        }
    }



}
