// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class ShootLow extends CommandBase {
  /** Creates a new ShootLow. */
  Shooter m_shooter;
  private double m_time;
	private double m_maxTime = 1.0;
	private double m_startTime;
  XboxController xboxTrigger;

  public ShootLow(Shooter shooter, XboxController xbox) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = shooter;
    xboxTrigger = xbox;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
    m_time = 0.0;
    System.out.println("Initialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_time = (System.currentTimeMillis() - m_startTime) / 1000;
    if ((m_time >= 0.0) && (m_time < m_maxTime)){
      xboxTrigger.setRumble(RumbleType.kLeftRumble, 0);
      xboxTrigger.setRumble(RumbleType.kRightRumble, 0);
    }
    else{
      xboxTrigger.setRumble(RumbleType.kLeftRumble, 1);
      xboxTrigger.setRumble(RumbleType.kRightRumble, 1);
    }
      m_shooter.ShootLow();
      System.out.println("Shoot Low");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
