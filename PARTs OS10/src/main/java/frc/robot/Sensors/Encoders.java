/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import frc.robot.Constants;
import frc.robot.Constants.*;
import com.revrobotics.CANEncoder;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveSparkMax;
import frc.robot.subsystems.Intake;


public class Encoders {
    private final DriveSparkMax drive = DriveSparkMax.getInstance();
    private final Climber climber = Climber.getInstance();
    private final Intake intake = Intake.getInstance();
    private CANEncoder rightEncoder = drive.Right1.getEncoder();
    private CANEncoder leftEncoder = drive.Left1.getEncoder();
        //=====================================================================================
    // Define Singleton Pattern
    //=====================================================================================
    private static Encoders _staticEncoders = new Encoders();
    public static Encoders getInstance()
    {
        return _staticEncoders;
    }
    private static double startPositionRight = 0;
    private static double startPositionLeft = 0;
    private static double startPosPivotElevator = 0;
    private static double startPosElevator = 0;
    private static double startPosArmPivot = 0;

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
        return (((double)climber.elevatorPivot.getSelectedSensorPosition()) - startPosPivotElevator)/4096.0;
    }

    public double getElevatorEncoderRot(){
        return (((double)climber.elevator.getSelectedSensorPosition()) - startPosElevator)/4096.0;
    }

    public double getArmPivotEncoderRot(){
        return (((double)intake.pivotIntake.getSelectedSensorPosition()) - startPosArmPivot)/4096.0;
    }


    public void resetEncoders(Encoder encoder){
        if(encoder == Encoder.drive){
            startPositionLeft = leftEncoder.getPosition();
            startPositionRight = rightEncoder.getPosition();
        }
        else if(encoder == Encoder.pivotElevator){
            startPosPivotElevator = (double)climber.elevatorPivot.getSelectedSensorPosition();
        }
        else if(encoder == Encoder.elevator){
            startPosElevator = (double)climber.elevator.getSelectedSensorPosition();
        }
        else if(encoder == Encoder.armPivot){
            startPosArmPivot = (double)intake.pivotIntake.getSelectedSensorPosition();
        }
        else if(encoder == Encoder.all){
            startPositionLeft = leftEncoder.getPosition();
            startPositionRight = rightEncoder.getPosition();
            startPosPivotElevator = (double)climber.elevatorPivot.getSelectedSensorPosition();
            startPosElevator = (double)climber.elevator.getSelectedSensorPosition();
            startPosArmPivot = (double)intake.pivotIntake.getSelectedSensorPosition();
        }
    }
}