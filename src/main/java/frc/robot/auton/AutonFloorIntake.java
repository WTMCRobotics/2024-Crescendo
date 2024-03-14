package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Robot;

public class AutonFloorIntake extends AutonAction {

    double targetTime = -1;

    @Override
    public boolean isDone() {
        if (Robot.getShooterBeambreakSensor().get() && targetTime == -1) {
            targetTime = Timer.getFPGATimestamp() + Constants.MOVEMENT_TIME_AFTER_FLOOR_INTAKE_BEAMBREAK_TRIGGER;
            Robot.motors.getFeeder().set(Constants.FEEDER_MOVEMENT_SPEED_AFTER_FLOOR_INTAKE_BEAMBREAK_TRIGGER);
        }
        if (targetTime != -1 && Timer.getFPGATimestamp() >= targetTime) {
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {
        Robot.motors.getIntake().set(Constants.INTAKE_SPEED);
        Robot.motors.getFeeder().set(Constants.FEEDER_MOTOR_SPEED);
    }

    @Override
    public void shutdown() {
        Robot.motors.getIntake().stopMotor();
        Robot.motors.getFeeder().stopMotor();
    }
}
