package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class InputtedDriverControls {

    static XboxController controller;

    public static void setDriverController(XboxController xboxController) {
        controller = xboxController;
    }

    public static void onEveryFrame() {
        if (controller.getBButtonPressed()) {
            stopAtFeedingDistance();
        }
        if (controller.getAButtonPressed()) {
            stopAtAmpShootingDistance();
        }
        if (controller.getXButtonPressed()) {
            stopAtShootingDistance();
        }
        driveTank(controller.getLeftY(), controller.getRightY());
    }

    private static void driveTank(double leftY, double rightY) {
        throw new UnsupportedOperationException("Unimplemented method 'driveTank'");
    }

    private static void stopAtShootingDistance() {
        throw new UnsupportedOperationException("Unimplemented method 'stopAtShootingDistance'");
    }

    private static void stopAtAmpShootingDistance() {
        throw new UnsupportedOperationException("Unimplemented method 'stopAtAmpShootingDistance'");
    }

    private static void stopAtFeedingDistance() {
        throw new UnsupportedOperationException("Unimplemented method 'stopAtFeedingDistance'");
    }
}
