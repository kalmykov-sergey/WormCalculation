package ru.msu.imec.lab111.worm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class WormReader {

    private BufferedReader br;
    private FileInputStream fis;
    private DataInputStream dis;
    private static final int _points = 50;

    private static int wlen() {
        return _points + 2 * 2;
    }

    private static int len() {
        return wlen() * 2 + 1;
    }

    public WormReader(File file) {
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis));
            dis = new DataInputStream(fis);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }

    protected void finalize() {
        try {
            br.close();
            dis.close();
            fis.close();
        } catch (IOException e) {
            System.exit(1);
        }

    }

    public float[] readLine(DataInputStream dis) throws IOException {
        float[] buff = new float[len()];
        byte[] b = new byte[4];

        dis.read(b);
        long a = ((b[0] + 256) % 256) + 256 * ((b[1] + 256) % 256) + 65536 * ((b[2] + 256) % 256) + 65536 * ((b[3] + 256) % 256) * 256 * 256 * 256;
        buff[0] = (float) (a * 0.000001);    // time
        for (int i = 0; i < wlen(); i++) {
            b[0] = dis.readByte();
            b[1] = dis.readByte();
            b[2] = dis.readByte();
            int x = ((b[0] + 256) % 256) + 256 * ((b[1] + 256) % 256);
            int y = ((b[2] + 256) % 256);
            buff[1 + 2 * i] = (float) (x * 0.001 - 1);
            buff[2 + 2 * i] = (float) (y * 0.005 - 0.5);
        }
        return buff;
    }

    public double velocity() {
        float[] buff = new float[len()];
        double distance = 5;
        double start = 0, finish = 0;
        try {
            while (dis.available() != 0) {
                buff = readLine(dis);
                if ((buff[5] < distance) && (start == 0)) {
                    start = buff[0];
                }
                if ((buff[5] < 0.001) && (finish == 0)) {
                    finish = buff[0];
                }
            }
            if (finish == 0) {
                finish = buff[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distance / (finish - start);
    }

    public Worm[] allWorms() {
        List<Worm> worms = new ArrayList<Worm>();
        float[] buff = new float[len()];

        try {
            while (dis.available() != 0) {
                buff = readLine(dis);
                Worm w = new Worm(buff);
                worms.add(w);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return worms.toArray(new Worm[worms.size()]);
    }

    public String excelString() {
        float[] buff = new float[len()];
        float[] times = new float[8];
        int stroke = 7;
        try {
            while (dis.available() != 0) {
                buff = readLine(dis);
                if ((buff[5] <= stroke)) {
                    times[stroke] = buff[0];
                    stroke += -1;
                }
            }
            times[0] = buff[0];
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str = "";
        for (float f : times) {
            str = String.valueOf(f).concat("\t").concat(str);
        }
        float velocity = 5 / (times[0] - times[5]);
        str = str.concat("\t").concat(String.valueOf(velocity));

        return str;
    }
}
