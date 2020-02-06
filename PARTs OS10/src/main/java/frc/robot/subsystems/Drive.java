/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive extends SubsystemBase {
  /**
   * Creates a new Drive.
   */
public static int Right1_port = 13;
public static int Right2_port = 14;
public static int Right3_port = 15;
public static WPI_TalonSRX Right1 = new WPI_TalonSRX(Right1_port);
public static WPI_TalonSRX Right2 = new WPI_TalonSRX(Right2_port);
public static WPI_TalonSRX Right3 = new WPI_TalonSRX(Right3_port);

public static int Left1_port = 2;
public static int Left2_port = 1;
public static int Left3_port = 0;
public static WPI_TalonSRX Left1 = new WPI_TalonSRX(Left1_port);
public static WPI_TalonSRX Left2 = new WPI_TalonSRX(Left2_port);
public static WPI_TalonSRX Left3 = new WPI_TalonSRX(Left3_port);

public static SpeedControllerGroup Right = new SpeedControllerGroup(Right1, Right2, Right3);
public static SpeedControllerGroup Left = new SpeedControllerGroup(Left1, Left2, Left3);
public static DifferentialDrive M_drive = new DifferentialDrive(Left, Right);
// making the test motors
public static TalonSRX lefttest_1 = new TalonSRX(Left1_port);
public static TalonSRX lefttest_2 = new TalonSRX(Left2_port);
public static TalonSRX lefttest_3 = new TalonSRX(Left3_port);
public static TalonSRX righttest_1 = new TalonSRX(Right1_port);
public static TalonSRX righttest_2 = new TalonSRX(Right2_port);
public static TalonSRX righttest_3 = new TalonSRX(Right3_port);
public static Double[] LeftValues_1;
public static Double[] LeftValues_2;
public static Double[] LeftValues_3;
public static Double[] RightValues_1;
public static Double[] RightValues_2;
public static Double[] RightValues_3;

  public Drive() {
  }
/**
 * makes the robit move
 * @param Speed left between 1, -1
 * @param Speed2 right drive train between 1,-1
 */
public static void move(Double Speed1,Double Speed2){
  M_drive.tankDrive(Speed1, Speed2);
}

public static void testmove(Double speed1, Double speed2) throws InterruptedException {
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
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
