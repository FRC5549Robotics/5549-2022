// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  double Kp =  1/75f;
  NetworkTable limelightTable;
  double ty, tv, tx, angle, distance;
  double min_command = 0.05;
  XboxController xbox1;


  public Limelight(XboxController xbox) {
    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    ty = limelightTable.getEntry("ty").getDouble(0);
    tv = limelightTable.getEntry("tv").getDouble(0);
    tx = limelightTable.getEntry("tx").getDouble(0);
    xbox1 = xbox;
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
    
    if (xbox1.getRawButton(8) == true && (tx > 5| tx < -5))
{
        double heading_error = -tx;
        double steering_adjust = 0.0;
        if (tx > 1.0)
        {
                steering_adjust = Kp*heading_error - min_command;
        }
        else if (tx < 1.0)
        {
                steering_adjust = Kp*heading_error; //+ min_command;
        }
        //left_command += steering_adjust;
        //right_command -= steering_adjust;
        System.out.print(steering_adjust);
        Drivetrain.getInstance().leftGroup.set(steering_adjust/2);
        Drivetrain.getInstance().rightGroup.set(-steering_adjust/2);
        
        
}


    SmartDashboard.putNumber("Horizontal", tx);
    SmartDashboard.putNumber("Vertical:", ty);
    ty = limelightTable.getEntry("ty").getDouble(0);
    tv = limelightTable.getEntry("tv").getDouble(0);
    tx = limelightTable.getEntry("tx").getDouble(0);
  }
}
