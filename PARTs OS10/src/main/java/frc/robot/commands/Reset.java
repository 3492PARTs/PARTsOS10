/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Sensors.*;
import frc.robot.*;

public class Reset extends CommandBase {
  /**
   * Creates a new SensorReset.
   */
  public Reset() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  private Encoders encoders;
  private Gyro gyro;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    encoders = Encoders.getInstance();
    gyro = Gyro.getInstance();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    encoders.resetEncoders(Constants.Encoder.all);
    gyro.zeroGyro();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
