/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Sensors.Encoders;
import frc.robot.Sensors.GyroSensor;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;

public class DriveSparkMax extends SubsystemBase {
  /**
   * Creates a new Drive.
   */
  private CANSparkMax Right1 = new CANSparkMax(Constants.Right1_port, MotorType.kBrushless);
  private CANSparkMax Right2 = new CANSparkMax(Constants.Right2_port, MotorType.kBrushless);
  private CANSparkMax Right3 = new CANSparkMax(Constants.Right3_port, MotorType.kBrushless);

  private CANSparkMax Left1 = new CANSparkMax(Constants.Left1_port, MotorType.kBrushless);
  private CANSparkMax Left2 = new CANSparkMax(Constants.Left2_port, MotorType.kBrushless);
  private CANSparkMax Left3 = new CANSparkMax(Constants.Left3_port, MotorType.kBrushless);

  private SpeedControllerGroup Right = new SpeedControllerGroup(Right1, Right2, Right3);
  private SpeedControllerGroup Left = new SpeedControllerGroup(Left1, Left2, Left3);
  private DifferentialDrive M_drive = new DifferentialDrive(Left, Right);

  private double ksVolts = 0.217;//TODO: get from characterization
  private double kvVoltSecondsPerMeter = 11.5;//TODO: get from characterization 
  private double kaVoltSecondsSquaredPerMeter = 2.95;//TODO: get from characterization
  private DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(0.12732171733494124);// TODO: get from Mechanical
  private double kRamseteB = 2;//given by wpilib
  private double kRamseteZeta = 0.7;//given by wpilib

  private int amps = 40;
  private int timeoutMs = 0;
  private int peakAmps = 55;
  private int mult = 1;
  private String driveFront = "";
  private double KPDrive = 0.873; 

  //private Encoders encoders = Encoders.getInstance(); 

  private Gyro m_Gyro = GyroSensor.getInstance().getGyro();
  private DifferentialDriveOdometry m_Odometry = new DifferentialDriveOdometry(m_Gyro.getRotation2d());

  public DriveSparkMax(){
    rightEncoder = getRight1SparkMax().getEncoder();
    leftEncoder = getLeft1SparkMax().getEncoder();
}

  // =====================================================================================
  // Define Singleton Pattern
  // =====================================================================================
  private static DriveSparkMax _staticDriveSparkMax = new DriveSparkMax();

  public static DriveSparkMax getInstance() {
    return _staticDriveSparkMax;
  }

    /**
   * Drive
   * 
   * @param Speed  left between 1, -1
   * @param Speed2 right drive train between 1,-1
   */
  public void move(Double Speed1, Double Speed2) {
    M_drive.tankDrive(Speed1, Speed2);
  }

  // joystick limiter
  private double limitedJS1 = 0;
  private double limitedJS2 = 0;

  /**
   * Drive with speed ramp
   * 
   * @param joyY joystick left y axis
   * @param JoyX joystick right y axis
   */
  public void moveLimited(Double joy1, Double joy2) {
    double limit = .02;
    double change = joy1 - limitedJS1;
    if (change > limit) {
      change = limit;
    } else if (change <= limit) {
      change = -limit;
    }
    limitedJS1 += change;

    change = joy2 - limitedJS2;
    if (change > limit)
      change = limit;
    else if (change <= limit)
      change = -limit;
    limitedJS2 += change;
    M_drive.tankDrive((mult) * limitedJS1, (mult) * limitedJS2);
  }

  /**
   * Toggle which side is the front of the robot
   * 
   * @param orientation true = shooter is front, false = intake is front
   */
  public void switchFront(boolean orientation) {
    if (orientation) {
      mult = 1;
      driveFront = "Shoot";
    } else {
      mult = -1;
      driveFront = "INTAKE";
    }
  }

  public Pose2d getPose(){
    return m_Odometry.getPoseMeters();
  }

