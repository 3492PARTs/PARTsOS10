/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.Direction;
import frc.robot.Sensors.Encoders;
import frc.robot.Sensors.Gyro;
import frc.robot.subsystems.DriveSparkMax;

public class DriveCom extends CommandBase {
  /**
   * Creates a new Drive.
   */
  private double distance;
  private Encoders encoders;
  private DriveSparkMax drive;
  private Direction dir;
  private Gyro gyro;

  /**
   * 
   * @param distance the distance it should move in inches
   * @param dir      the direction it should move
   */

  public DriveCom(double distance, Direction dir) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.distance = distance;
    this.dir = dir;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    encoders = Encoders.getInstance();
    drive = DriveSparkMax.getInstance();
    gyro = Gyro.getInstance();

    encoders.resetEncoders(Constants.Encoder.drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (dir == Direction.forward) {
      drive.move(-Constants.DRIVE_SPEED, -Constants.DRIVE_SPEED);
    }
    if (dir == Direction.reverse) {
      drive.move(Constants.DRIVE_SPEED, Constants.DRIVE_SPEED);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.move(.0, .0);
    gyro.zeroGyro();
    encoders.resetEncoders(Constants.Encoder.drive);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return distance <= encoders.getDistanceMovedRight();
  }
}