package ru.msu.imec.lab111.worm;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CalculationStateTest {

    @Test
    public void testSetInitialConditions() throws Exception {
        CalculationParams params = new CalculationParams();
        CalculationState state = CalculationState.setInitialConditions(params);
        assertEquals(state.getTime(), 0);
        assertEquals(state.getIndex(), 0);
//        assertEquals(state.getWormHeadPosition(), params.ge);

    }
}
