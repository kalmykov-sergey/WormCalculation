package ru.msu.imec.lab111.worm;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 30.05.12
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class InstallationParameters {
    private double channelInnerRadius;
    private double channelOuterRadius;
    private double channelLength;

    private int coilsNumber;

    public double getChannelInnerRadius() {
        return channelInnerRadius;
    }

    public void setChannelInnerRadius(double channelInnerRadius) {
        this.channelInnerRadius = channelInnerRadius;
    }

    public double getChannelOuterRadius() {
        return channelOuterRadius;
    }

    public void setChannelOuterRadius(double channelOuterRadius) {
        this.channelOuterRadius = channelOuterRadius;
    }

    public double getChannelLength() {
        return channelLength;
    }

    public void setChannelLength(double channelLength) {
        this.channelLength = channelLength;
    }

    public int getCoilsNumber() {
        return coilsNumber;
    }

    public void setCoilsNumber(int coilsNumber) {
        this.coilsNumber = coilsNumber;
    }
}
