// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class TankDrive extends CommandBase {
  /** Creates a new TankDrive. */
  private final Drivetrain m_drivetrain;
  private final Joystick m_axis1;
  private final Joystick m_axis2;
  private final XboxController xbox1;


  public TankDrive(Drivetrain drivetrain, Joystick joystickLeft, Joystick joystickRight, XboxController xbox) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    m_axis1 = joystickLeft;
    m_axis2 = joystickRight;
    xbox1 = xbox;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //slide = Math.abs(m_axis1.getRawAxis(3));
    if(!xbox1.getRawButton(8)){
      m_drivetrain.tankDriveMethod(m_axis2.getY(), m_axis1.getY());///, m_axis1.getRawAxis(3), m_axis2.getRawAxis(3));
    }
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
