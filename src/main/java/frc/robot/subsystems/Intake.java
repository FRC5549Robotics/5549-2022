// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /* Intake Class */
  CANSparkMax motor_intake_1;
  
  //import/put the correct file
  public Intake() {
    /* Creates a new Intake. */
    motor_intake_1 = new CANSparkMax(Constants.MOTOR_INTAKE, MotorType.kBrushless);
   
    // turns right motor in opposite direction of left motor
    // allows for motors to rotate towards the center, intaking objects
    // or rotating outwards from the center, ejecting objects
  }

  public void intake_bottom(){
    motor_intake_1.set(Constants.INTAKE_SPEED);
  }

  public void intake_stop(){
    motor_intake_1.set(0);
  }

  @Override
  public void periodic() {
  }
}
