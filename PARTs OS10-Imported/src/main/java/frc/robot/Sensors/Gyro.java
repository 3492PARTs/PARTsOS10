/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import edu.wpi.first.wpilibj.AnalogGyro;

/**
 * Gyro Sensor on the robot
 */

public class Gyro {
    private static Gyro _staticGyro = new Gyro();
    private AnalogGyro gyro;

    // =====================================================================================
    // Define Singleton Pattern
    // =====================================================================================
    public static Gyro getInstance() {

        return _staticGyro;
    }


    public Gyro() {
        gyro = new AnalogGyro(0);
    }

    public AnalogGyro getGyro(){
        return gyro;
    }

    /**
     * Initialize the gyro
     */
    public void initGyro() {
        gyro.initGyro();
    }

    /**
     * Calibrate the gyro
     */
    public void calibrate() {
        gyro.calibrate();
    }

    /**
     * Get the andle from the gyro
     * 
     * @return gyro angle
     */
    public double getAngle() {
        return gyro.getAngle();
    }

    /**
     * Zero the gyro
     */
    public void zeroGyro() {
        gyro.reset();
    }
}