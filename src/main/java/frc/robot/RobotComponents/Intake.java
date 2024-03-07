package frc.robot.robotcomponents;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.auton.AutonIntake;

public class Intake {

    public static void autoIntake() {
        Robot.getTeleopActionRunner().removeActionsOfType(AutonIntake.class);
        // Robot.getTeleopActionRunner().addActionToRun(new AutonIntake());
    }

    public static void startFloorIntake() {
        // Robot.motors.getIntake().set(Constants.INTAKE_SPEED);
        Robot.motors.getLeftFlywheel().set(-0.25);
        Robot.motors.getRightFlywheel().set(-0.25);
    }

    /* This will spin the motor backwards in an attempt to eject a stuck note*/
    public static void backtrack() {
        // Robot.motors.getIntake().set(Constants.MOTOR_BACKTRACK_SPEED_PERCENT);
    }

    public static void stopFloorIntake() {
        Robot.getTeleopActionRunner().removeActionsOfType(AutonIntake.class);
        // Robot.motors.getIntake().stopMotor();
        Robot.motors.getLeftFlywheel().set(0);
        Robot.motors.getRightFlywheel().set(0);
    }
}
