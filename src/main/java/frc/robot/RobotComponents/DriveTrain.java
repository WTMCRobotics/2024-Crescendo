package frc.robot.robotcomponents;

import frc.robot.Constants;
import frc.robot.QuickActions;
import frc.robot.Robot;

public class DriveTrain {

    public static void driveTank(double leftY, double rightY) {
        QuickActions.stopDriveMotors();
        if (Math.abs(leftY) > Constants.CONTROLLER_DEADZONE) {
            Robot.motors.getDriveLeftParent().set(leftY);
        }
        if (Math.abs(rightY) > Constants.CONTROLLER_DEADZONE) {
            Robot.motors.getDriveRightParent().set(rightY);
        }
    }
}
