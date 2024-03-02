package frc.robot;

public class QuickActions {

    public static void setDriveMotors(double percent) {
        Robot.motors.getDriveLeftParent().set(percent);
        Robot.motors.getDriveRightParent().set(percent);
    }

    public static void stopDriveMotors() {
        Robot.motors.getDriveLeftParent().set(0.0);
        Robot.motors.getDriveRightParent().set(0.0);
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
            Robot.motors.getDriveLeftParent().set(-percent);
            Robot.motors.getDriveRightParent().set(percent);
        } else if (direction == TurnDirection.RIGHT) {
            Robot.motors.getDriveLeftParent().set(percent);
            Robot.motors.getDriveRightParent().set(-percent);
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

    public enum TurnDirection {
        LEFT,
        RIGHT,
    }
}
