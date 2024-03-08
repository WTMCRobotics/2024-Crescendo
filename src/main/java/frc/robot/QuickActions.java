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
        if (percent > 0) {
            turn(TurnDirection.RIGHT, percent);
        } else {
            turn(TurnDirection.LEFT, percent);
        }
    }

    public static void turn(TurnDirection direction, double percent) {
        if (direction == TurnDirection.LEFT) {
            setLeft(-percent);
            setRight(percent);
        } else if (direction == TurnDirection.RIGHT) {
            setLeft(percent);
            setRight(-percent);
        }
    }

    public static void turnVelocity(TurnDirection direction, double percent) {
        if (direction == TurnDirection.LEFT) {
            Robot.motors.getDriveLeftParent().setVelocity(-percent);
            Robot.motors.getDriveRightParent().setVelocity(percent);
        } else if (direction == TurnDirection.RIGHT) {
            Robot.motors.getDriveLeftParent().setVelocity(percent);
            Robot.motors.getDriveRightParent().setVelocity(-percent);
        }
    }

    public static void resetDriveTrainEncoders() {
        Robot.motors.getDriveLeftParent().setEncoderPosition(0.0);
        Robot.motors.getDriveRightParent().setEncoderPosition(0.0);
    }

    public static void setLeft(double percent) {
        Robot.motors.getDriveLeftParent().setPercentOutput(percent);
    }

    public static void setRight(double percent) {
        Robot.motors.getDriveRightParent().setPercentOutput(percent);
    }

    public enum TurnDirection {
        LEFT,
        RIGHT,
    }
}
