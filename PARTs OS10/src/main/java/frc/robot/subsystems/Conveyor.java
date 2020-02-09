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
  int timeoutMs = 0;
  public Conveyor() {
    conveyor = new TalonSRX(Conveyor_port);  
  conveyor.configPeakCurrentDuration(100, 10); 
  conveyor.configContinuousCurrentLimit(45, timeoutMs);
  conveyor.configPeakCurrentLimit(25, timeoutMs);
  conveyor.enableCurrentLimit(true);
  }
  /**
   * 
   * @param result a value for the direction the motor should rotate. 0 is none, 1 is forward, 2 is out.
   */
  public void toggleState(Direction dir){
      if(dir == Direction.forward){
       conveyor.set(ControlMode.PercentOutput, -1);
    } 
    else if(dir == Direction.reverse){
        conveyor.set(ControlMode.PercentOutput, 1);
    }
    else conveyor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
