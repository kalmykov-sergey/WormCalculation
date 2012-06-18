package ru.msu.imec.lab111.worm.history;

import ru.msu.imec.lab111.worm.CalculationParams;
import ru.msu.imec.lab111.worm.CalculationState;

/**
 * Just console output: no dataSource, no pop, only printOnPush
 */
public class ConsoleWormHistory extends WormHistory {
    public ConsoleWormHistory(CalculationParams params) {
        super(params);
//        empty
    }

    @Override
    public void push(CalculationState state) {
        System.out.format("t=%1$f head=%2$f", state.getTime(), state.getWormHeadPosition());
    }

    @Override
    public CalculationState pop() {
//        not used never
        return null;
    }
}
