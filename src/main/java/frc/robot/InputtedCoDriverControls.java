package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.auton.AutonShoot;
import frc.robot.auton.ExtendArms;
import frc.robot.auton.RetractArms;

public class InputtedCoDriverControls {

    static XboxController controller;

    public static void setCoDriverController(XboxController xboxController) {
        controller = xboxController;
    }

    public static void onEveryFrame() {
        Robot.motors.getFeeder().stopMotor();
        if (controller.getAButton()) {
            Robot.motors.getFeeder().set(30);
        }
        if (controller.getXButtonPressed()) {
            shootIntoSpeaker();
        }
        Robot.motors.getIntake().stopMotor();
        if (controller.getYButton()) {
            doFloorIntake();
        }
        Robot.motors.getLeftClimb().stopMotor();
        Robot.motors.getRightClimb().stopMotor();
        if (controller.getStartButton()) {
            //extendArms();
            Robot.motors.getLeftClimb().set(Constants.CLIMB_EXTENSION_SPEED);
            Robot.motors.getRightClimb().set(Constants.CLIMB_EXTENSION_SPEED);
        }
        if (controller.getBackButton()) {
            //retractArms();
            Robot.motors.getLeftClimb().set(Constants.CLIMB_RETRACTION_SPEED);
            Robot.motors.getRightClimb().set(Constants.CLIMB_RETRACTION_SPEED);
        }
    }

    private static void doFloorIntake() {
        Robot.motors.getIntake().set(.5);
        //Robot.motors.getFeeder().set(.5);
    }

    private static void extendArms() {
        Robot.getTeleopActionRunner().addActionToRun(new ExtendArms());
        Robot.getTeleopActionRunner().removeActionsOfType(RetractArms.class);
    }

    private static void retractArms() {
        Robot.getTeleopActionRunner().addActionToRun(new RetractArms());
        Robot.getTeleopActionRunner().removeActionsOfType(ExtendArms.class);
    }

    private static void shootIntoSpeaker() {
        Robot.getTeleopActionRunner().addActionToRun(new AutonShoot());
    }

    private static void dropIntoAmp() {
        throw new UnsupportedOperationException("Unimplemented method 'dropIntoAmp'");
    }
}
