package frc.robot.auton;

import frc.robot.QuickActions;
import frc.robot.Robot;

public class AutonMoveForward extends AutonAction {

    double distance;
    double speed;

    @Override
    public boolean executeAndIsDone() {
        double distanceTravelled = Robot.getGyroscope().getDisplacementY();

        if (distanceTravelled >= distance) {
            QuickActions.stopAll();
            return true;
        }

        return false;
    }

    @Override
    public void initiate() {
        QuickActions.setAll(speed);
    }

    public AutonMoveForward(double distance, double speed) {
        this.distance = distance;
        this.speed = speed;
    }
}
