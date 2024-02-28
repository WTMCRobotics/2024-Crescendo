package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Robot;

public class RetractArms extends AutonAction {

    @Override
    public boolean isDone() {
        if (Robot.motors.getLeftClimb().getEncoderPosition() < Constants.CLIMB_RETRACTED_ENCODER_POSITION) {
            Robot.motors.getLeftClimb().set(0);
            Robot.motors.getRightClimb().set(0);
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {
        Robot.motors.getLeftClimb().set(Constants.CLIMB_RETRACTION_SPEED);
        Robot.motors.getRightClimb().set(Constants.CLIMB_RETRACTION_SPEED);
    }
}
