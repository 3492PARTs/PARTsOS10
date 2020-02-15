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

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  private static Climber _staticClimber = new Climber();
  private final TalonSRX climb;
  private final TalonSRX elevator;
  private final TalonSRX elevatorPivot;
    int peakAmps = 25;
    int amps = 45;
    int timeoutMs = 0;
    double speed = .25;
  public Climber() {
  climb = new TalonSRX(6);
  elevator = new TalonSRX(11);
  elevatorPivot = new TalonSRX(5);


    climb.configPeakCurrentDuration(100, 10); 
    climb.configContinuousCurrentLimit(amps, timeoutMs); 
    climb.configPeakCurrentLimit(peakAmps, timeoutMs);
    climb.enableCurrentLimit(true); 


    elevator.configPeakCurrentDuration(100, 10); 
    elevator.configContinuousCurrentLimit(amps, timeoutMs); 
    elevator.configPeakCurrentLimit(peakAmps, timeoutMs);
    elevator.enableCurrentLimit(true);


    elevatorPivot.configPeakCurrentDuration(100, 10); 
    elevatorPivot.configContinuousCurrentLimit(amps, timeoutMs); 
    elevatorPivot.configPeakCurrentLimit(peakAmps, timeoutMs);
    elevatorPivot.enableCurrentLimit(true); 
  }

  public void pivotToggleState(Direction dir){
    if(dir == Direction.forward){
      elevatorPivot.set(ControlMode.PercentOutput, speed);
  } 
   else if(dir == Direction.reverse){
    elevatorPivot.set(ControlMode.PercentOutput, -speed);
  }
   else elevatorPivot.set(ControlMode.PercentOutput, 0);
  }

  public static Climber getInstance(){
    return _staticClimber;
  }


  public int pivotEncoder(){
    return elevatorPivot.getSelectedSensorPosition();
  }

  public void climbToggleState(Direction dir){
    if(dir == Direction.forward){
      climb.set(ControlMode.PercentOutput, speed);
  } 
   else if(dir == Direction.reverse){
       climb.set(ControlMode.PercentOutput, -speed);
  }
   else climb.set(ControlMode.PercentOutput, 0);
  }

  public void elevatorToggleState(Direction dir){
    if(dir == Direction.forward){
      elevator.set(ControlMode.PercentOutput, speed);
   } 
   else if(dir == Direction.reverse){
       elevator.set(ControlMode.PercentOutput, -speed);
   }
   else elevator.set(ControlMode.PercentOutput, 0);
  }


  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}