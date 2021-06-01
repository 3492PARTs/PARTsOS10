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
    private CANEncoder rightEncoder;
    private CANEncoder leftEncoder;

    private static double startPositionRight = 0;
    private static double startPositionLeft = 0;
    private static double startPosPivotElevator = 0;
    private static double startPosElevator = 0;
    private static double startPosArmPivot = 0;
    // =====================================================================================
    // Define Singleton Pattern
    // =====================================================================================
    private static Encoders _staticEncoders = new Encoders();

    public static Encoders getInstance() {
        
        return _staticEncoders;
    }

    public Encoders(){
        rightEncoder = drive.getRight1SparkMax().getEncoder();
        leftEncoder = drive.getLeft1SparkMax().getEncoder();
    }

    /**
     * Distance of the left drive wheel
     * 
     * @return distance the left wheel moved in in ?
     */
    public double getDistanceMovedLeft() {
        return Math.abs(((leftEncoder.getPosition() - startPositionLeft) * Constants.WHEEL_CIRCUMFERENCE)
                / Constants.GEAR_RATIO);
    }

    /**
     * Distance of the right drive wheel
     * 
     * @return distance the right wheel moved in in ?
     */
    public double getDistanceMovedRight() {
        return Math.abs(((rightEncoder.getPosition() - startPositionRight) * Constants.WHEEL_CIRCUMFERENCE)
                / Constants.GEAR_RATIO);
    }

    /**
     * Get the rotation of the elevator pivot
     * 
     * @return in rotations
     */
    public double getPivotElevatorEncoderRot() {
        return (((double) climber.getElevatorPivot().getSelectedSensorPosition()) - startPosPivotElevator) / 4096.0;
    }

    /**
     * Get the rotation of the elevator
     * 
     * @return in rotations
     */
    public double getElevatorEncoderRot() {
        return (((double) climber.getElevator().getSelectedSensorPosition()) - startPosElevator) / 4096.0;
    }

    /**
     * Get the rotation of the arm pivot
     * 
     * @return in rotations
     */
    public double getArmPivotEncoderRot() {
        return (((double) intake.getPivotIntakeTalon().getSelectedSensorPosition()) - startPosArmPivot) / 4096.0;
    }

    public double[] getRate(Encoder encoder) {
        if (encoder == Encoder.drive) {
            return new double[] { rightEncoder.getVelocity(), leftEncoder.getVelocity() };
        }
        
        else return new double[]{0, 0};



    }


    /**
     * Reset an encoder
     * 
     * @param encoder the encoder to reset
     */
    public void resetEncoders(Encoder encoder) {
        if (encoder == Encoder.drive) {
            startPositionLeft = leftEncoder.getPosition();
            startPositionRight = rightEncoder.getPosition();
        } else if (encoder == Encoder.pivotElevator) {
            startPosPivotElevator = (double) climber.getElevatorPivot().getSelectedSensorPosition();
        } else if (encoder == Encoder.elevator) {
            startPosElevator = (double) climber.getElevator().getSelectedSensorPosition();
        } else if (encoder == Encoder.armPivot) {
            startPosArmPivot = (double) intake.getPivotIntakeTalon().getSelectedSensorPosition();
        } else if (encoder == Encoder.all) {
            startPositionLeft = leftEncoder.getPosition();
            startPositionRight = rightEncoder.getPosition();
            startPosPivotElevator = (double) climber.getElevatorPivot().getSelectedSensorPosition();
            startPosElevator = (double) climber.getElevator().getSelectedSensorPosition();
            startPosArmPivot = (double) intake.getPivotIntakeTalon().getSelectedSensorPosition();
        }
    }
}