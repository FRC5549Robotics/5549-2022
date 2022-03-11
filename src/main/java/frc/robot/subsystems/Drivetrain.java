// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

import r3.Record;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private static Drivetrain instance = null;

  CANSparkMax leftFront, rightFront, leftBack, rightBack;
  MotorControllerGroup leftGroup, rightGroup;
  DifferentialDrive drive;

  DoubleSolenoid rightGearShift, leftGearShift;

  public Drivetrain() {
    leftFront =  new CANSparkMax(Constants.LEFT_MOTOR1, MotorType.kBrushless);
    leftBack = new CANSparkMax(Constants.LEFT_MOTOR2, MotorType.kBrushless);

    rightFront = new CANSparkMax(Constants.RIGHT_MOTOR1, MotorType.kBrushless);
    rightBack = new CANSparkMax(Constants.RIGHT_MOTOR2, MotorType.kBrushless);

    leftFront.setInverted(true);
    leftBack.setInverted(true);

    leftGroup = new MotorControllerGroup(leftFront, leftBack);
    rightGroup = new MotorControllerGroup(rightFront, rightBack);

    drive = new DifferentialDrive(rightGroup, leftGroup);

    rightGearShift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.RIGHTSOLENOID_FORWARD, Constants.RIGHTSOLENOID_REVERSE);
    leftGearShift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.LEFTSOLENOID_FORWARD, Constants.LEFTSOLENOID_REVERSE);
    instance = this;
  }

  public void tankDriveMethod(double leftJoystickAxis, double rightJoystickAxis) {
    Record.recordCall(this, leftJoystickAxis, rightJoystickAxis);
    double ScalingFactor = Constants.SCALING_FACTOR;
    double leftScalingFactor = Constants.LEFT_SCALING_FACTOR;
    drive.tankDrive(leftJoystickAxis * leftScalingFactor, rightJoystickAxis * ScalingFactor);
  }

  public void arcadeDriveMethod(double limelight_angletx) {
    drive.arcadeDrive(0, -1);
  }

  public void align(Limelight l) {
    Record.recordCall(this, l);
    arcadeDriveMethod(l.getAngle());
  }

  public void changeGear() {
    rightGearShift.toggle();
    leftGearShift.toggle();
  }
  
  public void autoDrive(double speed1, double speed2) {
     drive.tankDrive(-speed1, -speed2);
   }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static Drivetrain getInstance() {
    return instance;
  }
}
