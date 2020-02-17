/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Climber_Command;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drive;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private static Command m_autoCommand;// = new ExampleCommand(m_exampleSubsystem);
  public static Climber climber = Climber.getInstance();
  public static  Conveyor conveyor = Conveyor.getInstance();
  public static Shooter shooter = Shooter.getInstance();
  public static Intake intake = Intake.getInstance();
  public static Drive drive = Drive.getInstance();
  public static Climber_Command climberCommand = new Climber_Command();
// The two joysticks
  public Joystick rightJoystick = new Joystick(0);
  public Joystick leftJoystick = new Joystick(1);
  public Joystick launchPad = new Joystick(2);
  public JoystickButton leftButton_9 = new JoystickButton(leftJoystick, 9);
  public JoystickButton rightButton_9 = new JoystickButton(rightJoystick, 9);

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
