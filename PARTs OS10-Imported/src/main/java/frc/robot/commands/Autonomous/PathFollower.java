// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Function;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants.Encoder;
import frc.robot.Sensors.Encoders;
import frc.robot.subsystems.DriveSparkMax;

public class PathFollower extends CommandBase {
  Trajectory trajectory;
  DriveSparkMax drive = DriveSparkMax.getInstance();
  RamseteCommand ramseteCommand;
  Path path;
  Encoders encoders = Encoders.getInstance();

  /** Creates a new PathFollower. */
  public PathFollower() {
    // Use addRequirements() here to declare subsystem dependencies.
    try {
      trajectory = TrajectoryUtil.fromPathweaverJson(path);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } // TODO set path
    
  }
  public Command getPathFollowerCom(){
    return ramseteCommand =
     new RamseteCommand(trajectory, 
      drive::getPose,
      new RamseteController(drive.getkRamseteB(), drive.getkRamseteZeta()), 
      new SimpleMotorFeedforward(drive.getKsVolts(),
          drive.getKvVoltSecondsPerMeter(),
          drive.getKaVoltSecondsSquaredPerMeter()
        ), 
        drive.getkDriveKinematics(), 
      drive::getWheelSpeeds,
      new PIDController(drive.getKPDrive(),0,0),
      new PIDController(drive.getKPDrive(), 0, 0),

      drive::moveVolts,
      drive
    );
      
  }
 
}
