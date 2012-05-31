package ru.msu.imec.lab111.worm;

public class Worm {

    private float[] _x;
    private float[] _y;
    private float _time;
    private int _num;

    public Worm(int points_number) {
        _num = points_number;
        _x = new float[_num];
        _y = new float[_num];
    }

    public Worm(float[] buff) {
        _num = (buff.length - 1) / 2;
        _x = new float[_num];
        _y = new float[_num];
        _time = buff[0];
        for (int i = 1; i < _num; i++) {
            _x[i - 1] = buff[2 * i - 1];
            _y[i - 1] = buff[2 * i];
        }

    }

    public double x(int i) {
        return _x[i];
    }

    public double y(int i) {
        return _y[i];
    }

    public double time() {
        return _time;
    }

    public int points_number() {
        return _num;
    }
}
