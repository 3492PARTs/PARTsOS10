/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.time.StopWatch;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Shoot extends CommandBase {
  /**
   * Creates a new Shoot.
   */
  private double distance;
  private int degrees;
  private int shootTime = 10000;
  private StopWatch timer = new StopWatch();
  /**
   * 
   * @param distance distance from goal
   * @param degrees the degrees from target
   */
  public Shoot(double distance, int degrees) {
    this.distance = distance;
    this.degrees = degrees;
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
