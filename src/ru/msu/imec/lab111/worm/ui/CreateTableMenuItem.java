package ru.msu.imec.lab111.worm.ui;

import ru.msu.imec.lab111.worm.WormView;
import ru.msu.imec.lab111.worm.history.BinaryWormHistory;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


public class CreateTableMenuItem extends BaseMenuItem {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String name() {
        return "Open Dir";
    }

    @Override
    public void action(WormView wv) {

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setCurrentDirectory(new File("F:\\USERS\\Kalmykov\\Calculations\\using_eclipse"));
        int returnVal = fc.showOpenDialog(wv);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File dir = fc.getSelectedFile();
            wv.setTitle("Wait, please. Analyzing directory. Creating table...");
            try {
                FileWriter fstream = new FileWriter(dir.getAbsolutePath().concat("//table.log"));
                BufferedWriter out = new BufferedWriter(fstream);

                String[] files = dir.list();
                HashMap<Integer, String> map = new HashMap<Integer, String>();
                for (String filename : files) {
                    if (filename.endsWith(".bin")) {
                        File wf = new File(dir.getAbsolutePath().concat("//").concat(filename));
                        BinaryWormHistory history = BinaryWormHistory.fromFile(wf);
                        int hz = (int) (history.getParams().getFrequency());
//                        int hz = Integer.parseInt(filename.substring(0, filename.length() - 4));
//                        WormReader wr = new WormReader(wf);

                        String statistics = history.excelStatistics();
//                        String str = wr.excelString();
//						double v = wr.velocity();
//						wv.setTitle(String.format("%1$i %2$d", hz, v));
//						String str = String.format("%1$d %2$f %n", hz, v);
                        map.put(hz, String.format("%1$d\t\t0\t0\t%2$s%n", hz, statistics));

                    }
                }
                Integer[] arr = (Integer[]) map.keySet().toArray(new Integer[map.keySet().size()]);
                Arrays.sort(arr);
                for (Integer i : arr) {
                    out.write(map.get(i));
                }
                wv.setTitle("Worm View");
                out.close();
                Runtime.getRuntime().exec("notepad " + dir.getAbsolutePath() + "//table.log");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
