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
  private final TalonSRX climbUp;
  private final TalonSRX climbDown;
  private final TalonSRX climbPivot;
    int peakAmps = 25;
    int amps = 45;
    int timeoutMs = 0;
  public Climber() {
    climbUp = new TalonSRX(6);
    climbDown = new TalonSRX(11);
    climbPivot = new TalonSRX(5);
    climbUp.configPeakCurrentDuration(100, 10); 
    climbUp.configContinuousCurrentLimit(amps, timeoutMs); 
    climbUp.configPeakCurrentLimit(peakAmps, timeoutMs);
    climbUp.enableCurrentLimit(true); 


    climbDown.configPeakCurrentDuration(100, 10); 
    climbDown.configContinuousCurrentLimit(amps, timeoutMs); 
    climbDown.configPeakCurrentLimit(peakAmps, timeoutMs);
    climbDown.enableCurrentLimit(true);


    climbPivot.configPeakCurrentDuration(100, 10); 
    climbPivot.configContinuousCurrentLimit(amps, timeoutMs); 
    climbPivot.configPeakCurrentLimit(peakAmps, timeoutMs);
    climbPivot.enableCurrentLimit(true); 
  }

  public void pivotToggleState(Direction dir){
    if(dir == Direction.forward){
      climbPivot.set(ControlMode.PercentOutput, 1);
   } 
   else if(dir == Direction.reverse){
       climbPivot.set(ControlMode.PercentOutput, -1);
   }
   else climbPivot.set(ControlMode.PercentOutput, 0);
  }

  public static Climber getInstance(){
    return _staticClimber;

  }


  public int pivotEncoder(){
    return climbPivot.getSelectedSensorPosition();
  }

  public void toggleState(Direction dir){
    if(dir == Direction.forward){
      climbUp.set(ControlMode.PercentOutput, 1);
   } 
   else if(dir == Direction.reverse){
       climbUp.set(ControlMode.PercentOutput, -1);
   }
   else climbUp.set(ControlMode.PercentOutput, 0);
  }

  public void climbToggleState(Direction dir){
    if(dir == Direction.forward){
      climbDown.set(ControlMode.PercentOutput, .3);
   } 
   else if(dir == Direction.reverse){
       climbDown.set(ControlMode.PercentOutput, -1);
   }
   else climbDown.set(ControlMode.PercentOutput, 0);
  }


  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
