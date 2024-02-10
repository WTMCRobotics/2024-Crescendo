package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class InputtedCoDriverControls {

    static XboxController controller;

    public static void setCoDriverController(XboxController xboxController) {
        controller = xboxController;
    }

    public static void onEveryFrame() {
        if (controller.getAButtonPressed()) {
            dropIntoAmp();
        }
        if (controller.getXButtonPressed()) {
            shootIntoSpeaker();
        }
        if (controller.getPOV() == 0 || controller.getPOV() == 180) {
            doIntake();
        }
        if (controller.getBackButton()) {
            doClimb();
        }
        if (controller.getStartButton()) {
            extendArms();
        }
    }

    private static void extendArms() {
        throw new UnsupportedOperationException("Unimplemented method 'extendArms'");
    }

    private static void doClimb() {
        throw new UnsupportedOperationException("Unimplemented method 'doClimb'");
    }

    private static void doIntake() {
        throw new UnsupportedOperationException("Unimplemented method 'doIntake'");
    }

    private static void shootIntoSpeaker() {
        throw new UnsupportedOperationException("Unimplemented method 'shootIntoSpeaker'");
    }

    private static void dropIntoAmp() {
        throw new UnsupportedOperationException("Unimplemented method 'dropIntoAmp'");
    }
}
