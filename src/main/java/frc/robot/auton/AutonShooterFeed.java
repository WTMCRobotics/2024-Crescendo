package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Robot;

public class AutonShooterFeed extends AutonAction {

    private double revFinishedTime;

    @Override
    public boolean isDone() {
        return Timer.getFPGATimestamp() > revFinishedTime;
    }

    @Override
    public void initiate() {
        Robot.motors.getFeeder().set(0.75);
        revFinishedTime = Timer.getFPGATimestamp() + Constants.FEEDER_MOTOR_SHUTOFF_TIME;
    }

    @Override
    public void shutdown() {
        Robot.motors.getFeeder().stopMotor();
    }
}
