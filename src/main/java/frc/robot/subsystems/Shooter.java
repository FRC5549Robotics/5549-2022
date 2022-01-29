// Copyright (c) FIRST 	and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.math.controller.PIDController;

import java.security.acl.Group;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Shooter extends SubsystemBase {
	/** Creates a new Shooter. */
	CANSparkMax motor1, motor2;
	SpeedControllerGroup shooterGroup;
	PIDController pid;
	boolean isOn;
	double set_point;

	public Shooter() {
		motor1 = new CANSparkMax(Constants.SHOOT_MOTOR1, MotorType.kBrushless);
		motor2 = new CANSparkMax(Constants.SHOOT_MOTOR2, MotorType.kBrushless);
		shooterGroup = new SpeedControllerGroup(motor1, motor2);
		isOn = false;
	}

	public static double getSpeed(double distance) {
		// Add implementation
		return 0.0;
	}

	public void on() {
		on(0); // Fill in logic here
	}

	public void on(double sp) {
		isOn = true;
		set_point = sp;
	}

	public void stop() {
		isOn = false;
	}

	@Override
	public void periodic() {
		if (isOn) {
			if (pid == null)
				pid = new PIDController(Constants.kP, Constants.kI, Constants.kD);
			shooterGroup.set(pid.calculate(motor1.getEncoder().getVelocity(), set_point));
		} else
			shooterGroup.set(0);
	}
}
