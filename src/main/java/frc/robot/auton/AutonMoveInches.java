package frc.robot.auton;

import frc.robot.QuickActions;

public class AutonMoveInches extends AutonAction {

    double distance;
    double isDoneDebounceTime = 0;

    @Override
    public boolean isDone() {
        QuickActions.leftParent.setDistance(distance);
        QuickActions.rightParent.setDistance(distance);
        System.out.println(
            QuickActions.leftParent.get() + " veL: " + QuickActions.leftParent.getActiveTrajectoryVelocity()
        );
        if (getMaxTrajectoryVelocity() < 0.05) {
            isDoneDebounceTime += 0.02;
        } else {
            isDoneDebounceTime = 0;
        }

        if (isDoneDebounceTime > 3) {
            System.out.println("We moved the correct amount of inches!");
            return true;
        }

        return false;
    }

    @Override
    public void initiate() {
        QuickActions.resetDriveTrainEncoders();
    }

    private double getMaxTrajectoryVelocity() {
        return Math.max(
            QuickActions.leftParent.getActiveTrajectoryVelocity(),
            QuickActions.rightParent.getActiveTrajectoryVelocity()
        );
    }

    /**
     *
     * @param distance The distance in inches
     */
    public AutonMoveInches(double distance) {
        this.distance = distance;
    }
}
