package ru.msu.imec.lab111.worm.ui;

import ru.msu.imec.lab111.worm.ResReader;
import ru.msu.imec.lab111.worm.Worm;

import javax.swing.*;
import java.awt.*;


public class WormCanvas extends JPanel {

    private Worm _worm;
    private ResReader _reader;
    private int _position, _init_position;
    private int _mid_y;
    private double _scale;
    public double hz;

    public WormCanvas(ResReader reader) {
        _reader = reader;
        _init_position = _position = _reader.distance() - (1 + _reader.coils_in_pause());
    }

    public void paintComponent(Graphics g) {
        drawChannel(g);

        for (int zug_number = 0; zug_number < 5; zug_number++) {
            int zug_start = _position - 3 + zug_number * (3 + _reader.coils_in_pause());
            int zug_finish = _position - 1 + zug_number * (3 + _reader.coils_in_pause());
            if (zug_start > _reader.coils_number())
                break;
            if (zug_start < 1)
                zug_start = 1;
            if (zug_finish < 1)
                continue;
            if (zug_finish > _reader.coils_number())
                zug_finish = _reader.coils_number();

            for (int cur_coil = zug_start; cur_coil <= zug_finish; cur_coil++)
                drawCoil(g, cur_coil);
        }

        drawWorm(g);
    }

    private void drawWorm(Graphics g) {
        if (_worm != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke((int) (_reader.channel_diameter() * _scale / 2)));
            for (int i = 2; i < _worm.points_number() - 4; i++) {
                int x1 = (int) ((1 + _worm.x(i)) * _scale),
                        y1 = _mid_y - (int) (_worm.y(i) * _scale),
                        x2 = (int) ((1 + _worm.x(i + 1)) * _scale),
                        y2 = _mid_y - (int) (_worm.y(i + 1) * _scale);
                g2.drawLine(x1, y1, x2, y2);
            }
            g.drawString(String.valueOf("time = " + _worm.time() + "    x[2]=" + _worm.x(2)), 20, 20);
        }
        //this.update(g);
    }

    public void drawChannel(Graphics g) {

        getScale();
        int x1 = (int) (1 * _scale),
                x2 = (int) ((1 + _reader.coils_number()) * _scale),
                y1 = _mid_y + (int) (_reader.channel_diameter() / 2 * _scale),
                y2 = _mid_y - (int) (_reader.channel_diameter() / 2 * _scale);

        g.drawLine(x1, y1, x2, y1);
        g.drawLine(x1, y2, x2, y2);
    }

    public void drawCoil(Graphics g, int number) {
        getScale();
        Color c = g.getColor();
        g.setColor(Color.red);
        double channel_radius = _reader.channel_diameter() / 2;

        int is_reflected = 1 - 2 * (number % 2);
        int x1 = (int) ((number - _reader.coil_width() / 2) * _scale),
                x2 = (int) ((number + _reader.coil_width() / 2) * _scale),
                y1 = _mid_y + (int) ((is_reflected * (_reader.coil_height() + channel_radius)) * _scale),
                y2 = _mid_y + (int) ((is_reflected * (channel_radius)) * _scale);

        g.drawRect(x1, y2 * (1 - (number % 2)) + y1 * (number % 2), x2 - x1, Math.abs(y2 - y1));
        g.setColor(c);
    }

    public void getScale() {
        int width = getWidth();
        _scale = width / (3 + _reader.coils_number());
        _mid_y = (int) ((0.5 + _reader.coil_height() + _reader.channel_diameter() / 2) * _scale);
    }

    private static final long serialVersionUID = 1;

    public void setWorm(Worm worm) {
        _worm = worm;
        setPosition(worm.time());
        int height = getHeight(),
                width = getWidth();
        this.getParent().setSize(width + 1, height + 1); // TODO: find normal way to total repaint
        this.getParent().setSize(width, height);
    }

    public void setPosition(double time) {
        _position = _init_position + (int) (time * hz) % (3 + _reader.coils_in_pause());
    }
}
