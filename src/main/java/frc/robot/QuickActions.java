package frc.robot;

import frc.robot.motor.MotorController;

public class QuickActions {

    public static MotorController leftParent;
    public static MotorController rightParent;

    public static void setDriveMotors(MotorController leftParentMotor, MotorController rightParentMotor) {
        leftParent = leftParentMotor;
        rightParent = rightParentMotor;
    }

    public static void setAll(double percent) {
        leftParent.set(percent);
        rightParent.set(percent);
    }

    public static void stopAll() {
        leftParent.set(0.0);
        rightParent.set(0.0);
    }

    public static void turn(TurnDirection direction, double percent) {
        if (direction == TurnDirection.LEFT) {
            leftParent.set(-percent);
            rightParent.set(percent);
        } else if (direction == TurnDirection.RIGHT) {
            leftParent.set(percent);
            rightParent.set(-percent);
        }
    }

    public static void turnVelocity(TurnDirection direction, double percent) {
        if (direction == TurnDirection.LEFT) {
            leftParent.setVelocity(-percent);
            rightParent.setVelocity(percent);
        } else if (direction == TurnDirection.RIGHT) {
            leftParent.setVelocity(percent);
            rightParent.setVelocity(-percent);
        }
    }

    public enum TurnDirection {
        LEFT,
        RIGHT,
    }
}
