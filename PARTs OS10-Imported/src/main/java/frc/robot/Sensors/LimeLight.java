/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Add your docs here.
 */
public class LimeLight {

    // =====================================================================================
    // Define Singleton Pattern
    // =====================================================================================
    private static LimeLight _staticLimeLight = new LimeLight();
    NetworkTableEntry TargetSighted;
    NetworkTableEntry HorizontalOffset;
    NetworkTableEntry VerticalOffset;
    NetworkTableEntry TargetArea;

    public static LimeLight getInstance() {
        return _staticLimeLight;
    }

    LimeLight() {
        ShuffleboardTab currentTab = Shuffleboard.getTab("SmartDashboard");
        HttpCamera limeLight = new HttpCamera("LimeLight", "http://10.34.92.46:5800/stream.mpeg");
        UsbCamera feed = CameraServer.getInstance().startAutomaticCapture(0);
        feed.setConnectVerbose(1);
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        TargetSighted = table.getEntry("tv");
        HorizontalOffset = table.getEntry("tx");
        VerticalOffset = table.getEntry("ty");
        TargetArea = table.getEntry("ta");
    }


    public boolean isTargetAquired(){
        return TargetSighted.getBoolean(false);
    }

    public double getHorizontalOffset(){
        return HorizontalOffset.getDouble(0.0);
    }

    public double getVerticalOffset(){
        return VerticalOffset.getDouble(0.0);
    }

    public double getTargetArea(){
        return TargetArea.getDouble(0.0);
    }


}
