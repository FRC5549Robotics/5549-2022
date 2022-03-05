// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class IndexerRunForSpecificTime extends CommandBase {
  /** Creates a new IndexerRunForSpecificTime. */
  private final Indexer m_indexer;
  private final Intake m_intake;
  private double m_time;
  private double startTime;
  boolean isIndexerDone = false;
  private double m_maxTime;
  public IndexerRunForSpecificTime(Indexer indexer, Intake intake, double time) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_indexer = indexer;
    m_maxTime = time;
    m_intake = intake;
    addRequirements(indexer);
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
    m_time = 0.0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_time = (System.currentTimeMillis() - startTime) / 1000;
    if ((m_time >= 0.0) && (m_time < m_maxTime)){
      m_indexer.indexer_run();
      m_intake.intake_bottom();
    }
    if ((m_time >= m_maxTime)){
      m_indexer.indexer_stop();
      m_intake.intake_stop();
      isIndexerDone = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isIndexerDone;
  }
}
