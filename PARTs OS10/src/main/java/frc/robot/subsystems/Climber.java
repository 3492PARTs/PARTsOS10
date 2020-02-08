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
  private final TalonSRX climbUp;
  private final TalonSRX climbDown;
  private final TalonSRX climbPivot;

  public Climber() {
    climbUp = new TalonSRX(6);
    climbDown = new TalonSRX(11);
    climbPivot = new TalonSRX(5);
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
      climbDown.set(ControlMode.PercentOutput, 1);
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
