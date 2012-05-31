package ru.msu.imec.lab111.worm;

public class CalculationParams {
    private WormParameters wormParameters;
    private InstallationParameters installationParameters;
    private double frequency;
    private double staticTimeStep;
    private double timeStep;
    private double dimensionStep;

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getStaticTimeStep() {
        return staticTimeStep;
    }

    public void setStaticTimeStep(double staticTimeStep) {
        this.staticTimeStep = staticTimeStep;
    }

    public double getTimeStep() {
        return timeStep;
    }

    public void setTimeStep(double timeStep) {
        this.timeStep = timeStep;
    }

    public double getDimensionStep() {
        return dimensionStep;
    }

    public void setDimensionStep(double dimensionStep) {
        this.dimensionStep = dimensionStep;
    }

    public WormParameters getWormParameters() {
        return wormParameters;
    }

    public void setWormParameters(WormParameters wormParameters) {
        this.wormParameters = wormParameters;
    }

    public InstallationParameters getInstallationParameters() {
        return installationParameters;
    }

    public void setInstallationParameters(InstallationParameters installationParameters) {
        this.installationParameters = installationParameters;
    }
}
