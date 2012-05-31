package ru.msu.imec.lab111.worm;

import ru.msu.imec.lab111.worm.history.BinaryWormHistory;
import ru.msu.imec.lab111.worm.history.ConsoleWormHistory;
import ru.msu.imec.lab111.worm.history.WormHistory;
import ru.msu.imec.lab111.worm.magnetic.MagneticForce;
import ru.msu.imec.lab111.worm.magnetic.SimpleMagneticForce;

public class WormConsoleApp {

    //    motion in experiment has maximum duration about 10 secs
    private static double maximumTime = 10;
    //    and movie recording stops when left end (head) of the worm crosses zero-stroke of attached ruler
    private static double finish = 0;

    public static void main(String[] args) {
//        specify global parameters to calculate - worm props, channel props, etc.
        CalculationParams params = new CalculationParams();
        int N = 50;
        double cTau = 1000;
        double h = params.getWormParameters().getWormLength() / (N - 1);
        params.setDimensionStep(h);
        params.setStaticTimeStep(h * h / cTau);
//        TODO fulfil static params from config file
        /*
          let's use experimental frequencies - with 0.1 ms step
          experimental most interesting frequency range is between 10 and 400 Hz = (2.5ms, 10ms)
          Note than experimental impulse generator have the restriction - only 2 digits on display
         */
//        TODO read experimental frequencies from exp-movies directory
        for (int impulseGeneratorDigits = 99; impulseGeneratorDigits >= 25; impulseGeneratorDigits--) {
            double ms = 0.1 * impulseGeneratorDigits;
            double frequency = 1.0 / ms;
            params.setFrequency(frequency);
            // frequency-depend calculation time step (in inverse ratio to frequency)
            params.setTimeStep(params.getStaticTimeStep() / frequency);
            calc(params);
        }
    }

    public static void calc(CalculationParams params) {
        try {
            WormHistory history = new BinaryWormHistory(params);
            WormHistory consoleHistory = new ConsoleWormHistory(params);

            CalculationState state = CalculationState.setInitialConditions(params);
            MagneticForce magneticForce = new SimpleMagneticForce(params);

            while ((state.getTime() < maximumTime) && (state.getWormHeadPosition() > finish)) {
                magneticForce.setPosition(state.getTime());
                state = calculationStep(state, params.getTimeStep());

                if (state.getIndex() % 100 == 0) {// write to file
                    history.push(state);
                }
                if (state.getIndex() % 10000 == 0) { // write to console
                    consoleHistory.push(state);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CalculationState calculationStep(CalculationState state, double timeStep) {
//        TODO implement more
        double time = state.getTime();
        state.setTime(time + timeStep);
        return state;
    }
}
