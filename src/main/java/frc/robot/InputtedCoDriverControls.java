package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.robotcomponents.Climber;
import frc.robot.robotcomponents.Intake;
import frc.robot.robotcomponents.Shooter;

public class InputtedCoDriverControls {

    static XboxController controller;

    public static void setCoDriverController(XboxController xboxController) {
        controller = xboxController;
    }

    public static void onTeleopInit() {
        hasGoneUp = false;
    }

    /**Prevents the codriver from accidentally moving the arms down */
    static boolean hasGoneUp = false;

    public static void onEveryFrame() {
        if (controller.getYButtonPressed()) {
            Shooter.startFeedMotors();
        }
        if (controller.getXButtonPressed()) {
            Shooter.startShooterMotors();
        } else if (controller.getBButtonPressed()) {
            Shooter.autoShootIntoSpeaker();
        }
        if (controller.getXButtonReleased()) {
            Shooter.stopShooterMotors();
        }

        if (controller.getLeftBumper()) {
            Intake.startFloorIntake();
        }
        if (controller.getLeftBumperReleased()) {
            Intake.stopFloorIntake();
        }

        if (controller.getStartButton()) {
            Climber.manualExtendArms();
            hasGoneUp = true;
        } else if (controller.getBackButton() && hasGoneUp) {
            Climber.manualRetractArms();
        } else {
            Climber.stopClimbMotors();
        }

        if (controller.getAButtonPressed()) {
            Intake.backtrack();
            Shooter.backtrack();
        } else if (controller.getAButtonReleased()) {
            Intake.stopFloorIntake();
            Shooter.stopShooterMotors();
        }
    }
}
