/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.Direction;
import frc.robot.Sensors.PhotoElectricSensor;
import frc.robot.commands.Autonomous.*;
import frc.robot.subsystems.Conveyor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private RobotContainer m_robotContainer;
  public static boolean shooterStatusRight;
  public static boolean shooterStatusLeft;
  public static Conveyor conveyor = Conveyor.getInstance(); 
    private double choosenDelay;
  public static boolean driveOrientation;
  public static PhotoElectricSensor pes = PhotoElectricSensor.getInstance();
  public static boolean autoShoot = false;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  Command m_autonomousCommand;
  public static boolean AutoFireReverseLock;
  public static boolean photolockfront;
  public static boolean photolockback;
  public static SendableChooser<Command> m_chooser = new SendableChooser<>();
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  private HttpCamera limelightFeed;

  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    limelightFeed = new HttpCamera("limelight", "http://limelight.local:5800/stream.mjpg");
    //driverShuffleboardTab.add("LL", limelightFeed).withPosition(0,0).withSize(15,8).withProperties(Map.of("Show Crosshair", true, "Show Controls", false));
    CameraServer.getInstance().startAutomaticCapture();
    m_robotContainer = new RobotContainer();
    m_robotContainer.conveyorSpace.whenPressed(new ConveyerSpaceCom(1.5));
    m_robotContainer.conveyorSpace.whenPressed(new ConveyerSpaceCom(1.5));
    m_robotContainer.elevatorPivot.whenPressed(new Pivot_Command());
    m_robotContainer.elevatorPivot2.whenPressed(new Pivot_Command());
    m_robotContainer.climbUp.whenPressed(new ClimbCom());
    m_robotContainer.gyro.getGyro().initGyro();
    m_robotContainer.gyro.getGyro().calibrate();
    driveOrientation = true;
    m_robotContainer.smartDashBoard.robotInitUpdate();
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

    shooterStatusLeft =  Math.abs(m_robotContainer.shooter.getLeftRPM()) >= 4000.0; 
    shooterStatusRight =  Math.abs(m_robotContainer.shooter.getRightRPM()) >= 4000.0; 
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
    
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    System.out.println("auto: " + m_autonomousCommand);
    // schedule the autonomous command (example)
    if(m_autonomousCommand != null){
      m_autonomousCommand.schedule();
    }
    choosenDelay = SmartDashboard.getNumber(Constants.SD_AUTO_DELAY, 0.0);
    System.out.println("Our choosen delay is " + choosenDelay);
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
    driveOrientation = false;
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


  @Override
  public void teleopPeriodic() {
    
    m_robotContainer.smartDashBoard.teleopPeriodicUpdate();

    if ((shooterStatusLeft || shooterStatusRight) && autoShoot) {
      AutoFireReverseLock = false;
      new ConveyerSpaceCom(1.5).schedule();
    }

    if(autoShoot && pes.photoEyeShoot.get() && !AutoFireReverseLock){
    
      new ConveyerSpaceCom(-.2);
    }

    if(m_robotContainer.launchPad.getRawButton(1)) {
     autoShoot = !autoShoot;
    }  


    final double Joystick1y = (m_robotContainer.rightJoystick.getY());
    final double Joystick2y = (m_robotContainer.leftJoystick.getY());
    //m_robotContainer.drive.moveLimited(Joystick1y, Joystick2y);

    // counter
    if(pes.photoEyeIntake.get() && !photolockback){
      photolockback = true;
      pes.counterIncrease();
      pes.lockTimer();
    }
    if(pes.photoEyeShoot.get() && !photolockfront){
      photolockfront = true;
      pes.counterDecrease();
      pes.lockTimer();
    }
    if(m_robotContainer.drive.mult > 0){
    m_robotContainer.drive.moveLimited(Joystick1y, Joystick2y);
    }
    else if(m_robotContainer.drive.mult < 0){
      m_robotContainer.drive.moveLimited(Joystick2y, Joystick1y);
    }

    //Drive inversion

    if(m_robotContainer.leftJoystick.getRawButton(3) || m_robotContainer.rightJoystick.getRawButton(3))
    {
      m_robotContainer.drive.switchFront(false);
    }

    //Drive inversion 2 electric boogaloo
    if(m_robotContainer.leftJoystick.getRawButton(4) || m_robotContainer.rightJoystick.getRawButton(4))
    {
      m_robotContainer.drive.switchFront(true);
    }


    
    if(m_robotContainer.launchPad.getRawButton(1))//conveyor out
    {
      m_robotContainer.conveyor.toggleState(Constants.Direction.forward);
    }
    else if(m_robotContainer.launchPad.getRawButton(3))//conveyor in
    {
      m_robotContainer.conveyor.toggleState(Constants.Direction.reverse);
    }
    else
    {
      m_robotContainer.conveyor.toggleState(Constants.Direction.off);
    }
   

    
    if(m_robotContainer.rightJoystick.getRawButton(1))
    {
      m_robotContainer.intake.wheelToggleState(Direction.reverse); //intake in
    }
    else if(m_robotContainer.leftJoystick.getRawButton(7) ||  m_robotContainer.rightJoystick.getRawButton(7))
    {
      m_robotContainer.intake.wheelToggleState(Direction.forward);//intake out
    }
    else
    { 
      m_robotContainer.intake.wheelToggleState(Direction.off);
    }

   
  
    if(m_robotContainer.launchPad.getRawButton(8)) //elevator up
    {
      m_robotContainer.climber.elevatorToggleState(Constants.Direction.forward);
    }
    else if(m_robotContainer.launchPad.getRawButton(6))//elevator down
    {
      m_robotContainer.climber.elevatorToggleState(Constants.Direction.reverse);
    }
    else
    {
      m_robotContainer.climber.elevatorToggleState(Constants.Direction.off);
    }

    // climber
    if(m_robotContainer.launchPad.getRawButton(5))
    {
      m_robotContainer.climber.climbToggleState(Constants.Direction.reverse);
    }
    else
    {
      m_robotContainer.climber.climbToggleState(Constants.Direction.off);
    }

   
    if(m_robotContainer.launchPad.getRawButton(7))//pivot back
    {
      m_robotContainer.climber.pivotToggleState(Constants.Direction.reverse);
    }
    else if(m_robotContainer.leftJoystick.getRawButton(8) ||  m_robotContainer.rightJoystick.getRawButton(8))
    {
      m_robotContainer.climber.pivotToggleState(Constants.Direction.forward);
    }
    else
    {
      m_robotContainer.climber.pivotToggleState(Constants.Direction.off);
    }


  
    if(m_robotContainer.launchPad.getRawButton(10))//shooter out
    {
      m_robotContainer.shooter.toggleState(Constants.Direction.reverse, .7);
    }
    else if(m_robotContainer.launchPad.getRawButton(11))//shooter in
    {
      m_robotContainer.shooter.toggleState(Constants.Direction.forward);
    }
    else if(m_robotContainer.leftJoystick.getRawButton(13) ||  m_robotContainer.rightJoystick.getRawButton(13))
    {
      m_robotContainer.shooter.toggleState(Constants.Direction.reverse, .35); //shooter low out
    }
    else
    {
      m_robotContainer.shooter.toggleState(Constants.Direction.off);
    }

    if(m_robotContainer.leftJoystick.getRawButton(11) ||  m_robotContainer.rightJoystick.getRawButton(11))
    {
      m_robotContainer.intake.pivotToggleState(Constants.Direction.forward); //arm pivot up
    }
    else if(m_robotContainer.leftJoystick.getRawButton(5) ||  m_robotContainer.rightJoystick.getRawButton(5))
    {
      m_robotContainer.intake.pivotToggleState(Constants.Direction.reverse); //arm pivot down
    }
    else
    {
      m_robotContainer.intake.pivotToggleState(Constants.Direction.off);
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
