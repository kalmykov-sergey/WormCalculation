package ru.msu.imec.lab111.worm;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Preforms calculation of magnetic field gradient with ANSYS CMF
 * User: Administrator
 * Date: 05.06.12
 */
public class AnsysConnector {

    private File ansysExecFile;

    public static void main(String[] args) {
        CalculationParams params = new CalculationParams();
//        calculate
//        MagneticForce force = calculate(params);
        File dbDirectory = new File("E:\\USERS\\Kalmykov\\Data\\Calc\\MagneticDB");
//        MagneticDB dataBase = DirectoryBasedMagneticDB.open(dbDirectory);
//        dataBase.save(force, params);

    }

    public void executeAdpl(String adplScript) {
        String cmd = adplScript;
        try {
            Process ansys = Runtime.getRuntime().exec(cmd);
            ansys.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAnsysExecFile() {
        if (this.ansysExecFile != null) {
            return this.ansysExecFile.getAbsolutePath();
        }
        Map<String, String> enviroment = System.getenv();
        if (!enviroment.containsKey("ANSYS_SYSDIR")) {
            return null;
        } else {
            String ansysDir = enviroment.get("ANSYS121_DIR");
            String ansysSysDir = enviroment.get("ANSYS_SYSDIR");
            return ansysDir + "/bin/" + ansysSysDir + "/ansys.exe";
        }
    }

    public String generateAdpl() throws IOException, ConfigurationException {

        File adplStubFile = new File("resources/coilFieldStub.adpl");
        File magneticPropertiesFile = new File("resources/magnetic.properties");
        Properties properties = new Properties();
        properties.load(new FileInputStream(magneticPropertiesFile));
        String adplStub = FileUtils.readFileToString(adplStubFile);

        for (String replacement : properties.stringPropertyNames()) {
            String value = properties.getProperty(replacement);
            if (replacement.startsWith("length.")) {
                value = String.valueOf(Float.parseFloat(value) * 0.01);
            }
            adplStub = adplStub.replace(replacement, value);
        }
        return adplStub;
    }
}
