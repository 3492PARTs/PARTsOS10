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
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class DriveSparkMax extends SubsystemBase {
  /**
   * Creates a new Drive.
   */
private RobotContainer m_robotContainer;
private final Drive drive = Drive.getInstance();
public  int Right1_port = 13;
public  int Right2_port = 14;
public  int Right3_port = 15;
public  CANSparkMax Right1 = new CANSparkMax(Right1_port, MotorType.kBrushless);
public  CANSparkMax Right2 = new CANSparkMax(Right2_port, MotorType.kBrushless);
public  CANSparkMax Right3 = new CANSparkMax(Right3_port, MotorType.kBrushless);

public  int Left1_port = 2;
public  int Left2_port = 1;
public  int Left3_port = 8;
public  CANSparkMax Left1 = new CANSparkMax(Left1_port, MotorType.kBrushless);
public  CANSparkMax Left2 = new CANSparkMax(Left2_port, MotorType.kBrushless);
public  CANSparkMax Left3 = new CANSparkMax(Left3_port, MotorType.kBrushless);

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



public void stop(){
  M_drive.tankDrive(0,0);
}




public void move(Double Speed1,Double Speed2){
  System.out.println("drive");
  System.out.println(M_drive);
  M_drive.tankDrive(Speed1, Speed2);
}

public void switchFront(boolean orientation)
{
  if(orientation)
  {
    Constants.mult = 1;
  }
  else
  {
    Constants.mult = -1;
  }
}



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}