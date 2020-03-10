/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Direction;
import frc.robot.Sensors.LimeLight;

public class LimeLightAllignHorizontalCom extends CommandBase {
  /**
   * Creates a new LimeLightAllignCom.
   */

   Double HorizontalOffset;
   LimeLight limelight = LimeLight.getInstance();
   double distancePerDegree = 0.0;
   Direction dir;
  public LimeLightAllignHorizontalCom() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    HorizontalOffset = limelight.getHorizontalOffset();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(HorizontalOffset < 0){
      new Turn90(.1,270);
    }
    if(HorizontalOffset > 0){
      new Turn90(.1, 90);
    }
    new DriveCom(HorizontalOffset*distancePerDegree, Direction.forward);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
