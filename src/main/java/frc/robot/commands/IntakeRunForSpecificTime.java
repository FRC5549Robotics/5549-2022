package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeRunForSpecificTime extends CommandBase {
  /** Creates a new Auto. */
  private final Drivetrain m_drivetrain;
  private double m_time;
  private double startTime;
  boolean myAutoFinished = false;
  private double m_maxTime;
  private double direction;
  private final Intake m_intake;

  public IntakeRunForSpecificTime(Drivetrain drivetrain, double time, double direction1, Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    m_intake = intake;
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
      m_intake.intake_run_during_auto();
    } 
    if ((m_time >= m_maxTime)){
      m_intake.intake_stop();
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