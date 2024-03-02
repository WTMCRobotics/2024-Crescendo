package frc.robot.auton;

import frc.robot.QuickActions;

public class AutonRotateWithPIDCommand extends AutonAction {

    private RotatePID pidCommand;
    private double targetRelativeDegrees;

    public AutonRotateWithPIDCommand(double targetRelativeDegrees) {
        this.targetRelativeDegrees = targetRelativeDegrees;
    }

    @Override
    public boolean isDone() {
        pidCommand.execute();
        return pidCommand.isFinished();
    }

    @Override
    public void initiate() {
        pidCommand = new RotatePID(targetRelativeDegrees);
    }

    @Override
    public void shutdown() {
        QuickActions.stopDriveMotors();
    }
}
