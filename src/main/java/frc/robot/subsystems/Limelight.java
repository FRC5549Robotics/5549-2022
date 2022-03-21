// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  double Kp = 1/27;
  NetworkTable limelightTable;
  double ty, tv, tx, angle, distance;
  double min_command = 0.05;
  XboxController xbox1;
  double steering_adjust = 0.0;
  private static Limelight limelight;


  public Limelight(XboxController xbox, double inputkP) {
    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    xbox1 = xbox;
    //Kp = inputkP;
    limelight = this;
  }

  public double getAngle() {
    SmartDashboard.putNumber("Horizontal Angle:", tx);
    if (tx != 0) {
      return tx;
    } else {
    return 0;
    }
  }


  public double getDistance() {
    if (tv != 0) {
      angle = (Constants.ANGLE_CAMERA + ty) * Math.PI / 180;
      return (Constants.HEIGHT_TARGET - Constants.HEIGHT_CAMERA) / Math.tan(angle);
    }
    return 0;
  }

  public static Limelight getInstance(){
    return Limelight.limelight;
  }

  public static double getDesiredRPM(){
    return 500.0;
    //Add implementation
  }

  @Override
  public void periodic() {
    ty = limelightTable.getEntry("ty").getDouble(0);
    tv = limelightTable.getEntry("tv").getDouble(0);
    tx = limelightTable.getEntry("tx").getDouble(0);

    if (xbox1.getRawButton(8) == true)
    {
      double heading_error = -tx;
     
      if (tx > 1.0 || tx < -1.0)
      {
        Drivetrain.getInstance().rightGroup.set(-heading_error/54);
        Drivetrain.getInstance().leftGroup.set(heading_error/54);
      }
    }
    
    SmartDashboard.putNumber("Horizontal", tx);
    SmartDashboard.putNumber("Vertical:", ty);
  }
}