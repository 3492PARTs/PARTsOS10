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
import frc.robot.subsystems.*;
import frc.robot.Sensors.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static Command m_autoCommand;
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
// The two joysticks
  public static Joystick rightJoystick = new Joystick(0);
  public static Joystick leftJoystick = new Joystick(1);
  public static Joystick launchPad = new Joystick(2);
  public JoystickButton conveyorSpace = new JoystickButton(leftJoystick, 1);
  public JoystickButton elevatorPivot = new JoystickButton(leftJoystick, 10);
  public JoystickButton elevatorPivot2 = new JoystickButton(rightJoystick, 10);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    //configureButtonBindings();
    
    conveyorSpace.whenPressed(new ConveyerSpaceCom(1.5));
    elevatorPivot.whenPressed(new Pivot_Command());
    elevatorPivot2.whenPressed(new Pivot_Command());

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
