// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /* Intake Class */
  WPI_TalonSRX motor_intake_1;
  
  
  //import/put the correct file
  public Intake() {
    /* Creates a new Intake. */
    motor_intake_1 = new WPI_TalonSRX(Constants.MOTOR_INTAKE);
   
    // turns right motor in opposite direction of left motor
    // allows for motors to rotate towards the center, intaking objects
    // or rotating outwards from the center, ejecting objects
  }

  public void intake_up(){

    motor_intake_1.set(Constants.INTAKE_SPEED);
  }

  public void intake_bottom(){
    motor_intake_1.set(-Constants.INTAKE_SPEED);
  }

  public void intake_stop(){
    motor_intake_1.set(0);
  }

  public void intake_auto(){
    motor_intake_1.set(-Constants.INTAKE_AUTON_SPEED);
  }
  
  public void intake_set_speed(double speed){
    motor_intake_1.set(speed);
  }

  @Override
  public void periodic() {
  }
}
