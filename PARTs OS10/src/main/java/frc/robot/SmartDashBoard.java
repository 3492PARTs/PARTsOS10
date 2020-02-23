/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Sensors.PhotoElectricSensor;

/**
 * Add your docs here.
 */
public class SmartDashBoard {
    private final RobotContainer m_robotContainer = new RobotContainer();

    private static SmartDashBoard _staticSmartDashboard = new SmartDashBoard();
    public static SmartDashBoard getInstance(){
        return _staticSmartDashboard;
    }

/**
 * updates smartdashboard during described period
 */
    public void robotInitUpdate(){
        SmartDashboard.putString("Drive Orientation", m_robotContainer.drive.driveFront);
        SmartDashboard.putNumber(Constants.SD_AUTO_DELAY, 0.0);
        SmartDashboard.putBoolean("Should shoot LEFT?", false);
        SmartDashboard.putBoolean("Should shoot RIGHT?", false);
        SmartDashboard.putBoolean("Should shoot?", false);
        SmartDashboard.putBoolean("Autoshoot", Robot.autoShoot);
    }

    public void robotPeriodicUpdate(){
        SmartDashboard.putBoolean("Should shoot LEFT?", Robot.shooterStatusLeft);
        SmartDashboard.putBoolean("Should shoot RIGHT?", Robot.shooterStatusRight);
        SmartDashboard.putBoolean("Should shoot?", Robot.shooterStatusLeft && Robot.shooterStatusRight);
        SmartDashboard.putBoolean("Photo eye camera", PhotoElectricSensor.getInstance().photoEyeIntake.get());
    }

    public void autoPeriodicUpdate(){
        SmartDashboard.putNumber("Proximity Distance", m_robotContainer.proximity.getDistance());
        SmartDashboard.putNumber("Encoder Distance", m_robotContainer.encoders.getDistanceMovedRight());
    }

    public void teleopPeriodicUpdate(){
        SmartDashboard.putNumber("Pivot Encoder", m_robotContainer.encoders.getPivotElevatorEncoderRot());
        SmartDashboard.putBoolean("PESensorShoot", m_robotContainer.PESensor.photoEyeShoot.get());
        SmartDashboard.putBoolean("PESensorIntake", m_robotContainer.PESensor.photoEyeIntake.get());
        SmartDashboard.putNumber("Prox. Distance", m_robotContainer.proximity.getDistance());
        SmartDashboard.putNumber("Climb encoder", m_robotContainer.encoders.getElevatorEncoderRot());
        SmartDashboard.putString("Drive Orientation", m_robotContainer.drive.driveFront);
        SmartDashboard.putBoolean("Autoshoot", Robot.autoShoot);
        SmartDashboard.putNumber("the number of balls", PhotoElectricSensor.getInstance().getCounter());
    }
}
