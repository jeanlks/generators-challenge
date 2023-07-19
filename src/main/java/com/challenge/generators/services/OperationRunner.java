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

    @Autowired
    private MaxOperation maxOperation;
    @Autowired
    private MinOperation minOperation;
    @Autowired
    private AverageOperation averageOperation;
    @Autowired
    private SumOperation sumOperation;

    @Async("threadPoolTaskExecutor")
    public void process(Generator generator, Info info) {
        for (List<Double> dataset : info.getDatasets()) {
            Instant now = Instant.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                    .withZone(ZoneId.systemDefault());

            String formattedTime = formatter.format(now);
            Map<OperationType, Operation<String, String, List<Double>>> operations= getAllOperations();
            OperationType operationType = OperationType.fromString(generator.getOperation());

            if (operations.containsKey(operationType)) {
                operations.get(operationType).run(formattedTime, generator.getName(), dataset);
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

    public Map<OperationType, Operation<String, String, List<Double>>> getAllOperations(){
        Map<OperationType, Operation<String, String, List<Double>>> functionMap = new HashMap<>();
        functionMap.put(OperationType.SUM, sumOperation);
        functionMap.put(OperationType.MAX, maxOperation);
        functionMap.put(OperationType.MIN, minOperation);
        functionMap.put(OperationType.AVERAGE, averageOperation);
        return functionMap;
    }
}
