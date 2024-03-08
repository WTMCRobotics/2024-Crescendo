package frc.robot.robotcomponents;

import frc.robot.Constants;
import frc.robot.QuickActions;

public class DriveTrain {

    public static void driveTank(double leftY, double rightY) {
        if (Math.abs(leftY) > Constants.CONTROLLER_DEADZONE) {
            QuickActions.setLeft(leftY);
        } else {
            QuickActions.stopLeftMotors();
        }
        if (Math.abs(rightY) > Constants.CONTROLLER_DEADZONE) {
            QuickActions.setRight(rightY);
        } else {
            QuickActions.stopRightMotors();
        }
    }
}
