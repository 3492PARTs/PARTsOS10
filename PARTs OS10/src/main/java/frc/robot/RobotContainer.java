/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.*;
import frc.robot.Sensors.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static Command m_autoCommand;
  public final LimeLight limeLight = LimeLight.getInstance();
  public final SmartDashBoard smartDashBoard = SmartDashBoard.getInstance();
  public final Conveyor conveyor = Conveyor.getInstance();
  public final Shooter shooter =  Shooter.getInstance();
  public final DriveSparkMax drive = DriveSparkMax.getInstance();
  public final Intake intake = Intake.getInstance();
  public final Climber climber = Climber.getInstance();
  public final Proximity proximity = Proximity.getInstance();
  public final Gyro gyro = Gyro.getInstance();
  public final Encoders encoders = Encoders.getInstance();
  public final PhotoElectricSensor PESensor = PhotoElectricSensor.getInstance();
  public static Pivot_Command climberCommand = new Pivot_Command();
  public static ClimbCom climb = new ClimbCom();
// The two joysticks
  public static Joystick rightJoystick = new Joystick(0);
  public static Joystick leftJoystick = new Joystick(1);
  public static Joystick launchPad = new Joystick(2);
  public JoystickButton conveyorSpace = new JoystickButton(leftJoystick, 1);
  public JoystickButton elevatorPivot = new JoystickButton(leftJoystick, 10);
  public JoystickButton elevatorPivot2 = new JoystickButton(rightJoystick, 10);
  public JoystickButton climbUp = new JoystickButton(rightJoystick, 12);
  
  //intake front
  public POVButton orientIntake1 = new POVButton(rightJoystick, 1);
  public POVButton orientIntake2 = new POVButton(rightJoystick, 2);
  public POVButton orientIntake3 = new POVButton(rightJoystick, 3);

  //shooter front 
  public POVButton orientShoot1 = new POVButton(rightJoystick, 5);
  public POVButton orientShoot2 = new POVButton(rightJoystick, 6);
  public POVButton orientShoot3 = new POVButton(rightJoystick, 7);

  public POVButton[] intakeFrontGroup;
  //intakeFrontGroup = {orientShoot1, orientShoot2, orientShoot3};
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    //configureButtonBindings();
    


  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */    
  // public JoystickButton pivoter = new JoystickButton(launchPad, 9);
  // private void configureButtonBindings() { 
    //elevator pivot up } //TODO: test this command


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    m_autoCommand = Robot.m_chooser.getSelected();
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
