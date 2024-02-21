package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.motor.MotorController;

//TODO : this
public class AutonShoot extends AutonAction {

    private MotorController leftShooterMotor = Robot.leftShooterMotor;
    private MotorController rightShooterMotor = Robot.rightShooterMotor;

    private MotorController shooterFeederMotor = Robot.feederMotor;

    private double revFinishedTime;

    @Override
    public boolean isDone() {
        if (Timer.getFPGATimestamp() >= revFinishedTime) {
            shooterFeederMotor.set(0.2);
        }
        if (Timer.getFPGATimestamp() >= revFinishedTime + 1.0) {
            shooterFeederMotor.set(0);
            leftShooterMotor.set(0);
            rightShooterMotor.set(0);
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {
        leftShooterMotor.set(1.0);
        rightShooterMotor.set(-1.0);
        revFinishedTime = Timer.getFPGATimestamp() + Constants.REV_TIME;
    }
}
