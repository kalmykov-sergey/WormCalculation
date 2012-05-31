package ru.msu.imec.lab111.worm;

import ru.msu.imec.lab111.worm.ui.BaseMenuItem;
import ru.msu.imec.lab111.worm.ui.WormCanvas;
import ru.msu.imec.lab111.worm.ui.WormMenuBar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WormView extends JFrame implements ActionListener, ChangeListener {

    private WormCanvas _canvas;
    private WormMenuBar _menu;
    private Worm[] _worms;
    private ResReader _resReader;
    private int _worm_num;
    private int _hz;
    private JSlider _slider;

    public static void main(String[] args) {
        new WormView();
    }

    public WormView() {
        super();
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        _resReader = new ResReader("readme_and_res.txt");

        _menu = new WormMenuBar();
        for (JMenuItem menuItem : _menu.items()) {
            menuItem.addActionListener(this);
        }

        _canvas = new WormCanvas(_resReader);
        _canvas.setAlignmentX(Component.CENTER_ALIGNMENT);
        _canvas.setAlignmentY(Component.CENTER_ALIGNMENT);

        _slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        _slider.addChangeListener(this);
        _slider.setEnabled(false);

        this.setSize(800, 400);
        this.setJMenuBar(_menu);
        this.getContentPane().add(_slider);
        this.getContentPane().add(_canvas);

        this.setTitle("WormView");
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseMenuItem bmi = (BaseMenuItem) e.getSource();
        bmi.action(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            int percent = (int) source.getValue();
            this._worm_num = (int) (percent * 0.01 * (this._worms.length - 1));
            _canvas.setWorm(_worms[_worm_num]);
        }
    }

    public WormCanvas Canvas() {
        return _canvas;
    }

    public void setWorms(WormReader wr) {
        _worms = wr.allWorms();

        for (JMenuItem menuItem : _menu.items()) {
            menuItem.setEnabled(true);
        }
        _slider.setEnabled(true);
        _worm_num = 0;
        _canvas.setWorm(_worms[_worm_num]);
    }

    public void nextWorm(int step) {
        _worm_num += step;
        if (_worm_num >= _worms.length) {
            _worm_num = _worms.length - 1;
        }
        if (_worm_num < 0) {
            _worm_num = 0;
        }
        _canvas.setWorm(_worms[_worm_num]);
        double rate = (1.0 * _worm_num) / _worms.length;
        int percent = (int) (100 * rate);
        _slider.setValue(percent);
        percent = _slider.getValue();
    }

    private static final long serialVersionUID = 1;

    public void set_hz(int _hz) {
        this._hz = _hz;
        this._canvas.hz = _hz;
    }

    public int get_hz() {
        return _hz;
    }


}
