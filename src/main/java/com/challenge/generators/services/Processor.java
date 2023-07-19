package com.challenge.generators.services;

import com.challenge.generators.model.Generator;
import com.challenge.generators.model.Info;
import com.challenge.generators.model.ReaderStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
/**
 * Processor holds the logic to read the file, process the json and them call the threads.
 */
public class Processor {


    private JsonService jsonService;
    private OperationRunner operationRunner;

    private ReaderStrategy fileReader;


    public void setFileReader(ReaderStrategy readerStrategy){
        this.fileReader = readerStrategy;
    }

    public void setOperationRunner(OperationRunner operationRunner){
        this.operationRunner = operationRunner;
    }

    public void setJsonService(JsonService jsonService){
        this.jsonService = jsonService;
    }

    @Async("threadPoolTaskExecutor")
    public void run() {
        try {
            log.info("Starting process to read file");
            String jsonString = this.fileReader.readFile("data.json");
            Info info = jsonService.parseJson(jsonString);
            log.info("File processed: "+info.toString());
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
