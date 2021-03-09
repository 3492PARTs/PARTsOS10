/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
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

public class MiddleLowGoal extends SequentialCommandGroup {
  /**
   * Creates a new Middle_LowGoal_Autonomous.
   */
  private long duration = 5000;
  Proximity proximity = Proximity.getInstance();

  public MiddleLowGoal() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super();
    addCommands(new Delay(), new DriveCom(90, Direction.forward),
        new ParallelCommandGroup(new ConveyorCom(duration), new Shoot(ShootSpeed.quarter, (int) duration)));
  }

}