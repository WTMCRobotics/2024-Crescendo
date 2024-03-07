package frc.robot.auton;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.QuickActions;
import frc.robot.Robot;

public class MovementPID extends PIDCommand {

    public MovementPID(double inches) {
        super(
            // The controller that the command will use
            new PIDController(
                Constants.NON_SMART_MOTION_MOVEMENT_PID_ROBOT_GAINS.P,
                Constants.NON_SMART_MOTION_MOVEMENT_PID_ROBOT_GAINS.I,
                Constants.NON_SMART_MOTION_MOVEMENT_PID_ROBOT_GAINS.D
            ),
            // This should return the measurement
            () -> Robot.motors.getDriveLeftParent().getEncoderPosition(),
            // This should return the setpoint (can also be a constant)
            () -> (inches / Constants.WHEEL_CIRCUMFERENCE_INCHES) * Constants.DRIVE_GEARBOX_RATIO,
            // This uses the output
            output -> {
                QuickActions.setDriveMotors(output);
            }
        );
        // Configure additional PID options by calling `getController` here.
        getController()
            .setTolerance(Constants.MOVEMENT_PID_INCH_TOLERANCE, Constants.MOVEMENT_PID_INCH_PER_SECOND_TOLERANCE);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}
