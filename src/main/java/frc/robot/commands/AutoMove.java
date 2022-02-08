package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;

public class AutoMove extends CommandBase {
    Drivetrain m_drivetrain;
    double max_time;
    boolean done;
    Timer t;
    
    public AutoMove(Drivetrain dt, double mt) {
        m_drivetrain = dt;
        max_time = mt;
        done = false;
        t = new Timer();
    }

    @Override
    public void initialize() { 
        t.start(); 
    }
    @Override
    public void execute() {
        if (!t.advanceIfElapsed(max_time)){
            m_drivetrain.autoDrive(Constants.DRIVE_AUTO_SPEED, Constants.DRIVE_AUTO_SPEED);
        }
    }
    @Override
    public void end(boolean interrupted) { 
        done = true; 
    }
    @Override
    public boolean isFinished() { 
        return done; 
    }
}
