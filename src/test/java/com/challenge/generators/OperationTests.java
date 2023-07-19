package com.challenge.generators;

import com.challenge.generators.model.AverageOperation;
import com.challenge.generators.model.MaxOperation;
import com.challenge.generators.model.MinOperation;
import com.challenge.generators.model.SumOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class OperationTests {

    @Autowired
    SumOperation sumOperation;

    @Autowired
    AverageOperation averageOperation;

    @Autowired
    MaxOperation maxOperation;

    @Autowired
    MinOperation minOperation;

    @Test
    public void testSumShouldPass(){
        Double returnValue = sumOperation.run("test", "test", List.of(1.0, 5.0));
        assertEquals(returnValue, 6.0);
    }

    @Test
    public void testSumShouldFail(){
        Double returnValue = sumOperation.run("test", "test", List.of(1.0, 5.0));
        assertNotEquals(returnValue, 6.1);
    }

    @Test
    public void testAverageShouldPass(){
        Double returnValue = averageOperation.run("test", "test", List.of(4.0, 5.0));
        assertEquals(returnValue, 4.5);
    }

    @Test
    public void testAverageShouldFail(){
        Double returnValue = averageOperation.run("test", "test", List.of(4.0, 5.0));
        assertNotEquals(returnValue, 6.0);
    }

    @Test
    public void testMinShouldPass(){
        Double returnValue = minOperation.run("test", "test", List.of(4.0, 5.0, 1.0, 0.2, 40.7));
        assertEquals(returnValue, 0.2);
    }

    @Test
    public void testMinShouldFail(){
        Double returnValue = minOperation.run("test", "test", List.of(4.0, 5.0, 1.0, 0.2, 40.7));
        assertNotEquals(returnValue, 5.0);
    }

    @Test
    public void testMaxShouldPass(){
        Double returnValue = maxOperation.run("test", "test", List.of(4.0, 5.0, 55.0, 37.0));
        assertEquals(returnValue, 55.0);
    }

    @Test
    public void testMaxShouldFail(){
        Double returnValue = maxOperation.run("test", "test", List.of(4.0, 5.0, 55.0, 37.0));
        assertNotEquals(returnValue, 37.0);
    }
}
