package frc.robot.robotcomponents;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.auton.AutonShoot;
import frc.robot.auton.AutonShooterFeed;

public class Shooter {

    public static void startShooterMotors() {
        Robot.motors.getLeftFlywheel().setVelocity(Constants.SHOOTING_VELOCITY_RPM);
        Robot.motors.getRightFlywheel().setVelocity(Constants.SHOOTING_VELOCITY_RPM);
    }

    public static void stopShooterMotors() {
        Robot.motors.getLeftFlywheel().stopMotor();
        Robot.motors.getRightFlywheel().stopMotor();
        Robot.motors.getFeeder().stopMotor();
    }

    public static void startFeedMotors() {
        Robot.getTeleopActionRunner().removeActionsOfType(AutonShooterFeed.class);
        Robot.getTeleopActionRunner().addActionToRun(new AutonShooterFeed());
    }

    /* This will spin the motor backwards in an attempt to eject a stuck note*/
    public static void backtrack() {
        Robot.getTeleopActionRunner().removeActionsOfType(AutonShooterFeed.class, AutonShoot.class);
        Robot.motors.getLeftFlywheel().set(Constants.MOTOR_BACKTRACK_SPEED_PERCENT);
        Robot.motors.getRightFlywheel().set(Constants.MOTOR_BACKTRACK_SPEED_PERCENT);
        Robot.motors.getFeeder().set(Constants.MOTOR_BACKTRACK_SPEED_PERCENT);
    }

    public static void autoShootIntoSpeaker() {
        Robot.getTeleopActionRunner().addActionToRun(new AutonShoot());
    }
}
