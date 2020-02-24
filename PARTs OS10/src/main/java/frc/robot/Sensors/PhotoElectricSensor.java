/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Add your docs here.
 */

public class PhotoElectricSensor {
    int counter = 0;
    public DigitalInput photoEyeIntake = new DigitalInput(9);
    public DigitalInput photoEyeShoot = new DigitalInput(8);
    private long timeAtCall;
    //=====================================================================================
    // Define Singleton Pattern
    //=====================================================================================
    private static PhotoElectricSensor _staticPhotoEye = new PhotoElectricSensor();
    public static PhotoElectricSensor getInstance()
    {
        return _staticPhotoEye;
    }

    /**
     * 
     * @param sensor = photoelectrc sensor reading
     * @return true if ball is breaking plane, false if not
     */
    public void counterIncrease(){
        if(timeAtCall + 200 < System.currentTimeMillis()){
        counter++;
        Robot.photolockback = false;
    }
        else;
    }
    public void counterDecrease(){
        if(timeAtCall + 100 < System.currentTimeMillis()){
        counter--;
        Robot.photolockfront = true;

        }
        else;
    }

    public void lockTimer(){
       timeAtCall = System.currentTimeMillis();
    }
    public void counterReset(){
        counter = 0;
    }
    public int getCounter(){
        return counter;
    }
}
