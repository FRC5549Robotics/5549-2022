package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;

public class AutoMove extends CommandBase {
    Drivetrain m_drivetrain;
    double max_time;
    boolean done;
    
    AutoMove(Drivetrain dt, double mt) {
        m_drivetrain = dt;
        max_time = mt;
        done = false;
    }

    @Override
    public void initialize() { Timer.start(); }
    @Override
    public void execute() {
        while (!Timer.hasPeriodPassed(max_time))
            m_drivetrain.autoDrive(Constants.DRIVE_AUTO_SPEED, Constants.DRIVE_AUTO_SPEED);
    }
    @Override
    public void end() { done = true; }
    @Override
    public boolean isFinished() { return done; }
}
