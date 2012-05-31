package ru.msu.imec.lab111.worm;

public class CalculationState {
    //    calculation step number
    private long index;
    //    real time of calculation moment
    private double time;
    private double wormHeadPosition;

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }


    public double getWormHeadPosition() {
        return wormHeadPosition;
    }

    public void setWormHeadPosition(double wormHeadPosition) {
        this.wormHeadPosition = wormHeadPosition;
    }

    public static CalculationState setInitialConditions(CalculationParams params) {
        CalculationState initialState = new CalculationState();
        initialState.setTime(0);
        initialState.setIndex(0);
//        TODO implement more
        return initialState;
    }
}
