package ru.msu.imec.lab111.worm.magnetic;

import ru.msu.imec.lab111.worm.CalculationParams;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 05.06.12
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
public interface MagneticDB {

    /**
     * Saves magnetic force to DB
     *
     * @param force  field of force for
     * @param params parameters of ANSYS calculation
     */
    void save(MagneticForce force, CalculationParams params);

    /**
     * Loads into memory force for specified parameters
     *
     * @param params force calculation params
     * @return saved force field
     */
    MagneticForce load(CalculationParams params);
}
