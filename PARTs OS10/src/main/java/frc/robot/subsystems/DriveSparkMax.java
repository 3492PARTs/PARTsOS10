/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

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
private RobotContainer m_robotContainer;
private final Drive drive = Drive.getInstance();

public  CANSparkMax Right1 = new CANSparkMax(Constants.Right1_port, MotorType.kBrushless);
public  CANSparkMax Right2 = new CANSparkMax(Constants.Right2_port, MotorType.kBrushless);
public  CANSparkMax Right3 = new CANSparkMax(Constants.Right3_port, MotorType.kBrushless);

public  CANSparkMax Left1 = new CANSparkMax(Constants.Left1_port, MotorType.kBrushless);
public  CANSparkMax Left2 = new CANSparkMax(Constants.Left2_port, MotorType.kBrushless);
public  CANSparkMax Left3 = new CANSparkMax(Constants.Left3_port, MotorType.kBrushless);

public  SpeedControllerGroup Right = new SpeedControllerGroup(Right1, Right2, Right3);
public  SpeedControllerGroup Left = new SpeedControllerGroup(Left1, Left2, Left3);
public DifferentialDrive M_drive = new DifferentialDrive(Left, Right);

  int amps = 55;
  int timeoutMs = 0;
  int peakAmps = 40;

//singleton
private static DriveSparkMax _staticDriveSparkMax = new DriveSparkMax();
public static DriveSparkMax getInstance(){
  return _staticDriveSparkMax;
}

  public DriveSparkMax() {
//current limiting for drive
  }
/**
 * makes the robit move
 * @param Speed left between 1, -1
 * @param Speed2 right drive train between 1,-1
 */

public void move(Double Speed1,Double Speed2){
  System.out.println("drive");
  System.out.println(M_drive);
  M_drive.tankDrive(Speed1, Speed2);
}

public void stop(){
  M_drive.tankDrive(0,0);
}


//joystick limiter
double limitedJS1 = 0;
double limitedJS2 = 0;
/**
 * 
 * @param joyY joystick left y axis
 * @param JoyX joystick right y axis
 */
public void moveLimited(Double joyY, Double JoyX){
  double limit = .02;
  double change = joyY -limitedJS1;
  if(change>limit){
    change = limit;
  } else if (change <= limit){
    change = - limit;
  } 
  limitedJS1 += change;

  change = joyY - limitedJS2;
  if (change>limit) change = limit;
  else if( change<=limit) change = -limit;
  limitedJS2 += change;
  M_drive.tankDrive(limitedJS1, limitedJS2);
}



/**
 * 
 * @param orientation true = shooter is front, false = intake is front
 */
public void switchFront(boolean orientation)
{
  if(orientation)
  {
    Constants.mult = 1;
    Constants.driveFront = "Shoot";
  }
  else
  {
    Constants.mult = -1;
    Constants.driveFront = "INTAKE";
  }
}



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}