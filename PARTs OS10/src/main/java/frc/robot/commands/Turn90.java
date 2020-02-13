/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Sensors.Gyro;
import frc.robot.subsystems.Drive;

public class Turn90 extends CommandBase {
  /**
   * Creates a new Turn90.
   */
  double degrees;
  final double rotationTarget = 90.0;
  double speed;
  Gyro gyro = Gyro.getInstance();
  Drive drive = Drive.getInstance();
  public Turn90(double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    degrees = gyro.gyro.getAngle();    
    if(rotationTarget < 180){
      drive.move(speed, -speed);
    }
    else{
      drive.move(-speed, speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  drive.move(.0, .0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(degrees > 180){
      return (rotationTarget - 180) <= degrees; 
    }
    if(degrees < 180){
      return (rotationTarget) <= degrees; 
    }
    return false;
    }

}