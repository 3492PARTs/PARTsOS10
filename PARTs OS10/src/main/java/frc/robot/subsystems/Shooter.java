/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Direction;
import com.ctre.phoenix.*;
import edu.wpi.first.wpilibj.*;


public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private static final TalonSRX shooterRight = new TalonSRX(12);
  private static final TalonSRX shooterLeft = new TalonSRX(3);
  private static Shooter _staticShooter = new Shooter();
  // private static final Encoder rShooterEnc = new Encoder(0,1);
  // private static final Encoder lShooterEnc = new Encoder(2,3);
  int amps = 40;
  int timeoutMs= 0;
  public Shooter() {
    shooterLeft.configPeakCurrentDuration(100, 10); 
    shooterLeft.configContinuousCurrentLimit(55, timeoutMs);
    shooterLeft.configPeakCurrentLimit(amps, timeoutMs);
    shooterLeft.enableCurrentLimit(true);

    shooterRight.configPeakCurrentDuration(100, 10); 
    shooterRight.configContinuousCurrentLimit(55, timeoutMs);
    shooterRight.configPeakCurrentLimit(amps, timeoutMs);
    shooterRight.enableCurrentLimit(true);
  }
  double speed = .1;
  int counter = 0;
  public void toggleState(Direction dir){
    System.out.println("shoot");//TODO: test ramp up tweak values if it works
    if(dir == Direction.forward){
      System.out.println("fwd");
      for(;counter<90; counter++){
        speed += .01;
        // end test rampup
        shooterRight.set(ControlMode.PercentOutput, speed);
        shooterLeft.set(ControlMode.PercentOutput, speed);
      }
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
  public static Shooter getInstance(){
    return _staticShooter;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
