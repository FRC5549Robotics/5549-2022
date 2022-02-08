// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
  /** Creates a new Indexer. */
  CANSparkMax motor_indexer_1;
  public Indexer() {
    motor_indexer_1 = new CANSparkMax(Constants.INDEXER_MOTOR_1, MotorType.kBrushed);
  }

  public void indexer_run(double speed){
    motor_indexer_1.set(speed);
  }

  public void indexer_up(){
    motor_indexer_1.set(Constants.INDEXER_SPEED);
  }

  public void indexer_back(){
    motor_indexer_1.set(-Constants.INDEXER_SPEED);
  }

  public void indexer_stop(){
    motor_indexer_1.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
