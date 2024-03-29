package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.robotcomponents.Climber;
import frc.robot.robotcomponents.Intake;
import frc.robot.robotcomponents.Shooter;

public class InputtedCoDriverControls {

    public static void onTeleopInit() {
        hasGoneUp = false;
    }

    /**Prevents the codriver from accidentally moving the arms down */
    static boolean hasGoneUp = false;

    public static void onEveryFrame() {
        XboxController controller = Robot.getCoDriverController();
        if (controller.getXButtonPressed()) {
            Shooter.startFeedMotors();
        }
        if (controller.getLeftBumperPressed()) {
            Shooter.startShooterMotors();
        } else if (controller.getYButtonPressed()) {
            Shooter.autoShootIntoSpeaker();
        }
        if (controller.getLeftBumperReleased()) {
            Shooter.stopShooterMotors();
        }

        if (controller.getBButton()) {
            Shooter.startShooterIntake();
            Intake.startFloorIntake();
        }
        if (controller.getBButtonReleased()) {
            Intake.stopFloorIntake();
            Shooter.stopShooterMotors();
        }

        if (controller.getBackButton()) {
            Climber.manualExtendArms();
            hasGoneUp = true;
        } else if (controller.getStartButton() && hasGoneUp) {
            Climber.manualRetractArms();
        } else {
            Climber.stopClimbMotors();
        }

        if (Double.compare(controller.getLeftTriggerAxis(), 0.90) == 0) {
            Shooter.moveHoodToIntakePosition();
        } else if (controller.getLeftTriggerAxis() >= 0.29 && controller.getLeftTriggerAxis() <= 0.71) {
            Shooter.moveHoodToShootingPosition();
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
