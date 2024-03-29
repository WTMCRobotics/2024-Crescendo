package frc.robot.auton;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.QuickActions;
import frc.robot.Robot;

public class AutonRotate extends AutonAction {

    private double targetTurnDegrees;
    private ProfiledPIDController rotationPID;

    public AutonRotate(double targetTurnDegrees) {
        this.targetTurnDegrees = targetTurnDegrees;
    }

    @Override
    public boolean isDone() {
        double angle = Robot.getGyroscope().getAngle() - targetTurnDegrees;
        double calculatedValue = rotationPID.calculate(angle, targetTurnDegrees);
        SmartDashboard.putNumber("Rotation PID", calculatedValue);
        SmartDashboard.putNumber("Position Error", rotationPID.getPositionError());
        SmartDashboard.putNumber("Velocity Error", rotationPID.getVelocityError());

        QuickActions.turn(calculatedValue);

        if (rotationPID.atGoal()) {
            double rotation = Math.abs(Robot.getGyroscope().getAngle() - targetTurnDegrees);
            System.out.println(
                "THE ROTATION IS DONE BECAUSE " + rotation + " is less than " + Constants.ROTATION_ERROR_DEGREES
            );
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {
        rotationPID =
            new ProfiledPIDController(
                Constants.ROTATION_GAINS.P,
                Constants.ROTATION_GAINS.I,
                Constants.ROTATION_GAINS.D,
                new Constraints(1, 1)
            );
        rotationPID.setGoal(targetTurnDegrees);
        rotationPID.reset(0);

        SmartDashboard.putNumber("target degree", targetTurnDegrees);
        Robot.getGyroscope().reset();
    }

    @Override
    public void shutdown() {
        QuickActions.stopDriveMotors();
    }
}
