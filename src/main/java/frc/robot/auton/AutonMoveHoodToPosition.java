package frc.robot.auton;

import frc.robot.Constants;

public class AutonMoveHoodToPosition extends AutonAction {

    public HoodPosition targetPosition;

    public AutonMoveHoodToPosition(HoodPosition targetPosition) {
        this.targetPosition = targetPosition;
    }

    @Override
    public boolean isDone() {
        return true;
        // double error = Math.abs(
        //     Robot.motors.getHoodAdjuster().getEncoderPosition() - targetPosition.getTargetRotations()
        // );
        // return error < Constants.SHOOTER_HOOD_ADJUSTMENT_ERROR_ROTATIONS;
    }

    @Override
    public void initiate() {
        // int multiplier = 1;
        // if (Robot.motors.getHoodAdjuster().getEncoderPosition() > targetPosition.getTargetRotations()) {
        //     multiplier = -1;
        // } else {
        //     multiplier = 1;
        // }
        // Robot.motors.getHoodAdjuster().set(Constants.SHOOTER_HOOD_ADJUSTMENT_SPEED * multiplier);
    }

    @Override
    public void shutdown() {
        // Robot.motors.getHoodAdjuster().stopMotor();
    }

    public enum HoodPosition {
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
