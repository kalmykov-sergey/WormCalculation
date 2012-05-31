package ru.msu.imec.lab111.worm.ui;

import ru.msu.imec.lab111.worm.WormView;

public class QuitMenuItem extends BaseMenuItem {


    @Override
    public String name() {
        return "Quit";
    }

    @Override
    public void action(WormView wv) {
        System.exit(0);
    }


    private static final long serialVersionUID = 1;
}
