package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import static frc.robot.Constants.Direction;

public class Conveyor extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  TalonSRX conveyor;
  int Conveyor_port = 4;

  public Conveyor() {
    conveyor = new TalonSRX(Conveyor_port);
  }
  /**
   * 
   * @param result a value for the direction the motor should rotate. 0 is none, 1 is forward, 2 is out.
   */
  public void toggleState(Direction dir){
      if(dir == Direction.forward){
       conveyor.set(ControlMode.PercentOutput, .4);
    } 
    else if(dir == Direction.reverse){
        conveyor.set(ControlMode.PercentOutput, -.4);
    }
    else conveyor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
