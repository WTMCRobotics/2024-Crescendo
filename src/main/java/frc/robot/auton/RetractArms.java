package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Robot;

public class RetractArms extends AutonAction {

    private double extensionTime;

    @Override
    public boolean isDone() {
        if (Timer.getFPGATimestamp() >= extensionTime) {
            Robot.motors.getLeftClimb().set(0);
            Robot.motors.getRightClimb().set(0);
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {
        Robot.motors.getLeftClimb().set(Constants.ARM_RETRACTION_SPEED);
        Robot.motors.getRightClimb().set(Constants.ARM_RETRACTION_SPEED);
        extensionTime = Timer.getFPGATimestamp() + Constants.ARM_RETRACTION_TIME;
    }
}
