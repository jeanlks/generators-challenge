package com.challenge.generators.services;

import com.challenge.generators.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OperationRunner {

    private final OperationFactory operationFactory;


    public OperationRunner(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }


    @Async("threadPoolTaskExecutor")
    public void process(Generator generator, Info info) {
        for (List<Double> dataset : info.getDatasets()) {
            Instant now = Instant.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                    .withZone(ZoneId.systemDefault());

            String formattedTime = formatter.format(now);
            Map<OperationType, Operation<List<Double>>> operations= this.operationFactory.getAllOperations();
            OperationType operationType = OperationType.fromString(generator.getOperation());

            if (operations.containsKey(operationType)) {
                Double returnValue = operations.get(operationType).run(dataset);
                log.info(formattedTime+" - " +generator.getName()+" - "+returnValue);
                try {
                    Thread.sleep(generator.getInterval() * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } else {
                log.error("Operation does not exists!");
            }
        }
    }

}
