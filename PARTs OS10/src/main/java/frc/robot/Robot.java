/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.Direction;
import frc.robot.Sensors.EncodersSparkMax;
import frc.robot.Sensors.Gyro;
import frc.robot.Sensors.Proximity;
import frc.robot.commands.Climber_Command;
import frc.robot.commands.Autonomous.*;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.DriveSparkMax;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.Autonomous.ConveyerSpaceCom;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private RobotContainer m_robotContainer;
  private final Conveyor conveyor = Conveyor.getInstance();
  private final Shooter shooter =  Shooter.getInstance();
  private final DriveSparkMax drive = DriveSparkMax.getInstance();
  private final Intake intake = Intake.getInstance();
  private final Climber climber = Climber.getInstance();
  private final Proximity proximity = Proximity.getInstance();
  private final Gyro gyro = Gyro.getInstance();
  private final EncodersSparkMax encoders = EncodersSparkMax.getInstance();

  private double choosenDelay;

NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
NetworkTableEntry tx = table.getEntry("tx");
NetworkTableEntry ty = table.getEntry("ty");
NetworkTableEntry ta = table.getEntry("ta");

Command m_autonomousCommand;
public static SendableChooser<Command> m_chooser = new SendableChooser<>();
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
    CameraServer.getInstance().startAutomaticCapture();
    m_robotContainer = new RobotContainer();
    gyro.gyro.initGyro();
    gyro.gyro.calibrate();
    Constants.driveOrientation = true;

    // m_robotContainer.pivoter.whenPressed(new Climber_Command());
    System.out.println("auto options");
    SmartDashboard.putNumber(Constants.SD_AUTO_DELAY, 0.0);
    SmartDashboard.putData("Choose Autonomous Mode", m_chooser);
    m_chooser.setDefaultOption("MiddleTopShooter", new StraightTopShooter());
    m_chooser.addOption("Left starting positon", new LeftShooter());
    m_chooser.addOption("Right Start Position", new RightShooter());
    m_chooser.addOption("Middle Low Goal", new MiddleLowGoal());
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
    
     m_autonomousCommand = m_robotContainer.getAutonomousCommand();

     System.out.println("auto: " + m_autonomousCommand);
    // schedule the autonomous command (example)
    if(m_autonomousCommand != null){m_autonomousCommand.schedule();}
      choosenDelay = SmartDashboard.getNumber(Constants.SD_AUTO_DELAY, 0.0);
      System.out.println("Our choosen delay is " + choosenDelay);
    

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("Proximity Distance", proximity.getDistance());
    SmartDashboard.putNumber("Encoder Distance", encoders.getDistanceMovedRight());
  }

  @Override
  public void teleopInit() {
    Constants.driveOrientation = false;
    if(m_autonomousCommand != null){
      m_autonomousCommand.cancel();


    }
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
  }

  /**
   * This function is called periodically during operator control.
   */

  double limitedJS1 = 0;
  double limitedJS2 = 0;

  @Override
  public void teleopPeriodic() {


    SmartDashboard.putNumber("Left Shooter RPM is: ", 600*(shooter.shooterLeft.getSelectedSensorVelocity()/4096));
    SmartDashboard.putNumber("Right Shooter RPM is: ", 600*(shooter.shooterRight.getSelectedSensorVelocity()/4096));

    
    SmartDashboard.putNumber("fixing encoder 1", encoders.getDistanceFix());
   //rampUp code
    final double Joystick1y = (Constants.mult)*(m_robotContainer.rightJoystick.getY());
    final double limit = .012;
    double change = Joystick1y - limitedJS1;
    if (change > limit)
      change = limit;
    else if (change <= limit)
      change = -limit;
    limitedJS1 += change;

    final double Joystick2y = (Constants.mult)*(m_robotContainer.leftJoystick.getY());
    change = Joystick2y - limitedJS2;
    if (change>limit) change = limit;
    else if( change<=limit) change = -limit;
    limitedJS2 += change;

    drive.move(limitedJS1, limitedJS2);

    //Drive inversion

    m_robotContainer.leftButton_9.whenPressed(new ConveyerSpaceCom(1));
    m_robotContainer.rightButton_9.whenPressed(new ConveyerSpaceCom(1));


    if(m_robotContainer.leftJoystick.getRawButton(3) || m_robotContainer.rightJoystick.getRawButton(3))
    {
      drive.switchFront(false);
    }

    //Drive inversion 2 electric boogaloo
    if(m_robotContainer.leftJoystick.getRawButton(4) || m_robotContainer.rightJoystick.getRawButton(4))
    {
      drive.switchFront(true);
    }
   

    
    if(m_robotContainer.launchPad.getRawButton(1))//conveyor out
    {
      conveyor.toggleState(Constants.Direction.forward);
    }
    else if(m_robotContainer.launchPad.getRawButton(3))//conveyor in
    {
      conveyor.toggleState(Constants.Direction.reverse);
    }
    else
    {
      conveyor.toggleState(Constants.Direction.off);
    }
   

    
    if(m_robotContainer.leftJoystick.getRawButton(1) ||  m_robotContainer.rightJoystick.getRawButton(1))
    {
      intake.wheelToggleState(Direction.forward); //intake in
    }
    else if(m_robotContainer.leftJoystick.getRawButton(7) ||  m_robotContainer.rightJoystick.getRawButton(7))
    {
      intake.wheelToggleState(Direction.reverse);//intake out
    }
    else
    { 
    intake.wheelToggleState(Direction.off);
    }

   
  
    if(m_robotContainer.launchPad.getRawButton(8)) //elevator up
    {
      climber.elevatorToggleState(Constants.Direction.forward);
    }
    else if(m_robotContainer.launchPad.getRawButton(6))//elevator down
    {
      climber.elevatorToggleState(Constants.Direction.reverse);
    }
    else
    {
      climber.elevatorToggleState(Constants.Direction.off);
    }

    // climber
    if(m_robotContainer.launchPad.getRawButton(5))
    {
      climber.climbToggleState(Constants.Direction.forward);
    }
    else
    {
      climber.climbToggleState(Constants.Direction.off);
    }

   
    if(m_robotContainer.launchPad.getRawButton(7))//pivot back
    {
      climber.pivotToggleState(Constants.Direction.reverse);
    }
    else if(m_robotContainer.leftJoystick.getRawButton(8) ||  m_robotContainer.rightJoystick.getRawButton(8))
    {
      climber.pivotToggleState(Constants.Direction.forward);
    }
    else
    {
      climber.pivotToggleState(Constants.Direction.off);
    }


  
    if(m_robotContainer.launchPad.getRawButton(10))//shooter out
    {
      shooter.toggleState(Constants.Direction.reverse, .35);
    }
    else if(m_robotContainer.launchPad.getRawButton(11))//shooter in
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
