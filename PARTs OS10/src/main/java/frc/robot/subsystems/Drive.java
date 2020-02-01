/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive extends SubsystemBase {
  /**
   * Creates a new Drive.
   */
public static int Right1_port = 13;
public static int Right2_port = 14;
public static  int Right3_port = 15;
public static WPI_TalonSRX Right1 = new WPI_TalonSRX(Right1_port);
public static WPI_TalonSRX Right2 = new WPI_TalonSRX(Right2_port);
public static WPI_TalonSRX Right3 = new WPI_TalonSRX(Right3_port);

public static  int Left1_port = 2;
public static  int Left2_port = 1;
public static int Left3_port = 0;
public static WPI_TalonSRX Left1 = new WPI_TalonSRX(Left1_port);
public static WPI_TalonSRX Left2 = new WPI_TalonSRX(Left2_port);
public static WPI_TalonSRX Left3 = new WPI_TalonSRX(Left3_port);

public static SpeedControllerGroup Right = new SpeedControllerGroup(Right1, Right2, Right3);
public static SpeedControllerGroup Left = new SpeedControllerGroup(Left1, Left2, Left3);
public static DifferentialDrive M_drive = new DifferentialDrive(Left, Right);
  public Drive() {
  }
/**
 * makes the robit move
 * @param Speed left between 1, -1
 * @param Speed2 right drive train between -1,1
 */
public static void move(Double Speed1,Double Speed2){
  M_drive.tankDrive(Speed1, Speed2);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
