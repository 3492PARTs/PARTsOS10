/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.time.StopWatch;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  /**
   * Creates a new Shoot.
   */
  private double distance;
  private int shootTime = 10000;//TODO: set values
  Shooter shooter = Shooter.getInstance();
  private StopWatch timer = new StopWatch();
  /**
   * 
   * @param distance distance robot should move
   */
  public Shoot(double distance, int time) {
    this.distance = distance;
    shootTime = time;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if((distance > 10))
    {
      shooter.toggleState(Constants.Direction.forward);
    }
    else
    {
      //shooter.toggleState(Constants.Direction.forward, (distance * 0.1));
      shooter.toggleState(Constants.Direction.forward, .5);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.getDurationMs() > shootTime;
  }
}
