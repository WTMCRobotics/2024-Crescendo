package frc.robot.auton;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.robotcomponents.Shooter;

public class AutonMoveHoodToPosition extends AutonAction {

    public HoodPosition targetPosition;

    public AutonMoveHoodToPosition(HoodPosition targetPosition) {
        this.targetPosition = targetPosition;
    }

    @Override
    public boolean isDone() {
        // return true;
        double error = Math.abs(
            Robot.motors.getHoodAdjuster().getEncoderPosition() - targetPosition.getTargetRotations()
        );
        if (error < Constants.SHOOTER_HOOD_ADJUSTMENT_ERROR_ROTATIONS) {
            Shooter.setCurrentHoodPosition(targetPosition);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initiate() {
        Shooter.setCurrentHoodPosition(HoodPosition.IN_BETWEEN);

        int multiplier = 1;
        if (Robot.motors.getHoodAdjuster().getEncoderPosition() > targetPosition.getTargetRotations()) {
            multiplier = 1;
        } else {
            multiplier = -1;
        }
        Robot.motors.getHoodAdjuster().set(Constants.SHOOTER_HOOD_ADJUSTMENT_SPEED * multiplier);
    }

    @Override
    public void shutdown() {
        Robot.motors.getHoodAdjuster().stopMotor();
    }

    public enum HoodPosition {
        IN_BETWEEN(0.0),
        INTAKING(Constants.HOOD_INTAKE_POSITION_ROTATIONS),
        SHOOTING_DEFAULT(Constants.HOOD_SHOOTING_POSITION_ROTATIONS);

        private double encoderRotations;

        private HoodPosition(double encoderRotation) {
            this.encoderRotations = encoderRotation;
        }

        public double getTargetRotations() {
            return encoderRotations;
        }
    }
}
