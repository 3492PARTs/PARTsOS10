/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.Direction;
import frc.robot.subsystems.Climber;

public class Climber_Command extends CommandBase {
  /**
   * Creates a new Climber_Command.
   */
  double rotations = 0;
  int encodervalue = 0;
  Climber climber = Climber.getInstance();
  private static Climber_Command _staticClimber_Command = new Climber_Command();
  public Climber_Command() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
  public static Climber_Command getInstance(){
    return _staticClimber_Command;
  }

  // Called every time the scheduler runs while the command is scheduled.
  
  @Override
  public void execute() {
  rotations = climber.pivotEncoder()/4096;
  climber.toggleState(Direction.forward);
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.toggleState(Direction.off);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return rotations>=.275;
  }
}