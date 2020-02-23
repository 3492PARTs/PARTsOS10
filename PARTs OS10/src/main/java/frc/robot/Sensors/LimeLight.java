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
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Add your docs here.
 */
public class LimeLight {

    private LimeLight _staticLimeLight = new LimeLight();

    public LimeLight getInstance() {
        return _staticLimeLight;
    }

    LimeLight() {
        ShuffleboardTab currentTab = Shuffleboard.getTab("dash");
        HttpCamera limeLight = new HttpCamera("LimeLight", "http://10.34.92.46:5800/stream.mpeg");
        UsbCamera feed = CameraServer.getInstance().startAutomaticCapture(0);
        feed.setConnectVerbose(1);

    }
}
