/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import edu.wpi.first.wpilibj.DigitalInput;


/**
 * Photo Electric sensor to count the balls
 */

public class PhotoElectricSensor {
    private int counter = 0;
    private DigitalInput photoEyeIntake = new DigitalInput(9);
    private DigitalInput photoEyeShoot = new DigitalInput(8);
    private static long timeAtCallFront;
    private static long timeAtCallBack;
    private boolean lockShootSens = false;
    private boolean lockIntakeSens = false;

    // =====================================================================================
    // Define Singleton Pattern
    // =====================================================================================
    private static PhotoElectricSensor _staticPhotoEye = new PhotoElectricSensor();

    public static PhotoElectricSensor getInstance() {
        return _staticPhotoEye;
    }

    /**
     * Run the PE sensor and count ballss
     */
    public void runPESensor() {
        if (photoEyeIntake.get() && !lockIntakeSens) {
            lockIntakeSens = true;
            counter++;
        }

        if (!photoEyeIntake.get()) {
            lockIntakeSens = false;
        }

        if (photoEyeShoot.get() && !lockShootSens) {
            lockShootSens = true;
            counter--;
        }

        if (!photoEyeShoot.get()) {
            lockIntakeSens = false;
        }

    }

    /**
     * Reset the counter
     */
    public void counterReset() {
        counter = 0;
    }

    /**
     * Get the counter
     * 
     * @return counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Get the Photot Eye Intake sensor DI
     * 
     * @return photoEyeIntake
     */
    public DigitalInput getPhotoEyeIntake() {
        return photoEyeIntake;
    }

    /**
     * Get the Photot Eye Shoot sensor DI
     * 
     * @return photoEyeShoot
     */
    public DigitalInput getPhotoEyeShoot() {
        return photoEyeShoot;
    }
}
