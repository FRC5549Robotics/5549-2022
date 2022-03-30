// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class IndexerRunForSpecificTime extends CommandBase {
  /** Creates a new IndexerRunForSpecificTime. */
  private final Indexer m_indexer;
  private final Intake m_intake;
  private final Limelight m_limelight;
  private double m_time;
  private double startTime;
  boolean isIndexerDone = false;
  private double m_maxTime;
  private final Shooter m_shooter;
  private double shootSpeed;
  public IndexerRunForSpecificTime(Indexer indexer, Intake intake, double time, Shooter shooter, Limelight limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_indexer = indexer;
    m_maxTime = time;
    m_intake = intake;
    m_shooter = shooter;
    m_limelight = limelight;
    addRequirements(indexer);
    addRequirements(intake);
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
    m_time = 0.0;
    shootSpeed = m_limelight.getDesiredRPM();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_time = (System.currentTimeMillis() - startTime) / 1000;
    m_shooter.on(shootSpeed-1.5);
    if ((m_time >= 4.5) && (m_time < m_maxTime)){
      m_indexer.indexer_up();
      m_intake.intake_auto();
    }
    if (m_time >= m_maxTime){
      isIndexerDone = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.off();
    m_indexer.indexer_stop();
    m_intake.intake_stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isIndexerDone;
  }
}
