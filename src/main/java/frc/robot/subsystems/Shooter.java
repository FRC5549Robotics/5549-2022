// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants;

public class Shooter extends SubsystemBase {
	/** Creates a new Shooter. */
	CANSparkMax motor1, motor2;
	RelativeEncoder motor1_encoder, motor2_encoder;
	MotorControllerGroup shooterGroup;
	boolean isOn;
	SparkMaxPIDController M1pid, M2pid;
	double kP = Constants.kP;
	double kI = Constants.kI;
	double kD = Constants.kD;

	public Shooter() {
		motor1 = new CANSparkMax(Constants.SHOOT_MOTOR1, MotorType.kBrushless);
		motor2 = new CANSparkMax(Constants.SHOOT_MOTOR2, MotorType.kBrushless);

		M1pid = motor1.getPIDController();
		M2pid = motor2.getPIDController();

		motor1_encoder = motor1.getEncoder();
		motor2_encoder = motor2.getEncoder();

		//setting PID values for motors
		M1pid.setP(Constants.kP);
		M1pid.setI(Constants.kI);
		M1pid.setD(Constants.kD);

		SmartDashboard.putNumber("P1 Gain", Constants.kP);
		SmartDashboard.putNumber("I1 Gain", Constants.kI);
		SmartDashboard.putNumber("D1 Gain", Constants.kD);

		double p = SmartDashboard.getNumber("P Gain", 0);
		double i = SmartDashboard.getNumber("I Gain", 0);
		double d = SmartDashboard.getNumber("D Gain", 0);

		if((p != Constants.kP)) { M1pid.setP(p); kP = p; }
		if((i != Constants.kI)) { M1pid.setI(i); kI = i; }
		if((d != Constants.kD)) { M1pid.setD(d); kD = d; }
	
    SmartDashboard.putNumber("motor 1 velocity", motor1_encoder.getVelocity());
	SmartDashboard.putNumber("motor 1 velocity", motor1_encoder.getVelocity());
	}

	public static double getSpeed(double distance) {
		// Add implementation
		return 0.0;
	}

	public void off(){
		shooterGroup.set(0);
	}

	public void on() {
		double setPoint = Limelight.getDesiredRPM();
		SmartDashboard.putNumber("SetPoint", setPoint);
		M1pid.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
		M2pid.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
	}

	public double getCurrentRPM() {
    double count = motor1.getEncoder().getCountsPerRevolution()/4;
	double currentRPM = (count/4096) * (60);
	return currentRPM;
	}
	
	@Override
	public void periodic(){}
}
