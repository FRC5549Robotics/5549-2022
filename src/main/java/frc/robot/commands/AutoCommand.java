// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import r3.Replay;

public class AutoCommand extends CommandBase {
  boolean done = false;
  public AutoCommand(Drivetrain dt, Shooter s) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    // requires(dt);
    // requires(s);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Replay.replay("/home/lvuser/recording.bin");
    done = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return done;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
}
