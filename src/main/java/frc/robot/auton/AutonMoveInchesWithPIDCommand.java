package frc.robot.auton;

import frc.robot.QuickActions;

public class AutonMoveInchesWithPIDCommand extends AutonAction {

    private MovementPID pidCommand;
    private double inches;

    public AutonMoveInchesWithPIDCommand(double inches) {
        this.inches = inches;
    }

    @Override
    public boolean isDone() {
        pidCommand.execute();
        return pidCommand.isFinished();
    }

    @Override
    public void initiate() {
        pidCommand = new MovementPID(inches);
    }

    @Override
    public void shutdown() {
        QuickActions.stopDriveMotors();
    }
}
