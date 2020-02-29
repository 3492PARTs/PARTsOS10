/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    // =====================================================================================
    // VALUES
    // =====================================================================================
    public static final double GEAR_RATIO = 8.01; // 24/50
    public static final double WHEEL_CIRCUMFERENCE = 6 * Math.PI;
    public static final double VOLTS_TO_DIST = 5.0;
    public static final double DRIVE_SPEED = .4;

    // =====================================================================================
    // PORT NUMBERS
    // =====================================================================================
    // shooter motor ports
    public static final int SHOOTER_RIGHT_PORT = 12;
    public static final int SHOOTER_LEFT_PORT = 3;
    // drivetrain
    public static final int Left1_port = 2;
    public static final int Left2_port = 1;
    public static final int Left3_port = 8;
    public static final int Right1_port = 13;
    public static final int Right2_port = 14;
    public static final int Right3_port = 15;
    // conveyor
    public static final int Conveyor_port = 4;
    // intake
    public static final int INTAKE_WHEEL_PORT = 10;
    public static final int PIVOT_INTAKE_PORT = 9;
    // climber motor ports
    public static final int CLIMB_1_PORT = 7;
    public static final int CLIMB_2_PORT = 11;
    public static final int ELEVATOR_PORT = 6;
    public static final int ELEVATOR_PIVOT_PORT = 5;

    // =====================================================================================
    // Mutable -- Used in multiple files
    // =====================================================================================
    public static int autoFireCounter = 0;
    public static boolean autoFireLock = false;

    public static boolean autoShoot = false;
    public static boolean intakeToggle = false;

    // public static boolean AutoFireReverseLock = false;

    // =====================================================================================
    // Smart Dashboard Strings
    // =====================================================================================
    public static final String SD_AUTO_DELAY = "Auto Delay";
    public static final String SD_DRIVE_ORIENTATION = "Drive Orientation";
    public static final String SD_LEFT_SHOOTER_SPEED = "Left Shooter Speed";
    public static final String SD_RIGHT_SHOOTER_SPEED = "Right Shooter Speed";
    public static final String SD_SHOOTER_SPEED = "Shooter Speed";
    public static final String SD_AUTO_SHOOT = "Auto Shoot";
    public static final String SD_PROXIMITY_DIST = "Proximity Distance";
    public static final String SD_ENCODER_DIST = "Encoder Distance";
    public static final String SD_PIVOT_EL_ENCODER = "Pivot EL Encoder";
    public static final String SD_PE_SHOOTER = "PE Shooter";
    public static final String SD_PE_INTAKE = "PE Intake";
    public static final String SD_CLIMB_ENCODER = "Climb encoder";
    public static final String SD_BALL_CNT = "Ball Count";
    public static final String SD_ARM_PIVOT_COUNTER = "ArmPivot";
    public static final String SD_LEFT_SHOOTER_SPEED_LOW = "Left Shooter Speed Low";
    public static final String SD_RIGHT_SHOOTER_SPEED_LOW = "Right Shooter Speed Low";

    // =====================================================================================
    // ENUMS
    // =====================================================================================
    public static enum Direction {
        forward, reverse, off
    }

    public static enum ShootSpeed {
        full, half, quarter, eighth
    }

    public static enum Encoder {
        drive, pivotElevator, elevator, armPivot, all
    }

    public static enum Motor {
        drive, climber, elevator, elevatorPivot, intakeWheel, intakePivot, conveyor, shooter
    }

}