  public void updatePose(){
    m_Odometry.update(m_Gyro.getRotation2d(), getDistanceMovedLeft(), getDistanceMovedRight());
  }

  /**
   * Get the multiplier variable
   * 
   * @return mult
   */
  public int getMult() {
    return mult;
  }

  /**
   * Get which side is the front of the robot
   * 
   * @return driveFront
   */
  public String getDriveFront() {
    return driveFront;
  }

  public void moveVolts(double leftV, double RightV){
    Left.setVoltage(leftV);
    Right.setVoltage(-RightV);
    M_drive.feed();
  }

  public void feed(){
    M_drive.feed();
  }

  /**
   * Get the Right1 spark max
   * 
   * @return Right1
   */
  public CANSparkMax getRight1SparkMax() {
    return Right2;
  }

  /**
   * Get the Left1 spark max
   * 
   * @return Left1
   */
  public CANSparkMax getLeft1SparkMax() {
    return Left2;
  }

  /**
   * Stop the motors
   */
  public void stop() {
    move(0.0, 0.0);

  }

  public double getKsVolts() {
    return ksVolts;
  }
  public double getKaVoltSecondsSquaredPerMeter() {
    return kaVoltSecondsSquaredPerMeter;
  }

  public double getKvVoltSecondsPerMeter() {
    return kvVoltSecondsPerMeter;
  }

  public DifferentialDriveKinematics getkDriveKinematics() {
    return kDriveKinematics;
  }

  public double getkRamseteB() {
    return kRamseteB;
  }

  public double getkRamseteZeta() {
    return kRamseteZeta;
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds(){
    return new DifferentialDriveWheelSpeeds(getRate()[0], getRate()[1]);
  }

  public double getKPDrive() {
    return KPDrive;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private final Climber climber = Climber.getInstance();
  private final Intake intake = Intake.getInstance();
  private CANEncoder rightEncoder;
  private CANEncoder leftEncoder;

  private static double startPositionRight = 0;
  private static double startPositionLeft = 0;
  private static double startPosPivotElevator = 0;
  private static double startPosElevator = 0;
  private static double startPosArmPivot = 0;
  

  /**
   * Distance of the left drive wheel
   * 
   * @return distance the left wheel moved in in ?
   */
  public double getDistanceMovedLeft() {
      return Math.abs(((leftEncoder.getPosition() - startPositionLeft) * Constants.WHEEL_CIRCUMFERENCE)
              / Constants.GEAR_RATIO)/39.37;
  }

  /**
   * Distance of the right drive wheel
   * 
   * @return distance the right wheel moved in in ?
   */
  public double getDistanceMovedRight() {
      return Math.abs(((rightEncoder.getPosition() - startPositionRight) * Constants.WHEEL_CIRCUMFERENCE)
              / Constants.GEAR_RATIO)/39.37;
  }

  /**
   * Get the rotation of the elevator pivot
   * 
   * @return in rotations
   */
  public double getPivotElevatorEncoderRot() {
      return (((double) climber.getElevatorPivot().getSelectedSensorPosition()) - startPosPivotElevator) / 4096.0;
  }

  /**
   * Get the rotation of the elevator
   * 
   * @return in rotations
   */
  public double getElevatorEncoderRot() {
      return (((double) climber.getElevator().getSelectedSensorPosition()) - startPosElevator) / 4096.0;
  }

  /**
   * Get the rotation of the arm pivot
   * 
   * @return in rotations
   */
  public double getArmPivotEncoderRot() {
      return (((double) intake.getPivotIntakeTalon().getSelectedSensorPosition()) - startPosArmPivot) / 4096.0;
  }

  public double[] getRate() {
      
    return new double[] { rightEncoder.getVelocity(), leftEncoder.getVelocity() };
     
  }


  /**
   * Reset an encoder
   * 
   * @param encoder the encoder to reset
   */
  public void resetEncoders() {
    
    startPositionLeft = leftEncoder.getPosition();
    startPositionRight = rightEncoder.getPosition();
      
  }

}