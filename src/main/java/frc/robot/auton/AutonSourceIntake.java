package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.robotcomponents.Shooter;

public class AutonSourceIntake extends AutonAction {

    double targetTime = -1;
    boolean seenRing;

    private static boolean isCurrentlyPosititionRing = false;

    public static boolean isCurrentlyPosititionRing() {
        return isCurrentlyPosititionRing;
    }

    @Override
    public boolean isDone() {
        if (Robot.getShooterBeambreakSensor().get()) {
            seenRing = true;
            isCurrentlyPosititionRing = true;
        } else if (seenRing && targetTime == -1) {
            targetTime = Timer.getFPGATimestamp() + Constants.MOVEMENT_TIME_AFTER_SOURCE_INTAKE_BEAMBREAK_TRIGGER;
            Robot.motors.getFeeder().set(Constants.FEEDER_MOVEMENT_SPEED_AFTER_SOURCE_INTAKE_BEAMBREAK_TRIGGER);
            Robot.motors.getLeftFlywheel().set(Constants.FLYWHEEL_MOVEMENT_SPEED_AFTER_SOURCE_INTAKE_BEAMBREAK_TRIGGER);
            Robot.motors
                .getRightFlywheel()
                .set(Constants.FLYWHEEL_MOVEMENT_SPEED_AFTER_SOURCE_INTAKE_BEAMBREAK_TRIGGER);
        }
        if (targetTime != -1 && Timer.getFPGATimestamp() >= targetTime) {
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {
        Robot.motors.getFeeder().set(Constants.FEEDER_MOTOR_SPEED);
        isCurrentlyPosititionRing = false;
    }

    @Override
    public void shutdown() {
        isCurrentlyPosititionRing = false;
        Shooter.stopShooterMotors();
    }
}
