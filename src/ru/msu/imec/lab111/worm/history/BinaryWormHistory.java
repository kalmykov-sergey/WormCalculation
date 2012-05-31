package ru.msu.imec.lab111.worm.history;

import ru.msu.imec.lab111.worm.CalculationParams;
import ru.msu.imec.lab111.worm.CalculationState;

import java.io.File;
import java.util.List;

public class BinaryWormHistory implements WormHistory {

    private File historyFile;
    private List<CalculationState> calculationStates;

    public BinaryWormHistory(CalculationParams params) {
        setDataSource(params);
    }

    @Override
    public void setDataSource(CalculationParams params) {

    }

    @Override
    public void push(CalculationState state) {
        calculationStates.add(state);
    }

    @Override
    public CalculationState pop() {
        return calculationStates.remove(calculationStates.size() - 1);
    }
}
