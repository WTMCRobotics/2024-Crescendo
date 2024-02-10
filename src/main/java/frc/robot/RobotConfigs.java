package frc.robot;

import frc.robot.motor.MotorController;
import frc.robot.motor.MotorControllerFactory;

public class RobotConfigs {

    static RobotConfig config = RobotConfig.COMPETITION;

    public static MotorController getLeftParent() {
        return config == RobotConfig.COMPETITION
            ? MotorControllerFactory.create(Constants.DRIVE_LEFT_PARENT_ID, MotorController.Type.SparkMax)
            : MotorControllerFactory.create(20, MotorController.Type.Talon);
    }

    public static MotorController getLeftChild() {
        return config == RobotConfig.COMPETITION
            ? MotorControllerFactory.create(Constants.DRIVE_LEFT_CHILD_ID, MotorController.Type.SparkMax)
            : MotorControllerFactory.create(22, MotorController.Type.Talon);
    }

    public static MotorController getRightParent() {
        return config == RobotConfig.COMPETITION
            ? MotorControllerFactory.create(Constants.DRIVE_RIGHT_PARENT_ID, MotorController.Type.SparkMax)
            : MotorControllerFactory.create(21, MotorController.Type.Talon);
    }

    public static MotorController getRightChild() {
        return config == RobotConfig.COMPETITION
            ? MotorControllerFactory.create(Constants.DRIVE_RIGHT_CHILD_ID, MotorController.Type.SparkMax)
            : MotorControllerFactory.create(23, MotorController.Type.Talon);
    }
}

enum RobotConfig {
    COMPETITION,
    PRACTICING,
}
