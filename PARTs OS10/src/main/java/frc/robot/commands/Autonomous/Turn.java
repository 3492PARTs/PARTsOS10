/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Sensors.Gyro;
import frc.robot.subsystems.DriveSparkMax;

public class Turn extends CommandBase {
  /**
   * Creates a new Turn.
   */
  Gyro gyro = Gyro.getInstance();
  private double degrees;
  private double objDegrees;
  private final double Speed = .1; //TODO: test for safe turning speed
  private DriveSparkMax drive = DriveSparkMax.getInstance();
/**
 * 
 * @param objDegrees the degrees it should be
 */
  public Turn( double objDegrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.objDegrees = objDegrees;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //TODO: reset encoders
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    degrees = gyro.gyro.getAngle();
    if(degrees < 180){
      drive.move(Speed, -Speed);
    }
    if(degrees > 180){
      drive.move(-Speed, Speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(degrees < 180){
      return degrees <= objDegrees;
    }
    if(degrees > 180){
      return degrees >= objDegrees;
    }
    return false;
  }
}