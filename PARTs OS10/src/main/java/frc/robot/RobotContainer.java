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
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  Climber climber = new Climber();
  Conveyor conveyor = new Conveyor();
  Shooter shooter = new Shooter();

// The two joysticks
  public Joystick rightJoystick = new Joystick(0);
  public Joystick leftJoystick = new Joystick(1);
  public static Joystick launchPad = new Joystick(2);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //elevator pivot up
    if(launchPad.getRawButton(9))
    {
      climber.pivotToggleState(Constants.Direction.forward);
    }
    else
    {
      climber.pivotToggleState(Constants.Direction.off);
    }

    //elevator up
    if(launchPad.getRawButton(8))
    {
      climber.toggleState(Constants.Direction.forward);
    }
    else
    {
      climber.toggleState(Constants.Direction.off);
    }

    //climber up
    if(launchPad.getRawButton(5))
    {
      climber.climbToggleState(Constants.Direction.forward);
    }
    else
    {
      climber.climbToggleState(Constants.Direction.off);
    }

    //elevator pivot down
    if(launchPad.getRawButton(7))
    {
      climber.pivotToggleState(Constants.Direction.reverse);
    }
    else
    {
      climber.pivotToggleState(Constants.Direction.reverse);
    }

    //elevator down
    if(launchPad.getRawButton(6))
    {
      climber.toggleState(Constants.Direction.reverse);
    }
    else
    {
      climber.toggleState(Constants.Direction.off);
    }

    // conveyor in
    if(launchPad.getRawButton(3))
    {
      conveyor.toggleState(Constants.Direction.reverse);
    }
    else
    {
      conveyor.toggleState(Constants.Direction.off);
    }

    //conveyor out
    if(launchPad.getRawButton(1))
    {
      conveyor.toggleState(Constants.Direction.forward);
    }
    else
    {
      conveyor.toggleState(Constants.Direction.off);
    }

    //shooter on
    if(launchPad.getRawButton(11))
    {
      shooter.toggleState(Constants.Direction.reverse);
    }
    
    //shooter off
    if(launchPad.getRawButton(10))
    {
      shooter.toggleState(Constants.Direction.off);
    }

    
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
