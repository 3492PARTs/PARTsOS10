/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Add your docs here.
 */
public class PhotoElectricSensor {
    private static PhotoElectricSensor _staticPESensor = new PhotoElectricSensor();
    public AnalogInput PESensor = new AnalogInput(2);
    public static PhotoElectricSensor getInstance()
    {
        return _staticPESensor;
    }

}
