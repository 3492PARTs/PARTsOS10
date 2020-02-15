/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Direction;
import frc.robot.subsystems.Conveyor;


public class ConveyerCom extends CommandBase {
  /**
   * Creates a new ConveyerCom.
   */
  long time;
  long currentTime;
  long duration;
  Conveyor conveyer = Conveyor.getInstance();
  public ConveyerCom(long duration) {
  this.duration = duration;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  time = System.currentTimeMillis();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  currentTime = System.currentTimeMillis();
  conveyer.toggleState(Direction.forward);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyer.toggleState(Direction.off);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return time - currentTime > duration;
  }
}
