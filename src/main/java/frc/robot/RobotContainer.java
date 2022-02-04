// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Limelight;
import frc.robot.commands.TankDrive;
import frc.robot.commands.TurnFlywheelOff;
import frc.robot.commands.GetFlywheelUpToSpeed;
import frc.robot.commands.IndexerRunForSpecificTime;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.ClimberDown;
import frc.robot.commands.ClimberStop;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final Drivetrain drivetrain = new Drivetrain();
  public final Shooter shooter = new Shooter();
  public final Intake intake = new Intake();
  public final Climber climber = new Climber();
  public final Limelight limelight = new Limelight();
  public final Indexer indexer = new Indexer();

  public static Joystick joystickLeft = new Joystick(Constants.JOYSTICK_LEFT);
  public static Joystick joystickRight = new Joystick(Constants.JOYSTICK_RIGHT);
  JoystickButton shootButton = new JoystickButton(joystickRight, Constants.SHOOT_BUTTON);
  public XboxController xbox = new XboxController(Constants.XBOX_CONTROLLER);
  JoystickButton intakeButton = new JoystickButton(xbox, Constants.INTAKE_BUTTON);
  JoystickButton climberButton = new JoystickButton(xbox, Constants.CLIMBER_BUTTON);
  JoystickButton climberButtonDown = new JoystickButton(xbox, Constants.CLIMBER_BUTTON2);
  JoystickButton indexerButton = new JoystickButton(xbox, Constants.INDEXER_BUTTON);
  JoystickButton indexerButton2 = new JoystickButton(xbox, Constants.INDEXER_BUTTON2);
  JoystickButton changeGear = new JoystickButton(xbox, Constants.CHANGE_GEAR_BUTTON);


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
    drivetrain.setDefaultCommand(new TankDrive(drivetrain, joystickLeft, joystickRight));
    shootButton.whenPressed(new SequentialCommandGroup(new TurnToAngle(limelight, drivetrain), 
    new GetFlywheelUpToSpeed(shooter), new IndexerRunForSpecificTime(indexer, 2), new TurnFlywheelOff(shooter)));
    intakeButton.whenPressed(new InstantCommand(intake::intake_bottom));
    intakeButton.whenReleased(new InstantCommand(intake::intake_stop));
    indexerButton.whenPressed(new InstantCommand(indexer::indexer_up));
    indexerButton2.whenPressed(new InstantCommand(indexer::indexer_back));
    indexerButton.whenReleased(new InstantCommand(indexer::indexer_stop));
    indexerButton2.whenReleased(new InstantCommand(indexer::indexer_stop));
    climberButton.whenPressed(new ClimberUp(climber));
    climberButton.whenReleased(new ClimberStop(climber));
    climberButtonDown.whenPressed(new ClimberDown(climber));
    climberButtonDown.whenReleased(new ClimberDown(climber));
    changeGear.whenPressed(new InstantCommand(drivetrain::changeGear));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
