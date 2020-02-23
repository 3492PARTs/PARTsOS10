/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import frc.robot.Constants;
import frc.robot.Constants.StartPos;

import com.revrobotics.CANEncoder;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveSparkMax;


public class Encoders {
    private final DriveSparkMax drive = DriveSparkMax.getInstance();
    private final Climber climber = Climber.getInstance();
    private CANEncoder rightEncoder = drive.Right1.getEncoder();
    private CANEncoder leftEncoder = drive.Left1.getEncoder();
    
    private static Encoders _staticEncoders = new Encoders();
    public static Encoders getInstance()
    {
        return _staticEncoders;
    }
    private static double startPositionRight = 0;
    private static double startPositionLeft = 0;

    public double getDistanceMovedLeft(){
        return Math.abs(((leftEncoder.getPosition() - startPositionLeft) * Constants.WHEEL_CIRCUMFERENCE) /  Constants.GEAR_RATIO) ;
    }
    public double getDistanceMovedRight(){
        return Math.abs(((rightEncoder.getPosition() - startPositionRight) * Constants.WHEEL_CIRCUMFERENCE) / Constants.GEAR_RATIO) ;
    }
    
    /**
     * 
     * @return in rotations
     */
    public double getPivotElevatorEncoderRot(){
        return ((double)climber.elevatorPivot.getSelectedSensorPosition())/4096.0;
    }

    public double getElevatorEncoderRot(){
        return ((double)climber.elevator.getSelectedSensorPosition())/4096.0;
    }

    public double getArmPivotEncoderRot(){
        return ((double)climber.elevator.getSelectedSensorPosition())/4096.0;
    }

    public void startPosEncoders(StartPos pos){

    }

    public void resetDriveEncoders(){
        startPositionLeft = leftEncoder.getPosition();
        startPositionRight = rightEncoder.getPosition();
    }
}