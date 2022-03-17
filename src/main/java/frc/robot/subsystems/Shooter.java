// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.SparkMaxPIDController;

import frc.robot.Constants;

import r3.Record;

public class Shooter extends SubsystemBase {
	private static Shooter instance = null;
	/** Creates a new Shooter. */
	CANSparkMax motor1, motor2;
	RelativeEncoder motor1_encoder, motor2_encoder;
	MotorControllerGroup shooterGroup;
	boolean isOn;
	PIDController pid = new PIDController(Constants.kP, Constants.kI, Constants.kD);
	SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Constants.kS, Constants.kV, Constants.kA);
	XboxController xboxTrigger;
	private double m_time;
	private double m_maxTime;
	private double m_startTime;

	public Shooter(XboxController xbox) {
		motor1 = new CANSparkMax(Constants.SHOOT_MOTOR1, MotorType.kBrushless);
		motor2 = new CANSparkMax(Constants.SHOOT_MOTOR2, MotorType.kBrushless);

		shooterGroup = new MotorControllerGroup(motor1, motor2);

		// M1pid = motor1.getPIDController();
		// M2pid = motor2.getPIDController();

		motor1_encoder = motor1.getEncoder();
		motor2_encoder = motor2.getEncoder();


		SmartDashboard.putNumber("P1 Gain", Constants.kP);
		SmartDashboard.putNumber("I1 Gain", Constants.kI);
		SmartDashboard.putNumber("D1 Gain", Constants.kD);
		xboxTrigger = xbox;
	
    SmartDashboard.putNumber("motor 1 velocity", motor1_encoder.getVelocity());
	SmartDashboard.putNumber("motor 2 velocity", motor2_encoder.getVelocity());
	instance = this;
	m_startTime = System.currentTimeMillis();
    m_time = 0.0;
	m_maxTime = 1.5;
	}

	public static double getSpeed(double distance) {
		// Add implementation
		return 0.0;
	}

	public void autonSpeed(){
		shooterGroup.set(Constants.SHOOTER_AUTON_SPEED);
	}
	public void runShooter(java.lang.Double speed){
		Record.recordCall(this, speed);
		shooterGroup.set(speed);
	}

	public void off(){
		shooterGroup.set(0);
		xboxTrigger.setRumble(RumbleType.kLeftRumble, 0);
		xboxTrigger.setRumble(RumbleType.kRightRumble, 0);
	}

	public void on(double setPoint) {
		Record.recordCall(this);
		SmartDashboard.putNumber("SetPoint", setPoint);
		motor1.setVoltage(pid.calculate(motor1_encoder.getVelocity(), setPoint) + feedforward.calculate(setPoint));
		motor2.setVoltage(pid.calculate(motor2_encoder.getVelocity(), setPoint) + feedforward.calculate(setPoint));
		// M1pid.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
		// M2pid.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
		SmartDashboard.putNumber("RPM", motor1_encoder.getVelocity());
		SmartDashboard.putNumber("RPM2", motor2_encoder.getVelocity());
	}
	
	@Override
	public void periodic(){
		m_time = (System.currentTimeMillis() - m_startTime) / 1000;
		if(xboxTrigger.getRawAxis(2) > 0.1) {
    		runShooter(xboxTrigger.getRawAxis(2));
    	} else if(xboxTrigger.getRawAxis(3) > 0.2){
			runShooter(xboxTrigger.getRawAxis(3)/3.5);
		} else {
			off();
		}

		if(xboxTrigger.getRawAxis(2) > 0.1 || xboxTrigger.getRawAxis(3) > 0.1)
		{
			xboxTrigger.setRumble(RumbleType.kLeftRumble, 1);
			xboxTrigger.setRumble(RumbleType.kRightRumble, 1);
		}
	}

	public Shooter getInstance() {
		return instance;
	}
}
