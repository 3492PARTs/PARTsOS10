/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Direction;
import frc.robot.subsystems.Conveyor;

public class ConveyerSpaceCom extends CommandBase {
  /**
   * Creates a new ConveyerSpaceCom.
   */
  long time;
  double duration;
  Conveyor conveyor = Conveyor.getInstance();
  /**
   * 
   * @param time in seconds
   */
  public ConveyerSpaceCom(double duration) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.duration = duration*1000;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = System.currentTimeMillis();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    conveyor.toggleState(Direction.forward);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyor.toggleState(Direction.off);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  return duration <= System.currentTimeMillis()-time;
  }
}
