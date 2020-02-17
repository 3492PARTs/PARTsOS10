/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import frc.robot.Constants;

import com.revrobotics.CANEncoder;
import frc.robot.subsystems.DriveSparkMax;


public class EncodersSparkMax {
    private final DriveSparkMax drive = DriveSparkMax.getInstance();
    private CANEncoder rightEncoder = drive.Right1.getEncoder();
    private CANEncoder leftEncoder = drive.Left1.getEncoder();
    private CANEncoder fix1 = drive.Left2.getEncoder();
    private static EncodersSparkMax _staticEncodersSparkMax = new EncodersSparkMax();
    public static EncodersSparkMax getInstance()
    {
        return _staticEncodersSparkMax;
    }
    private static double startPositionRight = 0;
    private static double startPositionLeft = 0;

    public double getDistanceMovedLeft(){
        return Math.abs(((leftEncoder.getPosition() - startPositionLeft) * Constants.WHEEL_CIRCUMFERENCE) /  Constants.GEAR_RATIO) ;
    }
    public double getDistanceMovedRight(){
        return Math.abs(((rightEncoder.getPosition() - startPositionRight) * Constants.WHEEL_CIRCUMFERENCE) / Constants.GEAR_RATIO) ;
    }
    public double getDistanceFix(){
        return Math.abs(((fix1.getPosition() - startPositionRight) * Constants.WHEEL_CIRCUMFERENCE) / Constants.GEAR_RATIO) ;
    }
    

    public void resetEncoders(){
        startPositionLeft = leftEncoder.getPosition();
        startPositionRight = rightEncoder.getPosition();
    }
}