// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class GetFlywheelUpToSpeed extends CommandBase {
  /** Creates a new GetFlywheelUpToSpeed. */
  Shooter m_shooter;
  private double m_time;
  private double startTime;
  boolean isShooterUpToSpeed = false;
  private double m_maxTime;
  public GetFlywheelUpToSpeed(Shooter shooter, double time) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = shooter;
    m_maxTime = time;
    addRequirements(shooter);
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
      m_shooter.autonSpeed();
    } else {
      m_shooter.autonSpeed();
      isShooterUpToSpeed = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isShooterUpToSpeed;
  }
}
