package ru.msu.imec.lab111.worm.history;

import ru.msu.imec.lab111.worm.CalculationParams;
import ru.msu.imec.lab111.worm.CalculationState;

public abstract class WormHistory {

    protected CalculationParams params;

    protected WormHistory(CalculationParams params) {
        this.params = params;
    }

    public CalculationParams getParams() {
        return params;
    }

    public abstract void push(CalculationState state);

    public abstract CalculationState pop();

    public String excelStatistics() {
        String str = "";
        try {
            float[] times = new float[8];
            int stroke = 7;
            CalculationState state = this.pop();
            while (state != null) {
                if (state.getWormHeadPosition() < stroke) {
                    times[stroke] = (float) state.getTime();
                    stroke += -1;

                }
                state = this.pop();
            }
            times[0] = (float) state.getTime();

            for (float f : times) {
                str = String.valueOf(f).concat("\t").concat(str);
            }
            float velocity = 5 / (times[0] - times[5]);
            str = str.concat("\t").concat(String.valueOf(velocity));
        } catch (Exception e) {
            return "";
        }
        return str;
    }
}
