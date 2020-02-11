/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Climber_Command;
import frc.robot.commands.DriveForwardOnly;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final Command m_autonomousCommand = new DriveForwardOnly();
  private RobotContainer m_robotContainer;
  private final Conveyor conveyor = RobotContainer.conveyor;
  private final Shooter shooter =  RobotContainer.shooter;
  private final Drive drive = RobotContainer.drive;
  private final Intake intake = RobotContainer.intake;
  //private final Climber climber = new Climber();
  Climber climber = RobotContainer.climber;
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  Climber_Command pivotCommand = new Climber_Command();
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  
    // m_robotContainer.pivoter.whenPressed(new Climber_Command());
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
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    
    //schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
      System.out.println(m_autonomousCommand!= null);
      
    
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

  double limitedJS1 = 0;
  double limitedJS2 = 0;

  @Override
  public void teleopPeriodic() {

      /*
    double Joystick1y = -(m_robotContainer.rightJoystick.getY());
    double Joystick2y = -(m_robotContainer.leftJoystick.getY());
    */

    
    //elevator pivot up
    double Joystick1y = -(m_robotContainer.rightJoystick.getY());
    double limit = .012;
    double change = Joystick1y - limitedJS1;
    if (change>limit) change = limit;
    else if( change<=limit) change = -limit;
    limitedJS1 += change;

    double Joystick2y = -(m_robotContainer.leftJoystick.getY());
    change = Joystick2y - limitedJS2;
    if (change>limit) change = limit;
    else if( change<=limit) change = -limit;
    limitedJS2 += change;

    drive.move(limitedJS1, limitedJS2);

    // if(m_robotContainer.launchPad.getRawButton(9))
    // {
    //   climber.pivotToggleState(Constants.Direction.forward);
    // }
    // else
    // {
    //   climber.pivotToggleState(Constants.Direction.off);
    // }
    
    if(m_robotContainer.launchPad.getRawButton(9))
    {
      pivotCommand.execute();
    }


    //elevator up
  
    if(m_robotContainer.launchPad.getRawButton(8))
    {
      climber.toggleState(Constants.Direction.forward);
    }
    else
    {
      climber.toggleState(Constants.Direction.off);
    }

    //climber up
    if(m_robotContainer.launchPad.getRawButton(5))
    {
      climber.climbToggleState(Constants.Direction.forward);
    }
    else
    {
      climber.climbToggleState(Constants.Direction.off);
    }

    //elevator pivot down
    if(m_robotContainer.launchPad.getRawButton(7))
    {
      climber.pivotToggleState(Constants.Direction.reverse);
    }
    else
    {
      climber.pivotToggleState(Constants.Direction.reverse);
    }

    //elevator down
    if(m_robotContainer.launchPad.getRawButton(6))
    {
      climber.toggleState(Constants.Direction.reverse);
    }
    else
    {
      climber.toggleState(Constants.Direction.off);
    }

    // conveyor in
    //conveyor out
    if(m_robotContainer.launchPad.getRawButton(1))
    {
      conveyor.toggleState(Constants.Direction.forward);
    }
    else if(m_robotContainer.launchPad.getRawButton(3))
    {
      conveyor.toggleState(Constants.Direction.reverse);
    }
    else
    {
      conveyor.toggleState(Constants.Direction.off);
    }

    //shooter out
    //shooter in
    if(m_robotContainer.launchPad.getRawButton(10))
    {
      System.out.println("btn 10");
      shooter.toggleState(Constants.Direction.reverse);
    }
    else if(m_robotContainer.launchPad.getRawButton(11))
    {
      System.out.println("btn 11");
      shooter.toggleState(Constants.Direction.forward);
    }
    else
    {
      shooter.toggleState(Constants.Direction.off);
    }


  }
    //trigger turns conveyor on
    

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
