package frc.robot.auton;

import frc.robot.QuickActions;
import frc.robot.Robot;

public class AutonMoveForward extends AutonAction {

    double distance;

    @Override
    public boolean isDone() {
        double distanceTravelled = Robot.getGyroscope().getDisplacementY();

        if (distanceTravelled >= distance) {
            QuickActions.stopAll();
            return true;
        }

        return false;
    }

    @Override
    public void initiate() {
        QuickActions.resetDriveTrainEncoders();
        QuickActions.leftParent.setDistance(distance);
        QuickActions.rightParent.setDistance(distance);
    }

    /**
     *
     * @param distance The distance in inches
     */
    public AutonMoveForward(double distance) {
        this.distance = distance;
    }
}
