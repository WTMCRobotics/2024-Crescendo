package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.motor.MotorController;

//TODO : this
public class AutonShoot extends AutonAction {

    private MotorController leftShooterMotor;
    private MotorController rightShooterMotor;

    private MotorController shooterFeederMotor;

    private double revFinishedTime;

    @Override
    public boolean isDone() {
        if (Timer.getFPGATimestamp() >= revFinishedTime) {
            shooterFeederMotor.set(0.2);
        }

        return true;
    }

    @Override
    public void initiate() {
        leftShooterMotor.set(1.0);
        rightShooterMotor.set(1.0);
        revFinishedTime = Timer.getFPGATimestamp() + Constants.REV_TIME;
    }
}
