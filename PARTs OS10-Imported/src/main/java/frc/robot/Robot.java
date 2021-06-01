/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.Direction;
import frc.robot.Constants.Encoder;
import frc.robot.commands.*;
import frc.robot.commands.Autonomous.PathFollower;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private RobotContainer m_robotContainer;
  private Command m_autonomousCommand;
  String trajectoryJSON = "paths/TestPathWeaver.wpilib.json";
  Trajectory trajectory = new Trajectory();


  private HttpCamera limelightFeed;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our autonomous chooser on the dashboard.
    limelightFeed = new HttpCamera("limelight", "http://limelight.local:5800/stream.mjpg");
    // driverShuffleboardTab.add("LL",
    // limelightFeed).withPosition(0,0).withSize(15,8).withProperties(Map.of("Show
    // Crosshair", true, "Show Controls", false));
    // CameraServer.getInstance().startAutomaticCapture();
    m_robotContainer = new RobotContainer();
    m_robotContainer.smartDashBoard.robotInitUpdate();
    m_robotContainer.gyro.calibrate();

    try{
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    m_robotContainer.smartDashBoard.robotPeriodicUpdate();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    new Reset().schedule();
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    m_autonomousCommand = new PathFollower().getPathFollowerCom(trajectory);

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    m_robotContainer.smartDashBoard.autoPeriodicUpdate();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    m_robotContainer.encoders.resetEncoders(Encoder.elevator);
    m_robotContainer.encoders.resetEncoders(Encoder.pivotElevator);
    m_robotContainer.encoders.resetEncoders(Encoder.armPivot);
  }

  
  private boolean toggleIntake = false, intakeDir = true;

  /**
   * This function is called periodically during operator control.
   */

  @Override
  public void teleopPeriodic() {
    final double Joystick1y = (m_robotContainer.rightJoystick.getY());
    final double Joystick2y = (m_robotContainer.leftJoystick.getY());

    m_robotContainer.smartDashBoard.teleopPeriodicUpdate();
    m_robotContainer.PESensor.runPESensor(); // Keeps the PE Sensor Counting

    // =====================================================================================
    // AUTO FIRE, If the shooter speed is good, fire the ball
    // =====================================================================================
    if (m_robotContainer.launchPad.getRawButton(1)) {
      Constants.autoShoot = true;
    } else {
      Constants.autoShoot = false;
    }
    if ((m_robotContainer.shooter.getShooterStatusLeft() || m_robotContainer.shooter.getShooterStatusRight())
        && Constants.autoShoot && m_robotContainer.launchPad.getRawButton(10)) {
      // Constants.AutoFireReverseLock = false;
      new ConveyerSpaceCom(1.35).schedule();
    }

    if ((m_robotContainer.shooter.getShooterStatusLeftLow() || m_robotContainer.shooter.getShooterStatusRightLow())
        && Constants.autoShoot && m_robotContainer.launchPad.getRawButton(11)) {
      // Constants.AutoFireReverseLock = false;
      new ConveyerSpaceCom(1.35).schedule();
    }
    // =====================================================================================
    // UNTESTED -- Back the ball up if its touching the top wheels
    // =====================================================================================
    // if (Constants.autoShoot && m_robotContainer.PESensor.getPhotoEyeShoot().get()
    // && !Constants.AutoFireReverseLock) {
    // new ConveyerSpaceCom(-.2);
    // }

    // =====================================================================================
    // DRIVE
    // =====================================================================================
    // Drive inversion
    if (m_robotContainer.leftJoystick.getRawButton(3)) {
      m_robotContainer.drive.switchFront(false);
    }
    // Drive inversion
    if (m_robotContainer.leftJoystick.getRawButton(4)) {
      m_robotContainer.drive.switchFront(true);
    }

    if (m_robotContainer.rightJoystick.getRawButton(3)) {
      m_robotContainer.intake.pivotToggleState(Constants.Direction.reverse); // arm pivot down
    } else if (m_robotContainer.rightJoystick.getRawButton(4)) {
      m_robotContainer.intake.pivotToggleState(Constants.Direction.forward); //arm pivot up
    } else {
      m_robotContainer.intake.pivotToggleState(Constants.Direction.off);
    }

    // Drive Multiplier
    if (m_robotContainer.drive.getMult() > 0) {
      m_robotContainer.drive.move(Joystick1y, Joystick2y);
    } else if (m_robotContainer.drive.getMult() < 0) {
      m_robotContainer.drive.move(Joystick1y, Joystick2y);
    }

    // =====================================================================================
    // INTAKE
    // =====================================================================================
    //System.out.println("' robit - oimtskr '" + Constants.intakeToggle);
    if (toggleIntake && m_robotContainer.rightJoystick.getRawButton(1)) {  //Constants.intakeToggle
      toggleIntake = false;
      if(intakeDir){
        intakeDir = false;
        m_robotContainer.intake.wheelToggleState(Direction.reverse); // intake in
      }
      else {
        intakeDir = true;        
        m_robotContainer.intake.wheelToggleState(Direction.off); // intake off
      }
    } 
    else if (!m_robotContainer.rightJoystick.getRawButton(1)) {
      toggleIntake = true;
    }

    //intskeDir = true means direction is off and it can be intake out based off line 204
    if (intakeDir && (m_robotContainer.leftJoystick.getRawButton(7) || m_robotContainer.rightJoystick.getRawButton(7))) {
      m_robotContainer.intake.wheelToggleState(Direction.forward);// intake out
    }
    else if (intakeDir && toggleIntake) {
      m_robotContainer.intake.wheelToggleState(Direction.off); // intake off
    }
    // Intake Arm Pivot
    // if (m_robotContainer.leftJoystick.getRawButton(11) ||
    // m_robotContainer.rightJoystick.getRawButton(11)) {
    // m_robotContainer.intake.pivotToggleState(Constants.Direction.forward); // arm
    // pivot up manual
    // } else if (m_robotContainer.leftJoystick.getRawButton(5) ||
    // m_robotContainer.rightJoystick.getRawButton(5)) {
    // m_robotContainer.intake.pivotToggleState(Constants.Direction.reverse); // arm
    // pivot down manual
    // } else {
    // m_robotContainer.intake.pivotToggleState(Constants.Direction.off);
    // }
    // Intake Arm Pivot -- WHY IS THIS NOT IN THE ABOVE CODE AS AN OR???ß

    // =====================================================================================
    // CONVEYOR
    // =====================================================================================
    if (m_robotContainer.launchPad.getRawButton(6)) {
      m_robotContainer.conveyor.toggleState(Constants.Direction.reverse); // conveyor in
    } else if (m_robotContainer.launchPad.getRawButton(7)) {
      m_robotContainer.conveyor.toggleState(Constants.Direction.forward); // conveyor out
    } else {
      m_robotContainer.conveyor.toggleState(Constants.Direction.off);
    }

    // =====================================================================================
    // SHOOTER
    // =====================================================================================
    if (m_robotContainer.launchPad.getRawButton(10)) { // shooter out
      m_robotContainer.shooter.toggleState(Constants.Direction.reverse, 1);
    } else if (m_robotContainer.launchPad.getRawButton(11)) { // shooter in
      m_robotContainer.shooter.toggleState(Constants.Direction.reverse, .8);
    } else {
      m_robotContainer.shooter.toggleState(Constants.Direction.off);
    }

    // =====================================================================================
    // CLIMBER
    // =====================================================================================
    if (m_robotContainer.launchPad.getRawButton(3)) { // winch (climber up)
      m_robotContainer.climber.climbToggleState(Constants.Direction.reverse);
    } else {
      m_robotContainer.climber.climbToggleState(Constants.Direction.off);
    }

    if (m_robotContainer.launchPad.getRawButton(9)) { // elevator up
      m_robotContainer.climber.elevatorToggleState(Constants.Direction.forward);
    } else if (m_robotContainer.launchPad.getRawButton(8)) { // elevator down
      m_robotContainer.climber.elevatorToggleState(Constants.Direction.reverse);
    } else {
      m_robotContainer.climber.elevatorToggleState(Constants.Direction.off);
    }
    // DOES THIS NOT NEED A STOP??????
    if (m_robotContainer.leftJoystick.getRawButton(9) || m_robotContainer.rightJoystick.getRawButton(9)) { // pivot back
      m_robotContainer.climber.pivotToggleState(Constants.Direction.reverse);
    } else
      m_robotContainer.climber.pivotToggleState(Direction.off);

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
