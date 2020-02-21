/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.Direction;
import frc.robot.Constants.ShootSpeed;
import frc.robot.Sensors.Proximity;
import frc.robot.commands.Delay;
import frc.robot.commands.DriveCom;
import frc.robot.commands.Shoot;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html


public class StraightTopShooter extends SequentialCommandGroup {
  /**
   * Creates a new StraightTopShooter.
   */
  private final Proximity proximity = Proximity.getInstance();
  public StraightTopShooter() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super();
    addCommands(
      new Delay(),
      new DriveCom(13, Direction.reverse),
      new DriveCom(22, Direction.forward),
      new Shoot(ShootSpeed.full, 5000),
      new DriveCom(40, Direction.reverse));
  }
}