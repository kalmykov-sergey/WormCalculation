package ru.msu.imec.lab111.worm;

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
    private final String adplStubFileName = "coilFieldStub.adpl";
    private final String adplFileName = "coilField.adpl";
    private final String outputAnsysFileName = "coilField.output";
    private final String magneticPropertiesFileName = "magnetic.properties";
    private final String ansysExecCmd =
            "$(ANSYSEXE) -p ANE3FL -dir \"$(ANSYSDIR)\" -j \"coil_field\" -s read -l en-us -b " +
                    "-i \"$(ANSYSINPUT)\" -o \"$(ANSYSOUTPUT)\"";
    private final String directoryForMagneticDB = "E:\\USERS\\Kalmykov\\Data\\Calc\\MagneticDB";

    public static void main(String[] args) {
        CalculationParams params = new CalculationParams();
        AnsysConnector ansysConnector = new AnsysConnector();

        try {
            String adpl = ansysConnector.generateAdpl();
            ansysConnector.executeAdpl(adpl);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//        calculate
//        MagneticForce force = calculate(params);
        File dbDirectory = new File(ansysConnector.directoryForMagneticDB);
//        MagneticDB dataBase = DirectoryBasedMagneticDB.open(dbDirectory);
//        dataBase.save(force, params);

    }

    public void executeAdpl(String adplScript) throws IOException {
        // clear previous output
        getOutputAnsysFile().delete();
        File scriptFile = new File(this.adplFileName).getAbsoluteFile();
        String ansysDirPath = scriptFile.getParent();
        FileUtils.writeStringToFile(scriptFile, adplScript);
        String cmd = this.ansysExecCmd
                .replace("$(ANSYSEXE)", getAnsysExecFile().getAbsolutePath())
                .replace("$(ANSYSDIR)", ansysDirPath)
                .replace("$(ANSYSINPUT)", scriptFile.getAbsolutePath())
                .replace("$(ANSYSOUTPUT)", getOutputAnsysFile().getAbsolutePath());
        try {
            Process ansys = Runtime.getRuntime().exec(cmd);
            ansys.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getAnsysExecFile() {
        if (this.ansysExecFile != null) {
            return this.ansysExecFile;
        }
        Map<String, String> environment = System.getenv();
        if (!environment.containsKey("ANSYS_SYSDIR")) {
            return null;
        } else {
            String ansysDir = environment.get("ANSYS121_DIR");
            String ansysSysDir = environment.get("ANSYS_SYSDIR");
            this.ansysExecFile = new File(ansysDir + "/bin/" + ansysSysDir + "/ansys.exe");
            return this.ansysExecFile;
        }
    }

    public String generateAdpl() throws IOException {

        File adplStubFile = new File(this.adplStubFileName);
        File magneticPropertiesFile = new File(this.magneticPropertiesFileName);
        Properties properties = new Properties();
        properties.load(new FileInputStream(magneticPropertiesFile));
        String adplStub = FileUtils.readFileToString(adplStubFile);
        // convert CGS units to SI units
        for (String replacement : properties.stringPropertyNames()) {
            String value = properties.getProperty(replacement);
            // all distance properties must be converted from cm to m
            if (replacement.startsWith("length.")) {
                value = String.valueOf(Float.parseFloat(value) * 0.01);
            }
            adplStub = adplStub.replace(replacement, value);
        }
        return adplStub;
    }

    public File getOutputAnsysFile() {
        return new File(this.outputAnsysFileName);
    }


}
