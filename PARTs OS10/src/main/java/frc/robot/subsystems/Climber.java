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

  private final TalonSRX climb;
  private final TalonSRX Elevator;
  private final TalonSRX climbPivot;
    int peakAmps = 25;
    int amps = 45;
    int timeoutMs = 0;
    double speed = .25;  
//Singleton  
private static Climber _staticClimber = new Climber();
    public static Climber getInstance(){
    return _staticClimber;
  }
  public Climber() {
  climb = new TalonSRX(6);
  Elevator = new TalonSRX(11);
  climbPivot = new TalonSRX(5);


    climb.configPeakCurrentDuration(100, 10); 
    climb.configContinuousCurrentLimit(amps, timeoutMs); 
    climb.configPeakCurrentLimit(peakAmps, timeoutMs);
    climb.enableCurrentLimit(true); 


    Elevator.configPeakCurrentDuration(100, 10); 
    Elevator.configContinuousCurrentLimit(amps, timeoutMs); 
    Elevator.configPeakCurrentLimit(peakAmps, timeoutMs);
    Elevator.enableCurrentLimit(true);


    climbPivot.configPeakCurrentDuration(100, 10); 
    climbPivot.configContinuousCurrentLimit(amps, timeoutMs); 
    climbPivot.configPeakCurrentLimit(peakAmps, timeoutMs);
    climbPivot.enableCurrentLimit(true); 
  }

  public void pivotToggleState(Direction dir){
    if(dir == Direction.forward){
      climbPivot.set(ControlMode.PercentOutput, speed);
  } 
   else if(dir == Direction.reverse){
       climbPivot.set(ControlMode.PercentOutput, -speed);
  }
   else climbPivot.set(ControlMode.PercentOutput, 0);
  }




  public int pivotEncoder(){
    return climbPivot.getSelectedSensorPosition();
  }

  public void toggleState(Direction dir){
    if(dir == Direction.forward){
      climb.set(ControlMode.PercentOutput, speed);
  } 
   else if(dir == Direction.reverse){
       climb.set(ControlMode.PercentOutput, -speed);
  }
   else climb.set(ControlMode.PercentOutput, 0);
  }

  public void climbToggleState(Direction dir){
    if(dir == Direction.forward){
      Elevator.set(ControlMode.PercentOutput, speed);
   } 
   else if(dir == Direction.reverse){
       Elevator.set(ControlMode.PercentOutput, -speed);
   }
   else Elevator.set(ControlMode.PercentOutput, 0);
  }


  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}