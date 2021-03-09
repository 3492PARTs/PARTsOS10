/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Direction;
import frc.robot.Constants.Encoder;
import frc.robot.Sensors.Encoders;
import frc.robot.subsystems.Climber;

public class ClimbCom extends CommandBase {
  /**
   * Creates a new ClimbCom.
   */
  private Encoders encoders;
  private double distance = 0; // TODO: what distance needs to traverse
  private Climber climber;

  public ClimbCom() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    encoders = Encoders.getInstance();
    climber = Climber.getInstance();
    encoders.resetEncoders(Encoder.elevator);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climber.elevatorToggleState(Direction.forward);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.elevatorToggleState(Direction.reverse);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(encoders.getElevatorEncoderRot()) >= 7.345703;
  }
}
