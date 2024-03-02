package frc.robot.robotcomponents;

import frc.robot.Robot;

public class Intake {

    public static void startFloorIntake() {
        Robot.motors.getIntake().set(.5);
    }

    public static void stopFloorIntake() {
        Robot.motors.getIntake().stopMotor();
    }
}
