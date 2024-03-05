package frc.robot.robotcomponents;

import frc.robot.Constants;
import frc.robot.Robot;

public class Intake {

    public static void startFloorIntake() {
        Robot.motors.getIntake().set(Constants.INTAKE_SPEED);
    }

    /* This will spin the motor backwards in an attempt to eject a stuck note*/
    public static void backtrack() {
        Robot.motors.getIntake().set(Constants.MOTOR_BACKTRACK_SPEED_PERCENT);
    }

    public static void stopFloorIntake() {
        Robot.motors.getIntake().stopMotor();
    }
}
