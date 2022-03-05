package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;

public class AutoMove extends CommandBase {
  /** Creates a new Auto. */
  private final Drivetrain m_drivetrain;
  private double m_time;
  private double startTime;
  boolean myAutoFinished = false;
  private double m_maxTime;
  private double direction;

  public AutoMove(Drivetrain drivetrain, double time, double direction1) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    m_maxTime = time;
    direction = direction1;
    addRequirements(drivetrain);
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
    if ((m_time >= 0.0) && (m_time < m_maxTime)) {
      m_drivetrain.autoDrive(-Constants.DRIVE_AUTO_SPEED * direction, -Constants.DRIVE_AUTO_SPEED * direction);
    } 
    if ((m_time >= m_maxTime)){
      myAutoFinished = true;
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