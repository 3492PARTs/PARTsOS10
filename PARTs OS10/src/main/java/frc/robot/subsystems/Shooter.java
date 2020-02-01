/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import com.ctre.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter extends PIDSubsystem {
  /**
   * Creates a new Shooter.
   */
  private final TalonSRX shooterRight = new TalonSRX(12);
  private final TalonSRX shooterLeft = new TalonSRX(3);
  private final Encoder rShooterEnc = new Encoder(0,1);
  private final Encoder lShooterEnc = new Encoder(2,3);
  
  public Shooter() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
