// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Drivetrain;

public class TurnToAngle extends CommandBase {
  /** Creates a new TurnToAngle. */
  Double m_angle;
  Limelight m_limelight;
  Drivetrain m_drivetrain;
  boolean isDone = false;
  public TurnToAngle(Limelight limelight, Drivetrain drivetrain) {
    m_limelight = limelight;
    m_angle = m_limelight.getAngle();
    m_drivetrain = drivetrain;
    SmartDashboard.putNumber("Test TX angle:", m_angle);
    // Use addRequirements() here to declare subsystem dependencies.
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Test2 TX angle:", m_limelight.getAngle());
    if (m_limelight.getAngle() > 5 || m_limelight.getAngle() < -5){
      m_drivetrain.arcadeDriveMethod(m_limelight.getAngle());
      SmartDashboard.putNumber("Horizontal Angle:", m_limelight.getAngle());
    } else{
      isDone = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isDone;
  }
}
