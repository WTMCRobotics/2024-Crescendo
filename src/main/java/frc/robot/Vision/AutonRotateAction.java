package frc.robot.Vision;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.QuickActions;
import frc.robot.QuickActions.TurnDirection;
import frc.robot.Robot;

public class AutonRotateAction {

    private double targetTurnDegrees;
    private ProfiledPIDController rotationPID;
    private TurnDirection turnDirection;
    private AHRS gyro;

    public AutonRotateAction(double targetTurnDegrees) {
        this.gyro = Robot.getGyroscope();
        this.targetTurnDegrees = targetTurnDegrees;
        rotationPID =
            new ProfiledPIDController(
                Constants.ROTATION_GAINS.P,
                Constants.ROTATION_GAINS.I,
                Constants.ROTATION_GAINS.D,
                new Constraints(Constants.ROTATION_GAINS.PEAK_OUTPUT, .01)
            );
        if (targetTurnDegrees > 0) {
            turnDirection = TurnDirection.RIGHT;
        } else {
            turnDirection = TurnDirection.LEFT;
        }
        SmartDashboard.putNumber("target degree", targetTurnDegrees);
        gyro.reset();
    }

    private int debounceTime = 0;

    public boolean executeAndIsDone() {
        double calculatedValue = Math.min(
            Math.max(-rotationPID.calculate(gyro.getAngle(), targetTurnDegrees), -0.4),
            0.4
        );
        SmartDashboard.putNumber("Rotation PID", calculatedValue);
        SmartDashboard.putNumber("Position Error", rotationPID.getPositionError());
        SmartDashboard.putNumber("Velocity Error", rotationPID.getVelocityError());

        QuickActions.turn(turnDirection, calculatedValue);
        double rotation = Math.abs(gyro.getAngle() - targetTurnDegrees);

        if (rotation < Constants.ROTATION_ERROR_DEGREES) {
            debounceTime++;
            if (debounceTime > 3) {
                System.out.println(
                    "THE ROTATION IS DONE BECAUSE " + rotation + " is less than " + Constants.ROTATION_ERROR_DEGREES
                );
                return true;
            }
        } else {
            debounceTime = 0;
        }
        return false;
    }
}
