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
    static Gyro _staticGyro = new Gyro();
    AnalogGyro gyro;
    //=====================================================================================
    // Define Singleton Pattern
    //=====================================================================================
   public Gyro(){
        gyro = new AnalogGyro(0);
    }

    public AnalogGyro getGyro(){
    return gyro;   
    }


    public static Gyro getInstance(){
         
        return _staticGyro;    
    }



    public double getAngle(){
        return gyro.getAngle();
    }

    public void zeroGyro() {
        gyro.reset();        
    }
}