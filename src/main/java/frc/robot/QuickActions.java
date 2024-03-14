package frc.robot;

public class QuickActions {

    public static void setDriveMotors(double percent) {
        setLeft(percent);
        setRight(percent);
    }

    public static void stopDriveMotors() {
        Robot.motors.getDriveLeftParent().set(0);
        Robot.motors.getDriveRightParent().set(0);
    }

    public static void stopLeftMotors() {
        Robot.motors.getDriveLeftParent().set(0);
    }

    public static void stopRightMotors() {
        Robot.motors.getDriveRightParent().set(0);
    }

    public static void turn(double percent) {
        setLeft(percent);
        setRight(-percent);
    }

    public static void resetDriveTrainEncoders() {
        Robot.motors.getDriveLeftParent().setEncoderPosition(0.0);
        Robot.motors.getDriveRightParent().setEncoderPosition(0.0);
    }

    public static void setLeft(double percent) {
        Robot.motors.getDriveLeftParent().set(percent);
    }

    public static void setRight(double percent) {
        Robot.motors.getDriveRightParent().set(percent);
    }
}
