// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  CANSparkMax leftFront, rightFront, leftBack, rightBack;
  MotorControllerGroup leftGroup, rightGroup;
  DifferentialDrive drive;
  Solenoid rightGearShift, leftGearShift;

  public Drivetrain() {
    leftFront =  new CANSparkMax(Constants.LEFT_MOTOR1, MotorType.kBrushless);
    leftBack = new CANSparkMax(Constants.LEFT_MOTOR2, MotorType.kBrushless);
    rightFront = new CANSparkMax(Constants.RIGHT_MOTOR1, MotorType.kBrushless);
    rightBack = new CANSparkMax(Constants.RIGHT_MOTOR2, MotorType.kBrushless);

    leftGroup = new MotorControllerGroup(leftFront, leftBack);
    rightGroup = new MotorControllerGroup(rightFront, rightBack);

    drive = new DifferentialDrive(rightGroup, leftGroup);

    rightGearShift = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.RIGHTSOLENOID);
    leftGearShift = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.LEFTSOLENOID);
  }

  public void tankDriveMethod(double leftJoystickAxis, double rightJoystickAxis) {
    double ScalingFactor = Constants.SCALING_FACTOR;
    drive.tankDrive(leftJoystickAxis * ScalingFactor, -rightJoystickAxis * ScalingFactor);
  }

  public void changeGear() {
    if(rightGearShift.get() == true){
      rightGearShift.set(false);
      leftGearShift.set(false);
    }
    else{
      rightGearShift.set(true);
      leftGearShift.set(true);
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
