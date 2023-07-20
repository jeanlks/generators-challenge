package com.challenge.generators.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OperationFactory {
    private final MaxOperation maxOperation;

    private final MinOperation minOperation;

    private final AverageOperation averageOperation;

    private final SumOperation sumOperation;


    public OperationFactory(MaxOperation maxOperation, MinOperation minOperation,
                           AverageOperation averageOperation, SumOperation sumOperation) {
        this.maxOperation = maxOperation;
        this.minOperation = minOperation;
        this.averageOperation = averageOperation;
        this.sumOperation = sumOperation;
    }

    public Map<OperationType, Operation<List<Double>>> getAllOperations(){
        Map<OperationType, Operation<List<Double>>> functionMap = new HashMap<>();
        functionMap.put(OperationType.SUM, sumOperation);
        functionMap.put(OperationType.MAX, maxOperation);
        functionMap.put(OperationType.MIN, minOperation);
        functionMap.put(OperationType.AVERAGE, averageOperation);
        return functionMap;
    }

}
