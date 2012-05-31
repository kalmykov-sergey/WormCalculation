package ru.msu.imec.lab111.worm.history;

import ru.msu.imec.lab111.worm.CalculationParams;
import ru.msu.imec.lab111.worm.CalculationState;

public interface WormHistory {
    void setDataSource(CalculationParams params);

    void push(CalculationState state);

    CalculationState pop();
}
