// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  NetworkTable limelightTable;
  double ty, tv, tx, angle, distance;
  public Limelight() {
    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    ty = limelightTable.getEntry("ty").getDouble(0);
    tv = limelightTable.getEntry("tv").getDouble(0);
    tx = limelightTable.getEntry("tx").getDouble(0);
  }

  public double getAngle() {
    SmartDashboard.putNumber("Horizontal Angle:", tx);
    if (tx != 0) {
      return tx;
    }
    return 0;
  }


  public double getDistance() {
    if (tv != 0) {
      angle = (Constants.ANGLE_CAMERA + ty) * Math.PI / 180;
      distance = (Constants.HEIGHT_TARGET - Constants.HEIGHT_CAMERA) / Math.tan(angle);
      return distance;
    }
    return 0;
  }

  public static double getDesiredRPM(){
    return 0.0;
    //Add implementation
  }

  @Override
  public void periodic() {
  }
}
