// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// SHREY, YOUR CODE IS BAD. READ THE API DOCS.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;


public class Auto extends CommandBase {
  /** Creates a new Auto. */
  Drivetrain m_drivetrain;
  Limelight m_limelight;
  Shooter m_shooter;
  Indexer m_indexer;
  private double m_time;
  private double startTime;
  boolean myAutoFinished = false;
  private double m_maxTime_drive;
  private double m_maxTime_index;
  Double m_angle2;

  public Auto(Drivetrain drivetrain, Limelight limelight, double timeDrive, double timeIndedxer, Shooter shooter, Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    m_limelight = limelight;
    m_maxTime_drive = timeDrive;
    m_maxTime_index = timeIndedxer;
    m_shooter = shooter;
    m_indexer = indexer;
    m_angle2 = m_limelight.getAngle();
    addRequirements(drivetrain, limelight);
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
    if ((m_time >= 0.0) && (m_time < m_maxTime_drive)) {
      // Constants are made positive because robot is supposed to go backwards. Since autoDrive already makes the values
      // negative, negatives here would cancel it out.
      m_drivetrain.autoDrive(Constants.DRIVE_AUTO_SPEED, Constants.DRIVE_AUTO_SPEED);
    }
    if ((m_time >= m_maxTime_drive)){
      if (m_limelight.getAngle() > 2 || m_limelight.getAngle() < -2) {
        m_drivetrain.arcadeDriveMethod(m_angle2);
      }
      else{
        m_shooter.on();
        startTime = System.currentTimeMillis();
        m_time = (System.currentTimeMillis() - startTime) / 1000;
        if ((m_time >= 0.0) && (m_time < m_maxTime_index)){
          m_indexer.indexer_run(Constants.INDEXER_SPEED);
        }
        if ((m_time >= m_maxTime_index)){
          myAutoFinished = true;
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return myAutoFinished;
  }
}
