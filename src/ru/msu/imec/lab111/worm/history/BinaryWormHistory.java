package ru.msu.imec.lab111.worm.history;

import org.apache.commons.io.FileUtils;
import ru.msu.imec.lab111.worm.CalculationParams;
import ru.msu.imec.lab111.worm.CalculationState;

import java.io.*;
import java.util.List;

public class BinaryWormHistory extends WormHistory {

    private File historyFile;
    private List<CalculationState> calculationStates;

    public static BinaryWormHistory fromFile(File file) throws IOException {
        CalculationParams params = BinaryWormHistory.FileToParams(file);
        BinaryWormHistory history = new BinaryWormHistory(params);
        history.readFromFile(file);
        return history;
    }

    public BinaryWormHistory(CalculationParams params) {
        super(params);
        this.historyFile = BinaryWormHistory.ParamsToFile(params);
    }

    public static File ParamsToFile(CalculationParams params) {
        return new File(String.format("%1$f.hz", params.getFrequency()));
    }

    public static CalculationParams FileToParams(File file) {
        CalculationParams params = new CalculationParams();
        double frequency = (double) (Float.parseFloat(file.getName()));
        params.setFrequency(frequency);
        return params;
    }

    @Override
    public void push(CalculationState state) {
        calculationStates.add(state);
    }

    @Override
    public CalculationState pop() {
        return calculationStates.remove(calculationStates.size() - 1);
    }

    private void readFromFile(File file) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(file);
        int recordSizeInBytes = 7;
        int recordsCount = bytes.length / recordSizeInBytes;
        if (bytes.length % recordSizeInBytes != 0) {
            throw new IOException("File consistency is broken: file end is ignored");
        }
        for (int recordIndex = 0; recordIndex < recordsCount; recordIndex++) {
            int recordStartIndex = recordIndex * recordSizeInBytes;


        }

    }

    private void writeToFile(File file) throws IOException {
        for (CalculationState state : this.calculationStates) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);
            outputStream.writeFloat((float) state.getTime());
//            for(state.get) {
//                outputStream.writeFloat();
//            }
            byteArrayOutputStream.writeTo(new FileOutputStream(file));
        }
    }

//    public static float[] readLine(DataInputStream dis) throws IOException {
//        float[] buff = new float[len()];
//        byte[] b = new byte[4];
//
//        dis.read(b);
//        long a = ((b[0] + 256) % 256) + 256 * ((b[1] + 256) % 256) + 65536 * ((b[2] + 256) % 256) + 65536 * ((b[3] + 256) % 256) * 256 * 256 * 256;
//        buff[0] = (float) (a * 0.000001);    // time
//        for (int i = 0; i < wlen(); i++) {
//            b[0] = dis.readByte();
//            b[1] = dis.readByte();
//            b[2] = dis.readByte();
//            int x = ((b[0] + 256) % 256) + 256 * ((b[1] + 256) % 256);
//            int y = ((b[2] + 256) % 256);
//            buff[1 + 2 * i] = (float) (x * 0.001 - 1);
//            buff[2 + 2 * i] = (float) (y * 0.005 - 0.5);
//        }
//        return buff;
//    }
}
