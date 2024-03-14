package frc.robot.robotcomponents;

import frc.robot.Robot;
import frc.robot.auton.AutonFloorIntake;
import frc.robot.auton.AutonSourceIntake;

public class Intake {

    public static void autoIntake() {
        Robot.getTeleopActionRunner().removeActionsOfType(AutonFloorIntake.class);
        // Robot.getTeleopActionRunner().addActionToRun(new AutonIntake());
    }

    public static void startFloorIntake() {
        Robot.getTeleopActionRunner().addActionToRun(new AutonFloorIntake());
    }

    public static void startSourceIntake() {
        Robot.getTeleopActionRunner().addActionToRun(new AutonSourceIntake());
    }

    /* This will spin the motor backwards in an attempt to eject a stuck note*/
    public static void backtrack() {
        // Robot.motors.getIntake().set(Constants.MOTOR_BACKTRACK_SPEED_PERCENT);
    }

    public static void stopSourceIntake() {
        Robot.getTeleopActionRunner().removeActionsOfType(AutonSourceIntake.class);
    }

    public static void stopFloorIntake() {
        Robot.getTeleopActionRunner().removeActionsOfType(AutonFloorIntake.class);
    }
}
