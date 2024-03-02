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
    }

    public static void startFeedMotors() {
        Robot.getTeleopActionRunner().removeActionsOfType(AutonShooterFeed.class);
        Robot.getTeleopActionRunner().addActionToRun(new AutonShooterFeed());
    }

    public static void autoShootIntoSpeaker() {
        Robot.getTeleopActionRunner().addActionToRun(new AutonShoot());
    }
}
