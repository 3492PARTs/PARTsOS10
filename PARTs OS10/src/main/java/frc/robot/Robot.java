/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotContainer;
import static frc.robot.Constants.Direction;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private final Conveyor conveyor = new Conveyor();
  private final Shooter shooter = new Shooter();
  private final Drive drive = new Drive();
  private final Intake intake = new Intake();
  private final Climber climber = new Climber();

  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }


  
  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

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
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {  
    double Joystick1y = m_robotContainer.rightJoystick.getY();
    double Joystick2y = m_robotContainer.leftJoystick.getY();
    drive.move(Joystick1y, Joystick2y);

    if(m_robotContainer.rightJoystick.getRawButton(3) || m_robotContainer.leftJoystick.getRawButton(3))
    {
      intake.wheelToggleState(Constants.Direction.forward);
    }
   

    //elevator pivot up
    if(m_robotContainer.launchPad.getRawButton(1))
    {
      climber.pivotToggleState(Constants.Direction.forward);
    }
    else
    {
      climber.pivotToggleState(Constants.Direction.off);
    }

    //elevator up
    if(m_robotContainer.launchPad.getRawButton(2))
    {
      climber.toggleState(Constants.Direction.forward);
    }
    else
    {
      climber.toggleState(Constants.Direction.off);
    }

    //climber up
    if(m_robotContainer.launchPad.getRawButton(3))
    {
      climber.climbToggleState(Constants.Direction.forward);
    }
    else
    {
      climber.climbToggleState(Constants.Direction.off);
    }
    
    //elevator pivot down
    if(m_robotContainer.launchPad.getRawButton(4))
    {
      climber.pivotToggleState(Constants.Direction.reverse);
    }
    else
    {
      climber.pivotToggleState(Constants.Direction.off);
    }

    //elevator down
    if(m_robotContainer.launchPad.getRawButton(5))
    {
      climber.toggleState(Constants.Direction.reverse);
    }
    else
    {
      climber.toggleState(Constants.Direction.off);
    }


    //conveyor in
    if(m_robotContainer.launchPad.getRawButton(7))
    {
      conveyor.toggleState(Constants.Direction.reverse);
    }
    else
    {
      conveyor.toggleState(Constants.Direction.off);
    }
    //conveyor out
    if(m_robotContainer.launchPad.getRawButton(9))
    {
      conveyor.toggleState(Constants.Direction.forward);
    }
    else
    {
      conveyor.toggleState(Constants.Direction.off);
    }


    //shooter out
    if(m_robotContainer.launchPad.getRawButton(8))
    {
      shooter.toggleState(Constants.Direction.reverse);
    }
    else
    {
      shooter.toggleState(Constants.Direction.off);
    }
    //shooter in
    if(m_robotContainer.launchPad.getRawButton(10))
    {
      shooter.toggleState(Constants.Direction.forward);
    }
    else
    {
      shooter.toggleState(Constants.Direction.off);
    }





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
