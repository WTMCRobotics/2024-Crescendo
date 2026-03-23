package frc.robot.robotcomponents;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.auton.ExtendArms;
import frc.robot.auton.RetractArms;

public class Climber {

    private static void extendArms() {
        Robot.getTeleopActionRunner().removeActionsOfType(RetractArms.class);
        Robot.getTeleopActionRunner().addActionToRun(new ExtendArms());
    }

    private static void retractArms() {
        Robot.getTeleopActionRunner().removeActionsOfType(ExtendArms.class);
        Robot.getTeleopActionRunner().addActionToRun(new RetractArms());
    }

    public static void manualExtendArms() {
        Robot.motors.getLeftClimb().set(Constants.CLIMB_EXTENSION_SPEED);
        Robot.motors.getRightClimb().set(Constants.CLIMB_EXTENSION_SPEED);
    }

    public static void manualRetractArms() {
        Robot.motors.getLeftClimb().set(Constants.CLIMB_RETRACTION_SPEED);
        Robot.motors.getRightClimb().set(Constants.CLIMB_RETRACTION_SPEED);
    }

    public static void stopClimbMotors() {
        Robot.motors.getLeftClimb().stopMotor();
        Robot.motors.getRightClimb().stopMotor();
    }
}
