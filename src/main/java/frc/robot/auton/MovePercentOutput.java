package frc.robot.auton;

import frc.robot.QuickActions;

public class MovePercentOutput extends AutonAction {

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void initiate() {
        QuickActions.setDriveMotors(0.25);
    }

    @Override
    public void shutdown() {}
}
