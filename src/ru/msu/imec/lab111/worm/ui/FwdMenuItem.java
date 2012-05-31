package ru.msu.imec.lab111.worm.ui;

import ru.msu.imec.lab111.worm.WormView;

public class FwdMenuItem extends BaseMenuItem {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int _step;
    private String _name;

    public FwdMenuItem(int step, String name) {
        _step = step;
        _name = name;
        this.setText(name);

    }

    @Override
    public String name() {
        return _name;
    }

    @Override
    public void action(WormView wv) {
        wv.nextWorm(_step);
    }

}
