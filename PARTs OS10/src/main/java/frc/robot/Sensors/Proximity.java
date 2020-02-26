/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Add your docs here.
 */
public class Proximity {

    private static AnalogInput proximitySensor = new AnalogInput(1);

    // =====================================================================================
    // Define Singleton Pattern
    // =====================================================================================
    private static Proximity _staticProximity = new Proximity();

    public static Proximity getInstance() {
        return _staticProximity;
    }

    /**
     * Get the distance of the robot from an object
     * 
     * @return in feet
     */
    public double getDistance() {
        return ((proximitySensor.getVoltage() / 0.04883) * Constants.VOLTS_TO_DIST) / 30.48;
    }

}