/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.*;
import frc.robot.Constants.Direction;
import frc.robot.Sensors.*;
import frc.robot.commands.*;
import frc.robot.commands.Autonomous.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // =====================================================================================
  // Auto
  // =====================================================================================
  private Command m_autoCommand;
  private SendableChooser<Command> m_chooser = new SendableChooser<>();

  // =====================================================================================
  // Subsystems & Sensors
  // =====================================================================================
  public final LimeLight limeLight = LimeLight.getInstance();
  public final SmartDashBoard smartDashBoard = SmartDashBoard.getInstance();
  public final Conveyor conveyor = Conveyor.getInstance();
  public final Shooter shooter = Shooter.getInstance();
  public final DriveSparkMax drive = DriveSparkMax.getInstance();
  public final Intake intake = Intake.getInstance();
  public final Climber climber = Climber.getInstance();
  public final Proximity proximity = Proximity.getInstance();
  public final Gyro gyro = Gyro.getInstance();
  public final Encoders encoders = Encoders.getInstance();
  public final PhotoElectricSensor PESensor = PhotoElectricSensor.getInstance();
  public Pivot_Command climberCommand = new Pivot_Command();
  public ClimbCom climb = new ClimbCom(); // Why?????
  // public ArmPivotCom armPivotComUp = new ArmPivotCom(Direction.forward);
  // public ArmPivotCom armPivotComDown = new ArmPivotCom(Direction.reverse);

  // =====================================================================================
  // Joysticks
  // =====================================================================================
  public Joystick rightJoystick = new Joystick(0);
  public Joystick leftJoystick = new Joystick(1);
  public Joystick launchPad = new Joystick(2);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Add options for AUTO to SD
    SmartDashboard.putData("Choose Autonomous Mode", m_chooser);
    m_chooser.setDefaultOption("MiddleTopShooter", new StraightTopShooter());
    m_chooser.addOption("Left starting positon", new LeftShooter());
    m_chooser.addOption("Right Start Position", new RightShooter());
    m_chooser.addOption("Middle Low Goal", new MiddleLowGoal());

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton conveyorSpace = new JoystickButton(leftJoystick, 1);
    conveyorSpace.whenPressed(new ConveyerSpaceCom(1.5));

    JoystickButton elevatorPivot = new JoystickButton(launchPad, 5);
    elevatorPivot.whenPressed(new Pivot_Command());

    // JoystickButton armPivotUp = new JoystickButton(rightJoystick, 4);
    // armPivotUp.whenPressed(new ArmPivotCom(Direction.forward));
    
    // JoystickButton armPivotDown = new JoystickButton(rightJoystick, 3);
    // armPivotDown.whenPressed(new ArmPivotCom(Direction.reverse));
    
    Button  ElevatorUpAuto = new JoystickButton(launchPad, 9);
    ElevatorUpAuto.whenPressed(new ClimbCom());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    m_autoCommand = m_chooser.getSelected();
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
