package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.robotcomponents.DriveTrain;
import frc.robot.robotcomponents.Intake;

public class InputtedDriverControls {

    static XboxController controller;

    public static void setDriverController(XboxController xboxController) {
        controller = xboxController;
    }

    public static void onEveryFrame() {
        DriveTrain.driveTank(controller.getLeftY(), controller.getRightY());
        if (controller.getBButtonPressed()) {
            stopAtFeedingDistance();
        }
        if (controller.getAButtonPressed()) {
            stopAtAmpShootingDistance();
        }
        if (controller.getXButtonPressed()) {
            stopAtShootingDistance();
        }
        if (controller.getLeftBumper()) {
            Intake.startFloorIntake();
        }
        if (controller.getLeftBumperReleased()) {
            Intake.stopFloorIntake();
        }
    }

    private static void stopAtShootingDistance() {}

    private static void stopAtAmpShootingDistance() {}

    private static void stopAtFeedingDistance() {}
}
