package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Robot;

//TODO : this
public class AutonShoot extends AutonAction {

    private double revFinishedTime;

    @Override
    public boolean isDone() {
        if (Timer.getFPGATimestamp() >= revFinishedTime) {
            Robot.motors.getFeeder().set(0.2);
            Robot.motors.getIntake().set(.2);
        }
        if (Timer.getFPGATimestamp() >= revFinishedTime + 1.0) {
            Robot.motors.getFeeder().set(0);
            Robot.motors.getLeftFlywheel().set(0);
            Robot.motors.getRightFlywheel().set(0);
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {
        Robot.motors.getLeftFlywheel().set(1.0);
        Robot.motors.getRightFlywheel().set(-1.0);
        revFinishedTime = Timer.getFPGATimestamp() + Constants.REV_TIME;
    }
}
