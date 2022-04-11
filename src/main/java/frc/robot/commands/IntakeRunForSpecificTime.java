package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Limelight;

public class IntakeRunForSpecificTime extends CommandBase {
  /** Creates a new Auto. */
  private final Drivetrain m_drivetrain;
  private double m_time;
  private double startTime;
  boolean myAutoFinished = false;
  private double m_maxTime;
  private double direction;
  private Indexer m_indexer;
  private Shooter m_shooter;
  private double shootSpeed;
  private Limelight m_limelight;
  private final Intake m_intake;

  public IntakeRunForSpecificTime(Drivetrain drivetrain, double time, double direction1, Intake intake, Indexer indexer, Limelight limelight, Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    m_intake = intake;
    m_maxTime = time;
    m_limelight = limelight;
    m_indexer = indexer;
    m_shooter = shooter;
    direction = direction1;
    addRequirements(drivetrain);
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
    m_shooter.on(shootSpeed+1.3);
    if ((m_time >= 1) && (m_time < 2)) {
      m_drivetrain.autoDrive(-Constants.DRIVE_AUTO_SPEED * direction, -Constants.DRIVE_AUTO_SPEED * direction);
    } 
    if ((m_time >=2) && (m_time < 4)){
      m_intake.intake_auto();
      m_indexer.indexer_up();
    }
    if(m_time >= 4 && m_time < m_maxTime)
    {
      m_drivetrain.autoDrive(-Constants.DRIVE_AUTO_SPEED * direction, -Constants.DRIVE_AUTO_SPEED * direction);
      m_intake.intake_bottom();
      m_indexer.indexer_stop();
    }
    if ((m_time >= m_maxTime)){
      m_intake.intake_stop();
      myAutoFinished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.off();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return myAutoFinished;
  }
}