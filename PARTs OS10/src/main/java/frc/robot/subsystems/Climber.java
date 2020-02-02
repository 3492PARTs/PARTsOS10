/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Direction;

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

  public void downToggleState(Direction dir){
    if(dir == Direction.forward){

    }
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
