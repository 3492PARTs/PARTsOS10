/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import edu.wpi.first.wpilibj.AnalogGyro;


/**
 * Add your docs here.
 */

public class Gyro {

        //=====================================================================================
    // Define Singleton Pattern
    //=====================================================================================
    private static Gyro _staticGyro = new Gyro();

    public static Gyro getInstance(){
         return _staticGyro;
    }
    public static AnalogGyro gyro = new AnalogGyro(0);


    public double getAngle(){
        return gyro.getAngle();
    }

    public static void zeroGyro() {
        gyro.reset();        
    }
}