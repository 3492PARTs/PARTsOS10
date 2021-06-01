/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * Gyro Sensor on the robot
 */

public class GyroSensor {
    private static GyroSensor _staticGyro = new GyroSensor();
    private AnalogGyro gyro;
    

    // =====================================================================================
    // Define Singleton Pattern
    // =====================================================================================
    public static GyroSensor getInstance() {

        return _staticGyro;
    }


    public GyroSensor() {
        gyro = new AnalogGyro(0);// Todo: give port numbers
    }

    public Gyro getGyro(){
        return gyro;
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