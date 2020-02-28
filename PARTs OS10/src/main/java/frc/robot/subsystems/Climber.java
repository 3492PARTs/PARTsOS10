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

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */

  public final TalonSRX climb1;
  public  final TalonSRX climb2;
  private final TalonSRX elevator;
  private final TalonSRX elevatorPivot;
  private int peakAmps = 45;
  private int amps = 35;
  private int timeoutMs = 0;
  private double speed = .25;

  // =====================================================================================
  // Define Singleton Pattern
  // =====================================================================================
  private static Climber _staticClimber = new Climber();

  public static Climber getInstance() {
    return _staticClimber;
  }

  public Climber() {
    climb1 = new TalonSRX(Constants.CLIMB_1_PORT);
    climb2 = new TalonSRX(Constants.CLIMB_2_PORT);
    elevator = new TalonSRX(Constants.ELEVATOR_PORT);
    elevatorPivot = new TalonSRX(Constants.ELEVATOR_PIVOT_PORT);

    climb1.configPeakCurrentDuration(100, 10);
    climb1.configContinuousCurrentLimit(amps, timeoutMs);
    climb1.configPeakCurrentLimit(peakAmps, timeoutMs);
    climb1.enableCurrentLimit(true);

    climb2.configPeakCurrentDuration(100, 10);
    climb2.configContinuousCurrentLimit(amps, timeoutMs);
    climb2.configPeakCurrentLimit(peakAmps, timeoutMs);
    climb2.enableCurrentLimit(true);

    elevator.configPeakCurrentDuration(100, 10);
    elevator.configContinuousCurrentLimit(amps, timeoutMs);
    elevator.configPeakCurrentLimit(peakAmps, timeoutMs);
    elevator.enableCurrentLimit(true);

    elevatorPivot.configPeakCurrentDuration(100, 10);
    elevatorPivot.configContinuousCurrentLimit(amps, timeoutMs);
    elevatorPivot.configPeakCurrentLimit(peakAmps, timeoutMs);
    elevatorPivot.enableCurrentLimit(true);
  }

  /**
   * Which direction the elevator should pivot
   * 
   * @param dir forward, reverse, off
   */
  public void pivotToggleState(Direction dir) {
    if (dir == Direction.forward) {
      elevatorPivot.set(ControlMode.PercentOutput, speed);
    } else if (dir == Direction.reverse) {
      elevatorPivot.set(ControlMode.PercentOutput, -speed);
    } else
      elevatorPivot.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Which direction the climber should move
   * 
   * @param dir direction to move
   */
  public void climbToggleState(Direction dir) {
    if (dir == Direction.forward) {
      climb1.set(ControlMode.PercentOutput, speed);
      climb2.set(ControlMode.PercentOutput, speed);
    } else if (dir == Direction.reverse) {
      climb1.set(ControlMode.PercentOutput, -speed);
      climb2.set(ControlMode.PercentOutput, -speed);
    } else {
      climb1.set(ControlMode.PercentOutput, 0);
      climb2.set(ControlMode.PercentOutput, 0);
    }
  }

  /**
   * Which direction the elevator should move
   * 
   * @param dir direction to move
   */
  public void elevatorToggleState(Direction dir) {
    if (dir == Direction.forward) {
      elevator.set(ControlMode.PercentOutput, -1);
    } else if (dir == Direction.reverse) {
      elevator.set(ControlMode.PercentOutput, 1);
    } else
      elevator.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Get the elevator talon
   */
  public TalonSRX getElevator() {
    return elevator;
  }

  /**
   * Get the elevator pivot talon
   */
  public TalonSRX getElevatorPivot() {
    return elevatorPivot;
  }

  /**
   * Stop all motors
   */
  public void stop() {
    elevatorToggleState(Direction.off);
    climbToggleState(Direction.off);
    pivotToggleState(Direction.off);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}