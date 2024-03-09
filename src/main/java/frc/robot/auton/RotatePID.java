package frc.robot.auton;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.QuickActions;
import frc.robot.Robot;

public class RotatePID extends PIDCommand {

    public RotatePID(double relativeTurnDegree) {
        super(
            // The controller that the command will use
            new PIDController(Constants.ROTATION_GAINS.P, Constants.ROTATION_GAINS.I, Constants.ROTATION_GAINS.D),
            // This should return the measurement
            () -> Robot.getGyroscope().getAngle(),
            // This should return the setpoint (can also be a constant)
            Robot.getGyroscope().getAngle() + relativeTurnDegree,
            // This uses the output
            output -> {
                QuickActions.turn(-output);
                System.out.println("error is:" + output);
            }
        );
        // Use addRequirements() here to declare subsystem dependencies.
        // getController().enableContinuousInput(-180, 180);
        // Configure additional PID options by calling `getController` here.
        getController()
            .setTolerance(Constants.ROTATION_DEGREE_TOLERANCE, Constants.ROTATION_DEGREE_PER_SECOND_TOLERANCE);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}
