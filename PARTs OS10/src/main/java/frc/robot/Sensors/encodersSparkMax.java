/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import frc.robot.Constants;
import frc.robot.subsystems.Drive;

/**
 * Add your docs here.
 */
public class EncodersSparkMax {
    private static EncodersSparkMax _staticEncodersSparkMax = new EncodersSparkMax();
    public static EncodersSparkMax getInstance()
    {
        return _staticEncodersSparkMax;
    }
    Drive drive = Drive.getInstance();
    private static double startPositionRight = 0;
    private static double startPositionLeft = 0;

    public double getDistanceMovedLeft(){
        return Math.abs(((drive.lefttest_1.getSelectedSensorPosition() - startPositionLeft) * Constants.WHEEL_CIRCUMFERENCE) /  Constants.GEAR_RATIO) ;
    }
    public double getDistanceMovedRight(){
        return Math.abs(((drive.righttest_1.getSelectedSensorPosition() - startPositionRight) * Constants.WHEEL_CIRCUMFERENCE) / Constants.GEAR_RATIO) ;
    }
    

    public void resetEncoders(){
        startPositionLeft = drive.lefttest_1.getSelectedSensorPosition();
        startPositionRight = drive.righttest_1.getSelectedSensorPosition();
    }

}
