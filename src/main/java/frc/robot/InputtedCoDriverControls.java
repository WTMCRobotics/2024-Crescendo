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
        Robot.motors.getLeftClimb().stopMotor();
        Robot.motors.getRightClimb().stopMotor();
        if (controller.getAButton()) {
            Robot.motors.getFeeder().set(30);
        } else {
            Robot.motors.getFeeder().set(0);
        }
        if (controller.getXButtonPressed()) {
            shootIntoSpeaker();
        }
        doShooterIntake();
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

    private static void extendArms() {
        Robot.getTeleopActionRunner().addActionToRun(new ExtendArms());
        Robot.getTeleopActionRunner().removeActionsOfType(RetractArms.class);
    }

    private static void retractArms() {
        Robot.getTeleopActionRunner().addActionToRun(new RetractArms());
        Robot.getTeleopActionRunner().removeActionsOfType(ExtendArms.class);
    }

    private static void doShooterIntake() {
        if (controller.getPOV() == 0) {
            Robot.motors.getRightFlywheel().set(0.3);
            Robot.motors.getLeftFlywheel().set(-0.3);
        } else if (controller.getPOV() == 180) {
            Robot.motors.getRightFlywheel().set(0);
            Robot.motors.getLeftFlywheel().set(0);
        }
    }

    private static void shootIntoSpeaker() {
        Robot.getTeleopActionRunner().addActionToRun(new AutonShoot());
    }

    private static void dropIntoAmp() {
        throw new UnsupportedOperationException("Unimplemented method 'dropIntoAmp'");
    }
}
