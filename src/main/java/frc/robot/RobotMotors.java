package frc.robot;

import frc.robot.motor.MotorController;

public class RobotMotors {

    public static void setupMotors(MotorController leftMotor, MotorController rightMotor) {
        leftMotor.setInverted(true);
        rightMotor.setInverted(false);
    }
}
