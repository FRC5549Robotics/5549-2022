// Copyright (c) FIRST 	and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.math.controller.PIDController;

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

	public Shooter() {
		motor1 = new CANSparkMax(Constants.SHOOT_MOTOR1, MotorType.kBrushless);
		motor2 = new CANSparkMax(Constants.SHOOT_MOTOR2, MotorType.kBrushless);

		M1pid = motor1.getPIDController();
		M2pid = motor2.getPIDController();

		motor1_encoder = motor1.getEncoder();
		motor2_encoder = motor2.getEncoder();
		shooterGroup = new MotorControllerGroup(motor1, motor2);

		//setting PID values for motors
		M1pid.setP(Constants.kP);
		M1pid.setI(Constants.kI);
		M1pid.setD(Constants.kD);
		M2pid.setP(Constants.kP);
		M2pid.setI(Constants.kI);
		M2pid.setD(Constants.kD);
	}

	public static double getSpeed(double distance) {
		// Add implementation
		return 0.0;
	}

	public void off(){
		shooterGroup.set(0);
	}

	public void on() {
		double speed = Limelight.getDesiredRPM();
		pid = new PIDController(Constants.kP, Constants.kI, Constants.kD);
		shooterGroup.set(pid.calculate(getCurrentRPM(),speed));
	}

	public double getCurrentRPM() {
    double count = motor1.getEncoder().getCountsPerRevolution()/4;
	double currentRPM = (count/4096) * (60);
	return currentRPM;
	}
	
	@Override
	public void periodic(){}
}
