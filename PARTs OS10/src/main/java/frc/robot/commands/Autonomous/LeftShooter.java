/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.Direction;
import frc.robot.Sensors.Proximity;
import frc.robot.commands.Delay;
import frc.robot.commands.DriveCom;
import frc.robot.commands.Shoot;



// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class LeftShooter extends SequentialCommandGroup {
  /**
   * Creates a new Left_Autonomous.
   */

  private double degrees = -125; //TODO: set values
  private double distance = 0; //TODO: set values
  private Proximity proximity = Proximity.getInstance();
  private long delay;
  public LeftShooter() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super();
    addCommands(
    new Delay(),
    new DriveCom(10, Direction.reverse),
    new AutoFire()
     //new Shoot(proximity.getDistance())
    );
    
  }
}