// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class PIDIntake extends CommandBase {
  /** Creates a new PIDIntake. */
  Intake m_intake;
  Indexer m_indexer;
  public PIDIntake(Intake intake, Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake = intake;
    m_indexer = indexer;
    addRequirements(intake);
    addRequirements(indexer);
  } 

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.intake_set_speed(-0.8);
    m_indexer.indexer_set_speed(0.8);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.intake_stop();
    m_indexer.indexer_stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
