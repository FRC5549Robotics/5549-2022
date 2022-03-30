// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
  /** Creates a new Indexer. */
  WPI_TalonSRX motor_indexer_1;
  public Indexer() {
    motor_indexer_1 = new WPI_TalonSRX(Constants.INDEXER_MOTOR);
  }

  public void indexer_run(){
    motor_indexer_1.set(Constants.INDEXER_SPEED);
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
  
  public void indexer_set_speed(speed){
    motor_indexer_1.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
