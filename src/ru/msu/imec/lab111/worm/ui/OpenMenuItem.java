package ru.msu.imec.lab111.worm.ui;

import ru.msu.imec.lab111.worm.WormReader;
import ru.msu.imec.lab111.worm.WormView;

import javax.swing.*;
import java.io.File;


public class OpenMenuItem extends BaseMenuItem {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String name() {
        return "Open";
    }

    @Override
    public void action(WormView wv) {

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("F:\\USERS\\Kalmykov\\Calculations\\using_eclipse"));
        int returnVal = fc.showOpenDialog(wv);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filename = file.getName();
            if (filename.endsWith(".bin")) {
                wv.setTitle("Wait, please. Loading...");
                WormReader wr = new WormReader(file);
                wv.setWorms(wr);
                int hz = Integer.parseInt(filename.substring(0, filename.length() - 4));
                wv.setTitle(String.valueOf(hz) + " Hz");
                wv.set_hz(hz);
            }
        }
    }
}
