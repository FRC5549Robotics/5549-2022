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

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  CANSparkMax leftFront, rightFront, leftBack, rightBack;
  MotorControllerGroup leftGroup, rightGroup;
  DifferentialDrive drive;

  DoubleSolenoid rightGearShift, leftGearShift;

  public Drivetrain() {
    leftFront =  new CANSparkMax(Constants.LEFT_MOTOR1, MotorType.kBrushless);
    leftBack = new CANSparkMax(Constants.LEFT_MOTOR2, MotorType.kBrushless);
    rightFront = new CANSparkMax(Constants.RIGHT_MOTOR1, MotorType.kBrushless);
    rightBack = new CANSparkMax(Constants.RIGHT_MOTOR2, MotorType.kBrushless);

    leftGroup = new MotorControllerGroup(leftFront, leftBack);
    rightGroup = new MotorControllerGroup(rightFront, rightBack);

    drive = new DifferentialDrive(rightGroup, leftGroup);


    rightGearShift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.RIGHTSOLENOID ,2);
    leftGearShift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.LEFTSOLENOID, 2);

  }

  public void tankDriveMethod(double leftJoystickAxis, double rightJoystickAxis) {
    double ScalingFactor = Constants.SCALING_FACTOR;
    drive.tankDrive(leftJoystickAxis * ScalingFactor, -rightJoystickAxis * ScalingFactor);
  }

  public void arcadeDriveMethod(double limelight_angletx) {
    drive.arcadeDrive(0, limelight_angletx*Constants.tP);
  }

  public void changeGear() {
    rightGearShift.toggle();
    leftGearShift.toggle();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
