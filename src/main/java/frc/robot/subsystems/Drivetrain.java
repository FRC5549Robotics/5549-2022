// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.Joystick;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;

import r3.Record;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private static Drivetrain instance = null;

  CANSparkMax leftFront, rightFront, leftBack, rightBack;
  RelativeEncoder leftFrontE, leftBackE, rightFrontE, rightBackE;
  public MotorControllerGroup leftGroup, rightGroup;
  DifferentialDrive drive;
  Compressor pcmCompressor;
  AHRS m_NavXMXP;
  DifferentialDriveOdometry m_odometry;
  Joystick left, right;

  DoubleSolenoid rightGearShift, leftGearShift;

  public Drivetrain(Joystick left, Joystick right) {
    leftFront =  new CANSparkMax(Constants.LEFT_MOTOR1, MotorType.kBrushless);
    leftBack = new CANSparkMax(Constants.LEFT_MOTOR2, MotorType.kBrushless);
    leftBackE = leftBack.getEncoder();
    leftFrontE = leftFront.getEncoder();

    rightFront = new CANSparkMax(Constants.RIGHT_MOTOR1, MotorType.kBrushless);
    rightBack = new CANSparkMax(Constants.RIGHT_MOTOR2, MotorType.kBrushless);
    rightBackE = rightBack.getEncoder();
    rightFrontE = rightFront.getEncoder();

    leftFront.setInverted(true);
    leftBack.setInverted(true);

    m_NavXMXP = new AHRS(SerialPort.Port.kMXP);

    leftGroup = new MotorControllerGroup(leftFront, leftBack);
    rightGroup = new MotorControllerGroup(rightFront, rightBack);

    drive = new DifferentialDrive(rightGroup, leftGroup);

    rightGearShift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.RIGHTSOLENOID_FORWARD, Constants.RIGHTSOLENOID_REVERSE);
    leftGearShift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.LEFTSOLENOID_FORWARD, Constants.LEFTSOLENOID_REVERSE);
    rightGearShift.set(Value.kForward);
    leftGearShift.set(Value.kForward);
    instance = this;

    pcmCompressor = new Compressor(PneumaticsModuleType.CTREPCM);

    resetEncoders();
    m_odometry = new DifferentialDriveOdometry(m_NavXMXP.getRotation2d());
    rightBackE.setPositionConversionFactor(Constants.POSITION_TO_METERS);
    rightFrontE.setPositionConversionFactor(Constants.POSITION_TO_METERS);
    leftFrontE.setPositionConversionFactor(Constants.POSITION_TO_METERS);
    leftBackE.setPositionConversionFactor(Constants.POSITION_TO_METERS);
    // getPosition now returns distance.
  }

  public void resetEncoders() {
    rightBackE.setPosition(0);
    rightFrontE.setPosition(0);
    leftBackE.setPosition(0);
    leftFrontE.setPosition(0);
  }

  public void tankDriveMethod(double leftJoystickAxis, double rightJoystickAxis) {
    Record.recordCall(this, leftJoystickAxis, rightJoystickAxis);
    drive.tankDrive(leftJoystickAxis, rightJoystickAxis);
    // drive.tankDrive(leftJoystickAxis * left.getRawAxis(3), rightJoystickAxis * right.getRawAxis(3));
  }

  public void arcadeDriveMethod(double limelight_angletx) {
    drive.arcadeDrive(0, -1);
  }

  public void align(Limelight l) {
    Record.recordCall(this, l);
    arcadeDriveMethod(l.getAngle());
  }

  public void changeGear() {
    rightGearShift.toggle();
    leftGearShift.toggle();
  }
  
  public void autoDrive(double speed1, double speed2) {
     drive.tankDrive(-speed1 * 0.75, -speed2 * 0.83);
   }
  
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    double rightV = ((rightFrontE.getVelocity() + leftBackE.getVelocity())/2);
    double leftV = ((leftFrontE.getVelocity()+leftBackE.getVelocity())/2);
    return new DifferentialDriveWheelSpeeds((rightV * Constants.ROTATION_TO_METERS),(leftV * Constants.ROTATION_TO_METERS));
  }

  // public static double RPMToDistance(RelativeEncoder encoder)
  // {
  //   encoder.
  //   return 0;
  // }

  public double getHeading(){
    return m_NavXMXP.getRotation2d().getDegrees();
  }

  public Pose2d getPose(){
    return m_odometry.getPoseMeters();
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, m_NavXMXP.getRotation2d());
  }

  public void tankDriveVolts(double leftVolts, double rightVolts){
    leftGroup.setVoltage(leftVolts);
    rightGroup.setVoltage(rightVolts);
    drive.feed();
  }

  public double getAverageEncoderDistance() {
    double leftd = (leftBackE.getPosition() + leftFrontE.getPosition()) / 2.0;
    double rightd = (rightBackE.getPosition() + rightFrontE.getPosition()) / 2.0;
    return (leftd + rightd) / 2.0;
  }

  // public RelativeEncoder getLeftEncoder() {
  //   return;    // What should this return?
  // }

  public void setMaxOutput(double maxOutput) {
    drive.setMaxOutput(maxOutput);
  }

  public void zeroHeading() {
    m_NavXMXP.reset();
  }

  public double getTurnRate() {
    return -m_NavXMXP.getRate();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("LeftFront", leftFrontE.getVelocity());
    SmartDashboard.putNumber("LeftBack", leftBackE.getVelocity());
    SmartDashboard.putNumber("RightFront", rightFrontE.getVelocity());
    SmartDashboard.putNumber("RightBack", rightBackE.getVelocity());
    
    // pcmCompressor.getPressure();
    m_odometry.update(
      m_NavXMXP.getRotation2d(), 
      (rightFrontE.getPosition() + rightBackE.getPosition()) /2.0, 
      (leftFrontE.getPosition() + leftBackE.getPosition()) / 2.0
    );
  }

  public static Drivetrain getInstance() {
    return instance;
  }
}
