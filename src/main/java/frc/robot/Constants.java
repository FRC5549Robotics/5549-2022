// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int RIGHT_MOTOR1 = 6;
    public static final int RIGHT_MOTOR2 = 7;
    public static final int LEFT_MOTOR1 = 1;
    public static final int LEFT_MOTOR2 = 2;
    public static final double SCALING_FACTOR = 0.75;
    public static final double LEFT_SCALING_FACTOR = 0.65;

    public static final int SHOOT_MOTOR1 = 8;
    public static final int SHOOT_MOTOR2 = 9;
    public static final int SHOOT_BUTTON = 4;
    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double tP = 1/27;

    public static final int JOYSTICK_LEFT = 1;
    public static final int JOYSTICK_RIGHT = 2;

    public static final int RIGHTSOLENOID_FORWARD = 0;
    public static final int LEFTSOLENOID_FORWARD = 1;
    public static final int RIGHTSOLENOID_REVERSE = 2;
    public static final int LEFTSOLENOID_REVERSE = 3;

    public static final int MOTOR_INTAKE = 11;
    public static final double INTAKE_SPEED = 0.525;
    public static final int INTAKE_BUTTON = 3;

    public static final double CLIMBER_SPEED = 0.5;
    public static final int CLIMBER_MOTOR_1 = 3;
    public static final int CLIMBER_MOTOR_2 = 4;
    public static final int CLIMBER_BUTTON = 6;
    public static final int CLIMBER_BUTTON2 = 5;

    public static final int ANGLE_CAMERA = 45;
    public static final double HEIGHT_TARGET = 101.625;
    public static final int HEIGHT_CAMERA = 38;

    public static final int INDEXER_MOTOR = 5;
    public static final double INDEXER_SPEED = 1;//0.9 normally
    public static final int INDEXER_BUTTON = 2;
    public static final int INDEXER_BUTTON2 = 1;
    public static final int INDEXER_PERIOD = 1;

    public static final double DRIVE_AUTO_SPEED = 0.3;
    public static final double BACK_TIME = 3;
    public static final double SHOOT_TIME = 6;
    public static final int CHANGE_GEAR_BUTTON = 1;
    public static final int XBOX_CONTROLLER = 0;
}
