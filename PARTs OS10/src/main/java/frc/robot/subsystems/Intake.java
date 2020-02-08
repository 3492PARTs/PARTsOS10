/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Direction;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private final double forwardHalt = 0.0; //TODO: set value
  private final double reverseHalt = 0.0;
  private final TalonSRX wheelIntake;
  private final TalonSRX pivotIntake;
  int amps = 25;
  int timeoutMs = 0;

  public Intake() {
  
  
    wheelIntake = new TalonSRX(10);
    pivotIntake = new TalonSRX(9);
    wheelIntake.configPeakCurrentDuration(100, 10); 
    wheelIntake.configContinuousCurrentLimit(55, timeoutMs);
    wheelIntake.configPeakCurrentLimit(amps, timeoutMs);
    wheelIntake.enableCurrentLimit(true);
    
    wheelIntake.configPeakCurrentDuration(100, 10); 
    wheelIntake.configContinuousCurrentLimit(55, timeoutMs);
    wheelIntake.configPeakCurrentLimit(amps, timeoutMs);
    wheelIntake.enableCurrentLimit(true);
  }

  public void wheelToggleState(final Direction dir) {
    if (dir == Direction.forward) {
      wheelIntake.set(ControlMode.PercentOutput, .5);
    } else if (dir == Direction.reverse) {
      wheelIntake.set(ControlMode.PercentOutput, -.5);
    } else {
      wheelIntake.set(ControlMode.PercentOutput, 0);
    }
  }

  public void pivotToggleState(final Direction dir){
    if(dir == Direction.forward){
      while(pivotIntake.getSelectedSensorPosition() < forwardHalt){
      pivotIntake.set(ControlMode.PercentOutput,.3);
      }
      pivotIntake.set(ControlMode.PercentOutput, 0);
    }
    else if(dir == Direction.reverse){
      while(pivotIntake.getSelectedSensorPosition() < reverseHalt){
        pivotIntake.set(ControlMode.PercentOutput, -.3);
      }
      pivotIntake.set(ControlMode.PercentOutput, 0);
    }
    else{
      pivotIntake.set(ControlMode.PercentOutput, 0);
    }
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
