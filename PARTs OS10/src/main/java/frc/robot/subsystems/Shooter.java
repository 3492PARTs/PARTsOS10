/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.time.StopWatch;
import edu.wpi.first.wpilibj.Timer;

import static frc.robot.Constants.Direction;


public class Shooter extends PIDSubsystem {
  /**
   * Creates a new Shooter.
   */
  private final TalonSRX shooterRight = new TalonSRX(12);
  private final TalonSRX shooterLeft = new TalonSRX(3);
  private final Encoder rShooterEnc = new Encoder(0,1);
  private final Encoder lShooterEnc = new Encoder(2,3);
  
  
 
  public Shooter() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));

    
  

  } 
  double setSpeed;
  double expected;
  
  double current;
  double wheelc = 6*Math.PI; // IN INCHES
  StopWatch timer  = new StopWatch();
  double integral = 0.0;
  double acceptableDeviation = .01; 
  double kp = 0.0;//TODO: set constant
  double ki = 0.0;//TODO: set constant
  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here    
    timer.start();
    // an attempt at PID 
    double time = timer.getDuration();
    current = (rShooterEnc.get() + lShooterEnc.get())/2;
    expected = wheelc * time;
    double error = current - expected;
    integral = integral + error * time;
    output = kp*integral*error*ki;
    if(output > acceptableDeviation ){
      if(output > 0){
        shooterRight.set(ControlMode.PercentOutput, 1.0);
        shooterLeft.set(ControlMode.PercentOutput, 1.0);
      }
    
    }
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }

  public void toggleState(Direction dir){
    if(dir == Direction.forward){
      shooterRight.set(ControlMode.PercentOutput, 1);
      shooterLeft.set(ControlMode.PercentOutput, 1);
   } 
   else{
    shooterRight.set(ControlMode.PercentOutput,0);
    shooterLeft.set(ControlMode.PercentOutput, 0);
    
   }
  }
}
