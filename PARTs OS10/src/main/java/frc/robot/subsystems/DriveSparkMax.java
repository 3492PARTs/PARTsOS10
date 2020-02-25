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
public static int mult = 1;
public static String driveFront = "";
//=====================================================================================
// Define Singleton Pattern
//=====================================================================================
private static DriveSparkMax _staticDriveSparkMax = new DriveSparkMax();
public static DriveSparkMax getInstance(){
  return _staticDriveSparkMax;
}

  public DriveSparkMax() {
    
  }
/**
 * makes the robit move
 * @param Speed left between 1, -1
 * @param Speed2 right drive train between 1,-1
 */
public void move(Double Speed1,Double Speed2){
  M_drive.tankDrive(Speed1, Speed2);
}




//joystick limiter
double limitedJS1 = 0;
double limitedJS2 = 0;
/**
 * 
 * @param joyY joystick left y axis
 * @param JoyX joystick right y axis
 */
public void moveLimited(Double joy1, Double joy2){
  double limit = .02;
  double change = joy1 -limitedJS1;
  if(change>limit){
    change = limit;
  } else if (change <= limit){
    change = - limit;
  } 
  limitedJS1 += change;

  change = joy2 - limitedJS2;
  if (change>limit) change = limit;
  else if( change<=limit) change = -limit;
  limitedJS2 += change;
    M_drive.tankDrive((mult) * limitedJS1, (mult)* limitedJS2);
}



/**
 * 
 * @param orientation true = shooter is front, false = intake is front
 */
public void switchFront(boolean orientation)
{
  if(orientation)
  {
    mult = 1;
    driveFront = "Shoot";
  }
  else
  {
    mult = -1;
    driveFront = "INTAKE";
  }
}
// stops all motors
public void stop(){
  move(0.0, 0.0);

}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}