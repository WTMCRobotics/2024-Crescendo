package frc.robot.RobotComponents;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.auton.AutonShooterFeed;

public class Shooter {

    public void startShooterMotors() {
        Robot.motors.getLeftFlywheel().setVelocity(Constants.SHOOTING_VELOCITY);
        Robot.motors.getRightFlywheel().setVelocity(Constants.SHOOTING_VELOCITY);
    }

    public void stopShooterMotors() {
        Robot.motors.getLeftFlywheel().stopMotor();
        Robot.motors.getRightFlywheel().stopMotor();
    }

    public void startFeedMotors() {
        Robot.getTeleopActionRunner().removeActionsOfType(AutonShooterFeed.class);
        Robot.getTeleopActionRunner().addActionToRun(new AutonShooterFeed());
    }
}
