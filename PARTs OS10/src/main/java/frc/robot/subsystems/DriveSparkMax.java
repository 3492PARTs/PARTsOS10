/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSparkMax extends SubsystemBase {

  /**
   * Creates a new Drive.
   */
  private CANSparkMax Right1;
  private CANSparkMax Right2;
  private CANSparkMax Right3;

  private CANSparkMax Left1;
  private CANSparkMax Left2;
  private CANSparkMax Left3;

  private SpeedControllerGroup Right;
  private SpeedControllerGroup Left;
  private DifferentialDrive M_drive;





  private int amps = 40;
  private int timeoutMs = 0;
  private int peakAmps = 55;
  private int mult = 1;
  private String driveFront = "";

  // =====================================================================================
  // Define Singleton Pattern
  // =====================================================================================
  private static DriveSparkMax _staticDriveSparkMax = new DriveSparkMax();

  public static DriveSparkMax getInstance() {
    return _staticDriveSparkMax;
  }


  /**
   * @param Right1
   * @param Right2
   * @param Right3
   * @param Left1
   * @param Left2
   * @param Left3
   */
  public DriveSparkMax(CANSparkMax Right1, CANSparkMax Right2, CANSparkMax Right3, CANSparkMax Left1, CANSparkMax Left2, CANSparkMax Left3){

    this.Right1 = Right1;
    this.Right2 = Right2;
    this.Right3 = Right3;

    this.Left1 = Left1;
    this.Left2 = Left2;
    this.Left3 = Left3;

    Right = new SpeedControllerGroup(Right1, Right2, Right3);
    Left = new SpeedControllerGroup(Left1, Left2, Left3);
    M_drive = new DifferentialDrive(Left, Right);

  }


  /**
   * @see This is a constructor for physical hardware, as in not for unit testing
   */
  public DriveSparkMax() {

    Right1 = new CANSparkMax(Constants.Right1_port, MotorType.kBrushless);
    Right2 = new CANSparkMax(Constants.Right2_port, MotorType.kBrushless);
    Right3 = new CANSparkMax(Constants.Right3_port, MotorType.kBrushless);

    Left1 = new CANSparkMax(Constants.Left1_port, MotorType.kBrushless);
    Left2 = new CANSparkMax(Constants.Left2_port, MotorType.kBrushless);
    Left3 = new CANSparkMax(Constants.Left3_port, MotorType.kBrushless);

    Right = new SpeedControllerGroup(Right1, Right2, Right3);
    Left = new SpeedControllerGroup(Left1, Left2, Left3);
    M_drive = new DifferentialDrive(Left, Right);

  }

  /**
   * Drive
   * 
   * @param Speed1  left between 1, -1
   * @param Speed2 right drive train between 1,-1
   */
  public void move(Double Speed1, Double Speed2) {
    M_drive.tankDrive(Speed1, Speed2);

  }

  // joystick limiter
  private double limitedJS1 = 0;
  private double limitedJS2 = 0;

  /**
   * Drive with speed ramp
   * 
   * @param joy1 joystick left y axis
   * @param joy2 joystick right y axis
   */
  public void moveLimited(Double joy1, Double joy2) {
    double limit = .02;
    double change = joy1 - limitedJS1;
    if (change > limit) {
      change = limit;
    } else if (change <= limit) {
      change = -limit;
    }
    limitedJS1 += change;

    change = joy2 - limitedJS2;
    if (change > limit)
      change = limit;
    else if (change <= limit)
      change = -limit;
    limitedJS2 += change;
    M_drive.tankDrive((mult) * limitedJS1, (mult) * limitedJS2);
  }

  /**
   * Toggle which side is the front of the robot
   * 
   * @param orientation true = shooter is front, false = intake is front
   */
  public void switchFront(boolean orientation) {
    if (orientation) {
      mult = 1;
      driveFront = "Shoot";
    } else {
      mult = -1;
      driveFront = "INTAKE";
    }
  }

  /**
   * Get the multiplier variable
   * 
   * @return mult
   */
  public int getMult() {
    return mult;
  }

  /**
   * Get which side is the front of the robot
   * 
   * @return driveFront
   */
  public String getDriveFront() {
    return driveFront;
  }

  /**
   * Get the Right1 spark max
   * 
   * @return Right1
   */
  public CANSparkMax getRight1SparkMax() {
    return Right1;
  }

  /**
   * Get the Left1 spark max
   * 
   * @return Left1
   */
  public CANSparkMax getLeft1SparkMax() {
    return Left1;
  }

  /**
   * Stop the motors
   */
  public void stop() {
    move(0.0, 0.0);

  }

  /**
   *
   * @return The speed of the motors in percent output
   */
  public double[] getSpeeds(){
    double[] speeds = {Right.get(), Left.get()};
    return speeds;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}