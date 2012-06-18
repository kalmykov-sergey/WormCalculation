package ru.msu.imec.lab111.worm;

import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class AnsysConnectorTest {
    @Test
    public void testGetAnsysExecFile() throws Exception {
        AnsysConnector connector = new AnsysConnector();
        String ansysExec = connector.getAnsysExecFile();
        assertNotNull(ansysExec);
        File file = new File(ansysExec);
        assertTrue(file.exists());
    }

    @Test
    public void testAdpl() throws Exception {
        AnsysConnector connector = new AnsysConnector();
        String adpl = connector.generateAdpl();
        assertNotNull(adpl);
        assertTrue(adpl.contains("Infty=0.05"));
    }
}
