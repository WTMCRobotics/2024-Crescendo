package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotComponents.Climber;
import frc.robot.RobotComponents.Intake;
import frc.robot.RobotComponents.Shooter;

public class InputtedCoDriverControls {

    static XboxController controller;

    public static void setCoDriverController(XboxController xboxController) {
        controller = xboxController;
    }

    public static void onEveryFrame() {
        if (controller.getAButtonPressed()) {
            Shooter.startFeedMotors();
        }
        if (controller.getXButton()) {
            Shooter.startShooterMotors();
        } else {
            Shooter.stopShooterMotors();
        }

        if (controller.getYButton()) {
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
    }
}
