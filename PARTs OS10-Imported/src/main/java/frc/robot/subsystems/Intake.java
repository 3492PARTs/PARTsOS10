/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Direction;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private final double forwardHalt = 0.0; // TODO: set value
  private final double reverseHalt = 0.0;
  private TalonSRX wheelIntake;
  private TalonSRX pivotIntake;
  private int amps = 25;
  private int timeoutMs = 0;
  //Encoders encoders = Encoders.getInstance();
  double maximumRotMin = 0;//TODO: set value
  double minimumRotMin = 0;//TODO: set


  // =====================================================================================
  // Define Singleton Pattern
  // =====================================================================================
  private static Intake _staticIntake = new Intake();

  public static Intake getInstance() {
    return _staticIntake;
  }

  public Intake() {
    wheelIntake = new TalonSRX(Constants.INTAKE_WHEEL_PORT);
    pivotIntake = new TalonSRX(Constants.PIVOT_INTAKE_PORT);
    wheelIntake.configPeakCurrentDuration(100, 10);
    wheelIntake.configContinuousCurrentLimit(55, timeoutMs);
    wheelIntake.configPeakCurrentLimit(amps, timeoutMs);
    wheelIntake.enableCurrentLimit(true);

    wheelIntake.configPeakCurrentDuration(100, 10);
    wheelIntake.configContinuousCurrentLimit(55, timeoutMs);
    wheelIntake.configPeakCurrentLimit(amps, timeoutMs);
    wheelIntake.enableCurrentLimit(true);
  }

  /**
   * Set the state of the wheels
   * 
   * @param dir What directions the wheels should spin
   */
  public void wheelToggleState(Direction dir) {
    if (dir == Direction.forward) {
      wheelIntake.set(ControlMode.PercentOutput, 1);
    } else if (dir == Direction.reverse) {
      wheelIntake.set(ControlMode.PercentOutput, -1);
    } else {
      wheelIntake.set(ControlMode.PercentOutput, 0);
    }
  }

  /**
   * Pivot the intake arm
   * 
   * @param dir What direction to pivot the arm
   */
  public void pivotToggleState(Direction dir) {// put in halts
    if (dir == Direction.forward) {
      pivotIntake.set(ControlMode.PercentOutput, .3);
    } else if (dir == Direction.reverse) {
      pivotIntake.set(ControlMode.PercentOutput, -.3);
    } else {
      pivotIntake.set(ControlMode.PercentOutput, 0);
    }
  }

  /**
   * Stop the while intake system from moving
   */
  public void stop() {
    wheelToggleState(Direction.off);
    pivotToggleState(Direction.off);
  }

  /**
   * Get the intake talon
   * 
   * @return the Pivod Intake Talon reference
   */
  public TalonSRX getPivotIntakeTalon() {
    return pivotIntake;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
