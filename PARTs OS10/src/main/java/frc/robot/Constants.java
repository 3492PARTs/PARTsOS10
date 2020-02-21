/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final double GEAR_RATIO = 8.01;
    public static final double WHEEL_CIRCUMFERENCE = 6 * Math.PI;
    public static final double  VOLTS_TO_DIST = 5.0;
    public static final double DRIVE_SPEED = .4;
    public static int mult; // used to switch orientation of drive
    public static boolean driveOrientation; // true = shooter in front, false = intake is front
    public static String driveFront;
    public static final String SD_AUTO_DELAY =  "autoDelay";
    public static final double PHOTO_EYE_RANGE = 0; //TODO: set value

    //drivetrain
    public static final int Right1_port = 13;
    public static final int Right2_port = 14;
    public static final int Right3_port = 15;
    public static final int Left1_port = 2;
    public static final int Left2_port = 1;
    public static final int Left3_port = 8;

    public static final int Conveyor_port = 4;
    

public static enum Direction {
    forward, reverse, off
}

public static enum ShootSpeed {
    full, half, quarter
}

}
