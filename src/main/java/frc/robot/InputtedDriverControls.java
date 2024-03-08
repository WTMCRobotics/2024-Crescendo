package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.robotcomponents.DriveTrain;
import frc.robot.robotcomponents.Intake;

public class InputtedDriverControls {

    public static void onEveryFrame() {
        XboxController controller = Robot.getDriverController();
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
        //This isn't probably helpful, but if the controllers somehow switch mid match, this box will turn red
        if (controller.getRightX() <= -0.99 && Math.abs(controller.getRightY()) < 0.01) {
            framesWithBadInput++;
            if (framesWithBadInput > 75) {
                SmartDashboard.putBoolean("Estimated Controller Status", false);
            }
        } else {
            // Doing an if statement here instead of putting it on smart dashboard every time saves performance
            if (framesWithBadInput > 75) {
                SmartDashboard.putBoolean("Estimated Controller Status", true);
            }
            framesWithBadInput = 0;
        }
    }

    static int framesWithBadInput = 0;

    private static void stopAtShootingDistance() {}

    private static void stopAtAmpShootingDistance() {}

    private static void stopAtFeedingDistance() {}
}
