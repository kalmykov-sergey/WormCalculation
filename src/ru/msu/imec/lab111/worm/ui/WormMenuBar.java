package ru.msu.imec.lab111.worm.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class WormMenuBar extends JMenuBar {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JMenuItem quit, open, table, about, forward, back, ff, bb;


    public WormMenuBar() {

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        JMenu navMenu = new JMenu("Navigation");

        quit = new QuitMenuItem();
        about = new AboutMenuItem();
        open = new OpenMenuItem();
        table = new CreateTableMenuItem();
        forward = new FwdMenuItem(10, "Forward");
        forward.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, ActionEvent.ALT_MASK));
        forward.setMnemonic(KeyEvent.VK_RIGHT);
        forward.setEnabled(false);
        back = new FwdMenuItem(-10, "Back");
        back.setEnabled(false);
        back.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, ActionEvent.ALT_MASK));
        ff = new FwdMenuItem(100, "FF");
        ff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, ActionEvent.ALT_MASK));
        ff.setEnabled(false);
        bb = new FwdMenuItem(-100, "BB");
        bb.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, ActionEvent.ALT_MASK));
        bb.setEnabled(false);

        fileMenu.add(open);
        fileMenu.add(table);
        fileMenu.add(quit);

        helpMenu.add(about);

        navMenu.add(forward);
        navMenu.add(back);
        navMenu.add(ff);
        navMenu.add(bb);

        this.add(fileMenu);
        this.add(navMenu);
//		this.add(helpMenu);
    }

    public JMenuItem[] items() {
        JMenuItem[] items = {quit, open, table, about, forward, back, ff, bb};
        return items;
    }

}
