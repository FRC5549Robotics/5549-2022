// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;


public class PIDShooter extends CommandBase {
  /** Creates a new PIDShooter. */
  Shooter m_shooter;
  Double shootSpeed;
  Limelight m_limelight;
  private double m_time;
  private double startTime;
  XboxController xboxTrigger;
  public PIDShooter(Shooter shooter, Limelight limelight, XboxController xbox) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = shooter;
    m_limelight = limelight;
    xboxTrigger = xbox;
  } 

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shootSpeed = m_limelight.getDesiredRPM();
    
    if( shootSpeed == 0){
      System.out.println("LimelightValue: false");
    }
    else{
      System.out.println("LimelightValue: true");
    }
    startTime = System.currentTimeMillis();
    m_time = 0.0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (shootSpeed == 24.18 - 2.9) {
	    xboxTrigger.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
	    xboxTrigger.setRumble(GenericHID.RumbleType.kRightRumble, 1);
    } else {
    //m_time = (System.currentTimeMillis() - startTime) / 1000;
    m_shooter.on(shootSpeed);
    }    
    //if (m_time > 1.5) 
    //{
      //xboxTrigger.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
			//xboxTrigger.setRumble(GenericHID.RumbleType.kRightRumble, 1);
    //}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    xboxTrigger.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
		xboxTrigger.setRumble(GenericHID.RumbleType.kRightRumble, 0);
    m_shooter.off();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
