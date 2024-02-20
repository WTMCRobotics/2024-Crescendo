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
            Robot.feederMotor.set(30);
        } else {
            Robot.feederMotor.set(0);
        }
        if (controller.getXButtonPressed()) {
            shootIntoSpeaker();
        }
        if (shoot != null) {
            if (shoot.isDone() == true) {
                shoot = null;
            }
        }
        doIntake();
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

    private static void doClimb() {
        throw new UnsupportedOperationException("Unimplemented method 'doClimb'");
    }

    private static void doIntake() {
        if (controller.getPOV() == 0) {
            Robot.rightShooterMotor.set(0.3);
            Robot.leftShooterMotor.set(-0.3);
        } else if (controller.getPOV() == 180) {
            Robot.rightShooterMotor.set(0);
            Robot.leftShooterMotor.set(0);
        }
    }

    static AutonShoot shoot = null;

    private static void shootIntoSpeaker() {
        shoot = new AutonShoot();
        shoot.initiate();
    }

    private static void dropIntoAmp() {
        throw new UnsupportedOperationException("Unimplemented method 'dropIntoAmp'");
    }
}
