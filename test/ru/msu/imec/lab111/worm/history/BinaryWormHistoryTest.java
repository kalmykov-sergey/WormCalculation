package ru.msu.imec.lab111.worm.history;

import org.junit.Test;
import ru.msu.imec.lab111.worm.CalculationParams;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class BinaryWormHistoryTest {
    @Test
    public void testFileNameToParams() throws Exception {
        String fileName = "50.hz";
        File file = new File(fileName);
        CalculationParams params = BinaryWormHistory.FileToParams(file);
        assertEquals(params.getFrequency(), 50);
        File file1 = BinaryWormHistory.ParamsToFile(params);
        assertEquals(file1.getName(), fileName);
    }
}
