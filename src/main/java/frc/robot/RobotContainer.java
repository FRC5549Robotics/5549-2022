// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Limelight;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import r3.Record;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public XboxController xbox = new XboxController(Constants.XBOX_CONTROLLER);
  public static Joystick joystickLeft = new Joystick(Constants.JOYSTICK_LEFT);
  public static Joystick joystickRight = new Joystick(Constants.JOYSTICK_RIGHT);
  // The robot's subsystems and commands are defined here...
  public final Drivetrain drivetrain = new Drivetrain();
  public final Intake intake = new Intake();
  public final Limelight limelight = new Limelight(xbox, Constants.tP);
  public final Indexer indexer = new Indexer();
  public final Shooter shooter = new Shooter(xbox, joystickRight, limelight);
  public final Climber climber = new Climber(xbox);

  //JoystickButton shootButton = new JoystickButton(xbox, Constants.SHOOT_BUTTON);
  JoystickButton intakeButton = new JoystickButton(xbox, Constants.INTAKE_BUTTON);
  JoystickButton intakeButton2 = new JoystickButton(xbox, Constants.SHOOT_BUTTON);
  JoystickButton indexerButton = new JoystickButton(xbox, Constants.INDEXER_BUTTON2);
  JoystickButton indexerButton2 = new JoystickButton(xbox, Constants.INDEXER_BUTTON);
  JoystickButton changeGear = new JoystickButton(joystickRight, Constants.CHANGE_GEAR_BUTTON);
  //JoystickButton turnButton = new JoystickButton(xbox, 8);
  JoystickButton recordButton = new JoystickButton(joystickRight, 8);
  JoystickButton PIDButton = new JoystickButton(xbox, Constants.PID_SHOOTER);
  Trigger highShoot = new JoystickButton(xbox, 2);
  Trigger lowShoot = new JoystickButton(xbox, 3);
  
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    drivetrain.setDefaultCommand(new TankDrive(drivetrain, joystickLeft, joystickRight,xbox));
    //shootButton.whenPressed(new SequentialCommandGroup(new TurnToAngle(limelight, drivetrain), 
    //new InstantCommand(shooter::runAtMax), new IndexerRunForSpecificTime(indexer, intake, 2), new InstantCommand(shooter::off)));
    intakeButton.whenPressed(new InstantCommand(intake::intake_up));
    intakeButton2.whenPressed(new InstantCommand(intake::intake_bottom));
    intakeButton.whenReleased(new InstantCommand(intake::intake_stop));
    intakeButton2.whenReleased(new InstantCommand(intake::intake_stop));
    indexerButton.whenPressed(new InstantCommand(indexer::indexer_up));
    indexerButton2.whenPressed(new InstantCommand(indexer::indexer_back));
    indexerButton.whenReleased(new InstantCommand(indexer::indexer_stop));
    indexerButton2.whenReleased(new InstantCommand(indexer::indexer_stop));
    changeGear.whenPressed(new InstantCommand(drivetrain::changeGear));
    //turnButton.whenPressed(new TurnToAngle(limelight, drivetrain));
    recordButton.whenPressed(new InstantCommand(Record::toggle));
    PIDButton.whenHeld(new PIDShooter(shooter, limelight, xbox));
  }
    

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return new InstantCommand(() -> Replay.replay("recording.bin"));
    //return new AutoCommand(drivetrain, shooter);
    return new SequentialCommandGroup(
     //new AutoMove(drivetrain, Constants.BACK_TIME1, -1),
     new IntakeRunForSpecificTime(drivetrain, Constants.BACK_TIME1, -1, intake),
     new AutoMove(drivetrain, Constants.BACK_TIME, 1),
     
     // new TurnToAngle(limelight, drivetrain),
      //new GetFlywheelUpToSpeed(shooter, 2),
      new IndexerRunForSpecificTime(indexer, intake, Constants.SHOOT_TIME, shooter)
      //new TurnFlywheelOff(shooter)
     );

  }
}
