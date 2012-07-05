package ru.msu.imec.lab111.worm;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.*;

public class AnsysConnectorTest {
    @Test
    public void testGetAnsysExecFile() throws Exception {
        AnsysConnector connector = new AnsysConnector();
        String ansysExecutable = connector.getAnsysExecFile().getAbsolutePath();
        assertNotNull(ansysExecutable);
        File file = new File(ansysExecutable);
        assertTrue(file.exists());
        assertTrue(file.canExecute());
    }

    @Test
    public void testGenerateAdpl() throws Exception {
        AnsysConnector connector = new AnsysConnector();
        String adpl = connector.generateAdpl();
        // check something was generated
        assertNotNull(adpl);
        // check CGS2SI work correct
        assertTrue(adpl.contains("Infty=0.05"));
    }

    @Test
    public void testExecuteAdpl() throws Exception {
        AnsysConnector connector = new AnsysConnector();
        File outputFile = connector.getOutputAnsysFile();
        outputFile.delete();
        // check if previous output was deleted
        assertFalse(outputFile.exists());
        String adplSimpleScript = "/CLE\n/PREP7\n/UNITS, SI";
        connector.executeAdpl(adplSimpleScript);
        // check output was written to file
        assertTrue(outputFile.exists());
        String output = FileUtils.readFileToString(outputFile);
        // check ansys finished correctly
        assertTrue(output.contains("E N D   A N S Y S   S T A T I S T I C S"));
    }
}
