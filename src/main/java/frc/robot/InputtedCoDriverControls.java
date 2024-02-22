package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.auton.AutonShoot;

public class InputtedCoDriverControls {

    static XboxController controller;

    public static void setCoDriverController(XboxController xboxController) {
        controller = xboxController;
    }

    public static void onEveryFrame() {
        if (controller.getAButton()) {
            Robot.motors.getFeeder().set(30);
        } else {
            Robot.motors.getFeeder().set(0);
        }
        if (controller.getXButtonPressed()) {
            shootIntoSpeaker();
        }
        doShooterIntake();
        if (controller.getBackButton()) {
            // doClimb();
        }
        if (controller.getStartButton()) {
            // extendArms();
        }
    }

    private static void extendArms() {
        throw new UnsupportedOperationException("Unimplemented method 'extendArms'");
    }

    private static void retractArms() {
        throw new UnsupportedOperationException("Unimplemented method 'doClimb'");
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
