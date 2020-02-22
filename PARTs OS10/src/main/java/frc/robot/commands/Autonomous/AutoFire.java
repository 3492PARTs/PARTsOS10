/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShootSpeed;
import frc.robot.commands.ConveyerSpaceCom;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;
import frc.robot.Robot;

public class AutoFire extends CommandBase {
  /**
   * Creates a new AutoFire.
   */
  private Shooter shooter;
  public AutoFire() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter = Shooter.getInstance();
    Constants.autoFireCounter = 0;
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.toggleState(Constants.Direction.forward);
    if((Robot.shooterStatusRight || Robot.shooterStatusLeft) && !Constants.autoFireLock)
    {
      new ConveyerSpaceCom(1.5).schedule();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.toggleState(Constants.Direction.off);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Constants.autoFireCounter > 3;
  }
}
