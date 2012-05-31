package ru.msu.imec.lab111.worm;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 30.05.12
 * Time: 14:34
 * To change this template use File | Settings | File Templates.
 */
public class WormParameters {
    private double wormLength;
    private double wormRadius;
    private double youngModulus;

    public double getWormLength() {
        return wormLength;
    }

    public void setWormLength(double wormLength) {
        this.wormLength = wormLength;
    }

    public double getWormRadius() {
        return wormRadius;
    }

    public void setWormRadius(double wormRadius) {
        this.wormRadius = wormRadius;
    }

    public double getYoungModulus() {
        return youngModulus;
    }

    public void setYoungModulus(double youngModulus) {
        this.youngModulus = youngModulus;
    }
}
