package ru.msu.imec.lab111.worm;

import org.junit.Test;

public class WormConsoleAppTest {


    @Test
    public void testCalc() throws Exception {
        CalculationParams params = null;
        WormConsoleApp.calc(params);
    }

    @Test
    public void testMain() throws Exception {
        String[] args = {"-wormFile", "worm.cfg"};
        WormConsoleApp.main(args);
    }
}
