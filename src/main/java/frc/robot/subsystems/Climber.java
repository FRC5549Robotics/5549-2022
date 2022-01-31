// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {
  CANSparkMax climber_motor_L, climber_motor_R;
  MotorControllerGroup climber_motor_group;

  public XboxController xboxTrigger = new XboxController(0);
  /** Creates a new Climber. */
  public Climber() {
    climber_motor_L = new CANSparkMax(Constants.CLIMBER_MOTOR_1, MotorType.kBrushless);
    climber_motor_R = new CANSparkMax(Constants.CLIMBER_MOTOR_2, MotorType.kBrushless);
    climber_motor_L.setInverted(true);
    // turns right motor in opposite direction of left motor
    // allows for motors to rotate towards the center, intaking objects
    // or rotating outwards from the center, ejecting objects
    climber_motor_group = new MotorControllerGroup(climber_motor_L, climber_motor_R);
  }
  
  // Called just before this Command runs the first time
  public void up() {
    climber_motor_group.set(Constants.CLIMBER_SPEED);
  }
  // Called repeatedly when this Command is scheduled to run
  public void down() {
    climber_motor_group.set(-Constants.CLIMBER_SPEED);
  }
  public void stop() {
    climber_motor_group.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
