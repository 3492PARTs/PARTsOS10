/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.Direction;
import frc.robot.Constants.Encoder;
import frc.robot.Sensors.Encoders;
import frc.robot.subsystems.Intake;

public class ArmPivotCom extends CommandBase {
  /**
   * Creates a new IntakePivotCom.
   */
  private final Intake intake = Intake.getInstance();
  private final Encoders encoders = Encoders.getInstance();
  private Direction dir;
  public ArmPivotCom(Direction dir) {
    this.dir = dir;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    encoders.resetEncoders(Constants.Encoder.armPivot);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(dir == Direction.forward)
    {
    intake.pivotToggleState(Direction.forward);
    }
    else if(dir == Direction.reverse)
    {
      intake.pivotToggleState(Direction.reverse);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    encoders.resetEncoders(Constants.Encoder.armPivot);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(encoders.getArmPivotEncoderRot()) >= 54.2; 
  }
}
