// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.Controller;
import frc.robot.components.DriveFrame;
import frc.robot.data.StickPosition;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final Controller controller;
  private final DriveFrame driveFrame;

  public final AprilTagFieldLayout kTagLayout = AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField);
  public final Transform3d kRobotToCam = new Transform3d(new Translation3d(0.5, 0.0, 0.5), new Rotation3d(0, 0, 0));

  private final PhotonCamera frontCamera = new PhotonCamera("front-camera");
  private final PhotonPoseEstimator poseEstimator = new PhotonPoseEstimator(kTagLayout, 
      PhotonPoseEstimator.PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, kRobotToCam);

  public Robot() {
    controller = new Controller();
    driveFrame = new DriveFrame();
  }

  int counter = 0;

  @Override
  public void robotPeriodic() {
    List<PhotonPipelineResult> result = frontCamera.getAllUnreadResults();
    
    for (PhotonPipelineResult i : result) {
      poseEstimator.update(i);
      counter++;
    }

    if (counter % 20 == 0) {
      //SmartDashboard.putData(poseEstimator);
      counter = 0;
    }
  }


  @Override
  public void autonomousInit() {}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    StickPosition left_joystick = controller.getLeftStickPosition();
    StickPosition right_joystick = controller.getRightStickPosition(); 
    
    driveFrame.dualStickDrive(left_joystick, right_joystick);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
