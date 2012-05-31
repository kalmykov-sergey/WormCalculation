package ru.msu.imec.lab111.worm.ui;

import ru.msu.imec.lab111.worm.WormView;

import javax.swing.*;

public abstract class BaseMenuItem extends JMenuItem {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BaseMenuItem() {
        super();
        this.setText(this.name());
    }

    public abstract String name();

    public abstract void action(WormView wv);
}
