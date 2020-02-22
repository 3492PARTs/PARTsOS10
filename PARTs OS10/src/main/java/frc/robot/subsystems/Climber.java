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

  private final TalonSRX climb1;
  private final TalonSRX climb2;
  private final TalonSRX elevator;
  public final TalonSRX elevatorPivot;
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
    climb1 = new TalonSRX(7);
    climb2 = new TalonSRX(11);
    elevator = new TalonSRX(6);
    elevatorPivot = new TalonSRX(5);


    climb1.configPeakCurrentDuration(100, 10); 
    climb1.configContinuousCurrentLimit(amps, timeoutMs); 
    climb1.configPeakCurrentLimit(peakAmps, timeoutMs);
    climb1.enableCurrentLimit(true); 

    climb2.configPeakCurrentDuration(100, 10); 
    climb2.configContinuousCurrentLimit(amps, timeoutMs); 
    climb2.configPeakCurrentLimit(peakAmps, timeoutMs);
    climb2.enableCurrentLimit(true); 

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




  public double pivotEncoder(){
    return ((double)elevatorPivot.getSelectedSensorPosition())/4096.0;
  }

  public void climbToggleState(Direction dir){
    if(dir == Direction.forward){
      climb1.set(ControlMode.PercentOutput, speed);
      climb2.set(ControlMode.PercentOutput, speed);
  } 
   else if(dir == Direction.reverse){
       climb1.set(ControlMode.PercentOutput, -speed);
       climb2.set(ControlMode.PercentOutput, -speed);
  }
   else 
   {
     climb1.set(ControlMode.PercentOutput, 0);
     climb2.set(ControlMode.PercentOutput, 0);
   }
  }

  public void elevatorToggleState(Direction dir){
    if(dir == Direction.forward){
      elevator.set(ControlMode.PercentOutput, -1);
   } 
   else if(dir == Direction.reverse){
       elevator.set(ControlMode.PercentOutput, 1);
   }
   else elevator.set(ControlMode.PercentOutput, 0);
  }


  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}