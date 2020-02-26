/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Direction;
import frc.robot.subsystems.Climber;
import frc.robot.Sensors.Encoders;
import frc.robot.Sensors.Gyro;

public class Pivot_Command extends CommandBase {
  /**
   * Creates a new Climber_Command.
   */
  private Climber climber = Climber.getInstance();
  private Gyro gyro = Gyro.getInstance();
  private Encoders encoders = Encoders.getInstance();

  public Pivot_Command() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.

  @Override
  public void execute() {
    climber.pivotToggleState(Direction.forward);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.pivotToggleState(Direction.off);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return encoders.getPivotElevatorEncoderRot() >= .251;
  }
}
