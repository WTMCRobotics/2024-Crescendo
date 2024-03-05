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

    public static void onEveryFrame() {
        if (controller.getYButtonPressed()) {
            Shooter.startFeedMotors();
        }
        if (controller.getXButton()) {
            Shooter.startShooterMotors();
        } else if (controller.getBButton()) {
            Shooter.autoShootIntoSpeaker();
        } else {
            Shooter.stopShooterMotors();
        }

        if (controller.getLeftBumper()) {
            Intake.startFloorIntake();
        } else {
            Intake.stopFloorIntake();
        }

        if (controller.getStartButton()) {
            Climber.manualExtendArms();
        } else if (controller.getBackButton()) {
            Climber.manualRetractArms();
        } else {
            Climber.stopClimbMotors();
        }

        if (controller.getAButton()) {

            Intake.backtrack();
            Shooter.backtrack();
        } else if (controller.getAButtonReleased()) {
            Intake.stopFloorIntake();
            Shooter.stopShooterMotors();
        }
    }
}
