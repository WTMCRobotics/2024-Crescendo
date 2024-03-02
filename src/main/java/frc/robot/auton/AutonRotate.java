package frc.robot.auton;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.QuickActions;
import frc.robot.QuickActions.TurnDirection;
import frc.robot.Robot;

public class AutonRotate extends AutonAction {

    private double targetTurnDegrees;
    private ProfiledPIDController rotationPID;
    private TurnDirection turnDirection;
    private AHRS gyro;

    public AutonRotate(double targetTurnDegrees) {
        this.gyro = Robot.getGyroscope();
        this.targetTurnDegrees = targetTurnDegrees;
        rotationPID =
            new ProfiledPIDController(
                Constants.ROTATION_GAINS.P,
                Constants.ROTATION_GAINS.I,
                Constants.ROTATION_GAINS.D,
                new Constraints(1, 1)
            );
        rotationPID.setGoal(targetTurnDegrees);
        rotationPID.reset(0);
        if (targetTurnDegrees > 0) {
            turnDirection = TurnDirection.RIGHT;
        } else {
            turnDirection = TurnDirection.LEFT;
        }
        System.out.println("We will be turning " + turnDirection);
        SmartDashboard.putNumber("target degree", targetTurnDegrees);
    }

    @Override
    public boolean isDone() {
        double angle = gyro.getAngle() - targetTurnDegrees;
        double calculatedValue = rotationPID.calculate(angle, targetTurnDegrees);
        SmartDashboard.putNumber("Rotation PID", calculatedValue);
        SmartDashboard.putNumber("Position Error", rotationPID.getPositionError());
        SmartDashboard.putNumber("Velocity Error", rotationPID.getVelocityError());

        QuickActions.turn(turnDirection, calculatedValue);

        if (rotationPID.atGoal()) {
            double rotation = Math.abs(gyro.getAngle() - targetTurnDegrees);
            System.out.println(
                "THE ROTATION IS DONE BECAUSE " + rotation + " is less than " + Constants.ROTATION_ERROR_DEGREES
            );
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {
        gyro.reset();
    }

    @Override
    public void shutdown() {
        QuickActions.stopDriveMotors();
    }
}
