/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Sensors.Encoders;
import frc.robot.Sensors.PhotoElectricSensor;
import frc.robot.subsystems.Climber;

/**
 * Add your docs here.
 */
public class SmartDashBoard {
    private final RobotContainer m_robotContainer = new RobotContainer();

    // =====================================================================================
    // Define Singleton Pattern
    // =====================================================================================
    private static SmartDashBoard _staticSmartDashboard = new SmartDashBoard();
    private static Encoders encoders = Encoders.getInstance();

    public static SmartDashBoard getInstance() {
        return _staticSmartDashboard;
    }

    /**
     * updates smartdashboard during described period
     */
    public void robotInitUpdate() {
        SmartDashboard.putString(Constants.SD_DRIVE_ORIENTATION, m_robotContainer.drive.getDriveFront());
        SmartDashboard.putNumber(Constants.SD_AUTO_DELAY, 0.0);
        SmartDashboard.putBoolean(Constants.SD_LEFT_SHOOTER_SPEED, false);
        SmartDashboard.putBoolean(Constants.SD_RIGHT_SHOOTER_SPEED, false);
        SmartDashboard.putBoolean(Constants.SD_SHOOTER_SPEED, false);
        SmartDashboard.putBoolean(Constants.SD_AUTO_SHOOT, Constants.autoShoot);
    }

    public void robotPeriodicUpdate() {
        SmartDashboard.putBoolean(Constants.SD_LEFT_SHOOTER_SPEED, m_robotContainer.shooter.getShooterStatusLeft());
        SmartDashboard.putBoolean(Constants.SD_RIGHT_SHOOTER_SPEED, m_robotContainer.shooter.getShooterStatusRight());
        SmartDashboard.putBoolean(Constants.SD_SHOOTER_SPEED,
                m_robotContainer.shooter.getShooterStatusLeft() && m_robotContainer.shooter.getShooterStatusRight());
        SmartDashboard.putNumber(Constants.SD_PROXIMITY_DIST, m_robotContainer.proximity.getDistance());
        SmartDashboard.putBoolean(Constants.SD_LEFT_SHOOTER_SPEED_LOW, m_robotContainer.shooter.getShooterStatusLeft());
        SmartDashboard.putBoolean(Constants.SD_RIGHT_SHOOTER_SPEED_LOW, m_robotContainer.shooter.getShooterStatusRight());
        
    }

    public void autoPeriodicUpdate() {
        SmartDashboard.putNumber(Constants.SD_ENCODER_DIST, m_robotContainer.encoders.getDistanceMovedRight());
    }

    public void teleopPeriodicUpdate() {
        SmartDashboard.putNumber(Constants.SD_PIVOT_EL_ENCODER, m_robotContainer.encoders.getPivotElevatorEncoderRot());
        SmartDashboard.putBoolean(Constants.SD_PE_SHOOTER, m_robotContainer.PESensor.getPhotoEyeShoot().get());
        SmartDashboard.putBoolean(Constants.SD_PE_INTAKE, m_robotContainer.PESensor.getPhotoEyeIntake().get());
        SmartDashboard.putNumber(Constants.SD_CLIMB_ENCODER, m_robotContainer.encoders.getElevatorEncoderRot());
        SmartDashboard.putString(Constants.SD_DRIVE_ORIENTATION, m_robotContainer.drive.getDriveFront());
        SmartDashboard.putBoolean(Constants.SD_AUTO_SHOOT, Constants.autoShoot);
        SmartDashboard.putNumber(Constants.SD_BALL_CNT, m_robotContainer.PESensor.getCounter());
        SmartDashboard.putNumber(Constants.SD_ARM_PIVOT_COUNTER, m_robotContainer.encoders.getArmPivotEncoderRot());
        SmartDashboard.putNumber("climber1 current", m_robotContainer.climber.climb1.getSupplyCurrent());
        SmartDashboard.putNumber("climber2 current", m_robotContainer.climber.climb2.getSupplyCurrent());
    }
}
