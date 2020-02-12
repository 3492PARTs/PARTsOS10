/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class Turn extends CommandBase {
  /**
   * Creates a new Turn.
   */
  private double degrees;
  private double objDegrees;
  private final double Speed = .1; //TODO: test for safe turning speed
  private Drive drive = Drive.getInstance();

  public Turn(double degrees, double objDegrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.degrees = degrees;
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
    if(degrees > 0){
      drive.move(Speed, -Speed);
    }
    if(degrees < 0){
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
    if(degrees < 0){
      return degrees <= objDegrees;
    }
    if(degrees > 0){
      return degrees >= objDegrees;
    }
    return false;
  }
}
