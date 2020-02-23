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
import frc.robot.Constants;
import frc.robot.Constants.Direction;


public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

  public final TalonSRX shooterRight = new TalonSRX(Constants.SHOOTER_RIGHT_PORT);
  public static final TalonSRX shooterLeft = new TalonSRX(Constants.SHOOTER_LEFT_PORT);
  private static Shooter _staticShooter = new Shooter();
  // private static final Encoder rShooterEnc = new Encoder(0,1);
  // private static final Encoder lShooterEnc = new Encoder(2,3);
  int amps = 40;
  int timeoutMs= 0;  
  
    //=====================================================================================
    // Define Singleton Pattern
    //=====================================================================================
  public static Shooter getInstance(){
    return _staticShooter;
  }
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
    if(dir == Direction.forward){
      for(;counter<900; counter++){
        speed += .001;
        shooterRight.set(ControlMode.PercentOutput, speed);
        shooterLeft.set(ControlMode.PercentOutput, speed);
      }
   } 
   else if(dir == Direction.reverse){
    shooterRight.set(ControlMode.PercentOutput, 1);
    shooterLeft.set(ControlMode.PercentOutput, 1);
 } 
   else{
    shooterRight.set(ControlMode.PercentOutput,0);
    shooterLeft.set(ControlMode.PercentOutput, 0);
    
   }
  }
  /**
   * 
   * @param dir output direction
   * @param adjust multiplies ramp up rate by decimal to decrease speed
   */

  public void toggleState(Direction dir, double adjust){
    
    if(dir == Direction.forward){
      for(;counter<(90 * adjust); counter++){
        speed += .01;
       // end test rampup
        shooterRight.set(ControlMode.PercentOutput, speed);
        shooterLeft.set(ControlMode.PercentOutput, speed);
      }
   } 
   else if(dir == Direction.reverse){
    shooterRight.set(ControlMode.PercentOutput, 1 * adjust);
    shooterLeft.set(ControlMode.PercentOutput, 1 * adjust);
 } 
   else{
    shooterRight.set(ControlMode.PercentOutput,0);
    shooterLeft.set(ControlMode.PercentOutput, 0);
    
   }
  }

  public double getRightRPM() {
    return 600.0 * (double)shooterRight.getSelectedSensorVelocity() / 4096.0;
  }

  public double getLeftRPM() {
    return 600.0 * (double)shooterLeft.getSelectedSensorVelocity() / 4096.0;
  }

  public void stop() {
    toggleState(Direction.off);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}