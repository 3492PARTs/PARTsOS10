package HardwareMocks;

import edu.wpi.first.wpilibj.SpeedController;

public class MotorMock implements SpeedController {

    private double Speed;
    private boolean isInverted;

    public MotorMock(){}
    public MotorMock(boolean isInverted){
        this.isInverted = isInverted;
    }

    @Override
    public void set(double speed) {
        this.Speed = speed;
    }

    @Override
    public void setVoltage(double outputVolts) {

    }

    @Override
    public double get() {
        return Speed = !isInverted ? Speed * -1 : Speed;
    }

    @Override
    public void setInverted(boolean isInverted) {
        this.isInverted = isInverted;

    }

    @Override
    public boolean getInverted() {
        return isInverted;
    }

    @Override
    public void disable() {
        Speed = 0;

    }

    @Override
    public void stopMotor() {
        Speed = 0;

    }

    @Override// ???????? what
    public void pidWrite(double output) {
    }
}
