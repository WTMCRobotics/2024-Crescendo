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
        if (
            Robot.motors.getLeftClimb().getEncoderPosition() < Constants.CLIMBER_LEFT_ARM_MAX_EXTENSION_ROTATIONS ||
            Robot.getDriverController().getAButton()
        ) {
            Robot.motors.getLeftClimb().set(Constants.CLIMB_EXTENSION_SPEED);
        } else {
            Robot.motors.getLeftClimb().stopMotor();
        }

        //For some reason, this encoder gives negative values, and inverted it didn't do anything
        if (-Robot.motors.getRightClimb().getEncoderPosition() < Constants.CLIMBER_RIGHT_ARM_MAX_EXTENSION_ROTATIONS) {
            Robot.motors.getRightClimb().set(Constants.CLIMB_EXTENSION_SPEED);
        } else {
            Robot.motors.getRightClimb().stopMotor();
        }
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
