/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;


public class Delay extends CommandBase {
  /**
   * Creates a new Delay.
   */
  long start_Time;
  String keyString = "Autonomous Shooting Delay Choice";
  int delayTime;
  double choosenDelay;
  public Delay() {
    // Use addRequirements() here to declare subsystem dependencie

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   start_Time = System.currentTimeMillis();
  choosenDelay = 1000*(SmartDashboard.getNumber(Constants.SD_AUTO_DELAY, 0.0));
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
    return System.currentTimeMillis() - start_Time >= delayTime;
  }
}