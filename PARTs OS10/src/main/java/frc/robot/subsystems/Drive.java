/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends SubsystemBase {
  /**
   * Creates a new Drive.
   */
public int Right1_port = 13;
public int Right2_port = 14;
public int Right3_port = 15;
public WPI_TalonSRX Right1 = new WPI_TalonSRX(Right1_port);
public WPI_TalonSRX Right2 = new WPI_TalonSRX(Right2_port);
public WPI_TalonSRX Right3 = new WPI_TalonSRX(Right3_port);

public int Left1_port = 2;
public int Left2_port = 1;
public int Left3_port = 0;
public WPI_TalonSRX Left1 = new WPI_TalonSRX(Left1_port);
public WPI_TalonSRX Left2 = new WPI_TalonSRX(Left2_port);
public WPI_TalonSRX Left3 = new WPI_TalonSRX(Left3_port);

public SpeedControllerGroup Right = new SpeedControllerGroup(Right1, Right2, Right3);
public SpeedControllerGroup Left = new SpeedControllerGroup(Left1, Left2, Left3);
public DifferentialDrive M_drive = new DifferentialDrive(Left, Right);
// making the test motors
 TalonSRX lefttest_1 = new TalonSRX(Left1_port);
 TalonSRX lefttest_2 = new TalonSRX(Left2_port);
 TalonSRX lefttest_3 = new TalonSRX(Left3_port);
 TalonSRX righttest_1 = new TalonSRX(Right1_port);
 TalonSRX righttest_2 = new TalonSRX(Right2_port);
  TalonSRX righttest_3 = new TalonSRX(Right3_port);
  Double[] LeftValues_1;
  Double[] LeftValues_2;
  Double[] LeftValues_3;
  Double[] RightValues_1;
  Double[] RightValues_2;
  Double[] RightValues_3;

  int amps = 55;
  int timeoutMs = 0;
  int peakAmps = 40;

  public Drive() {
//current limiting for drive
  lefttest_1.configPeakCurrentDuration(100, 10); 
  lefttest_1.configContinuousCurrentLimit(amps, timeoutMs); 
  lefttest_1.configPeakCurrentLimit(peakAmps, timeoutMs);
  lefttest_1.enableCurrentLimit(true); 
  
  lefttest_2.configPeakCurrentDuration(100, 10); 
  lefttest_2.configContinuousCurrentLimit(amps, timeoutMs);
  lefttest_2.configPeakCurrentLimit(peakAmps, timeoutMs);
  lefttest_2.enableCurrentLimit(true); 
  
  lefttest_3.configPeakCurrentDuration(100, 10); 
  lefttest_3.configContinuousCurrentLimit(amps, timeoutMs);
  lefttest_3.configPeakCurrentLimit(peakAmps, timeoutMs);
  lefttest_3.enableCurrentLimit(true); 

  righttest_1.configPeakCurrentDuration(100, 10); 
  righttest_1.configContinuousCurrentLimit(amps, timeoutMs);
  righttest_1.configPeakCurrentLimit(peakAmps, timeoutMs);
  righttest_1.enableCurrentLimit(true);

  righttest_2.configPeakCurrentDuration(100, 10); 
  righttest_2.configContinuousCurrentLimit(amps, timeoutMs);
  righttest_2.configPeakCurrentLimit(peakAmps, timeoutMs);
  righttest_2.enableCurrentLimit(true);


  righttest_3.configPeakCurrentDuration(100, 10); 
  righttest_3.configContinuousCurrentLimit(amps, timeoutMs);
  righttest_3.configPeakCurrentLimit(peakAmps, timeoutMs);
  righttest_3.enableCurrentLimit(true); 

  }
/**
 * makes the robit move
 * @param Speed left between 1, -1
 * @param Speed2 right drive train between 1,-1
 */
public void move(Double Speed1,Double Speed2){
  M_drive.tankDrive(Speed1, Speed2);
  SmartDashboard.putNumber("The Voltage of Left 1 is", lefttest_1.getBusVoltage());
  SmartDashboard.putNumber("The Voltage of Left 2 is", lefttest_2.getBusVoltage());
  SmartDashboard.putNumber("The Voltage of Left 3 is", lefttest_2.getBusVoltage());
  SmartDashboard.putNumber("The Voltage of Right 1 is", righttest_1.getBusVoltage());
  SmartDashboard.putNumber("The Voltage of Right 2 is", righttest_2.getBusVoltage());
  SmartDashboard.putNumber("The Voltage of Right 3 is", righttest_3.getBusVoltage());

}

public void testmove(Double speed1, Double speed2) throws InterruptedException {
  M_drive.tankDrive(speed1, speed1);
  LeftValues_1[0] = lefttest_1.getBusVoltage();
  LeftValues_2[0] = lefttest_2.getBusVoltage();
  LeftValues_3[0] = lefttest_3.getBusVoltage();
  RightValues_1[0] = righttest_1.getBusVoltage();
  RightValues_2[0]= righttest_2.getBusVoltage();
  RightValues_3[0] = righttest_3.getBusVoltage();
  Thread.sleep(20000,0);
  LeftValues_1[1] = lefttest_1.getBusVoltage();
  LeftValues_2[1] = lefttest_2.getBusVoltage();
  LeftValues_3[1] = lefttest_3.getBusVoltage();
  RightValues_1[1] = righttest_1.getBusVoltage();
  RightValues_2[1]= righttest_2.getBusVoltage();
  RightValues_3[1] = righttest_3.getBusVoltage();
  M_drive.tankDrive(speed2, speed2);
  LeftValues_1[2] = lefttest_1.getBusVoltage();
  LeftValues_2[2] = lefttest_2.getBusVoltage();
  LeftValues_3[2] = lefttest_3.getBusVoltage();
  RightValues_1[2] = righttest_1.getBusVoltage();
  RightValues_2[2]= righttest_2.getBusVoltage();
  RightValues_3[2] = righttest_3.getBusVoltage();
  System.out.println("Left one values:" + Arrays.toString(LeftValues_1));
  System.out.println("Left Two values:" + Arrays.toString(LeftValues_2));
  System.out.println("Left Three values:" + Arrays.toString(LeftValues_3));
  System.out.println("Right one values:" + Arrays.toString(RightValues_1));
  System.out.println("Right Two values:" + Arrays.toString(RightValues_2));
  System.out.println("Right Three values:" + Arrays.toString(RightValues_3));
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}