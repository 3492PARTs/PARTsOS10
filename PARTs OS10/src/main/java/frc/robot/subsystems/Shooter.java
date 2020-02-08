/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Direction;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private static final TalonSRX shooterRight = new TalonSRX(12);
  private static final TalonSRX shooterLeft = new TalonSRX(3);
  private static final Encoder rShooterEnc = new Encoder(0,1);
  private static final Encoder lShooterEnc = new Encoder(2,3);
  public Shooter() {

  }

  public void toggleState(Direction dir){
    System.out.println("shoot");
    if(dir == Direction.forward){
      System.out.println("fwd");
      shooterRight.set(ControlMode.PercentOutput, -1);
      shooterLeft.set(ControlMode.PercentOutput, -1);
   } 
   else if(dir == Direction.reverse){
    System.out.println("bak");
    shooterRight.set(ControlMode.PercentOutput, 1);
    shooterLeft.set(ControlMode.PercentOutput, 1);
 } 
   else{
    System.out.println("off");
    shooterRight.set(ControlMode.PercentOutput,0);
    shooterLeft.set(ControlMode.PercentOutput, 0);
    
   }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
