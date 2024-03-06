package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Robot;

public class AutonShoot extends AutonAction {

    private double revFinishedTime;

    @Override
    public boolean isDone() {
        if (Timer.getFPGATimestamp() >= revFinishedTime) {
            Robot.motors.getFeeder().set(0.2);
            //Robot.motors.getIntake().set(.2);
        }

        return Timer.getFPGATimestamp() >= revFinishedTime + 1.0;
    }

    @Override
    public void initiate() {
        Robot.motors.getLeftFlywheel().set(Constants.SHOOTER_LEFT_FLYWHEEL_SPEED);
        Robot.motors.getRightFlywheel().set(Constants.SHOOTER_RIGHT_FLYWHEEL_SPEED);
        revFinishedTime = Timer.getFPGATimestamp() + Constants.SHOOTER_FLYWHEEL_STARTUP_TIME;
    }

    @Override
    public void shutdown() {
        Robot.motors.getFeeder().set(0);
        Robot.motors.getLeftFlywheel().set(0);
        Robot.motors.getRightFlywheel().set(0);
    }
}
