package frc.robot.motor;

public class MotorControllerFactory {

    public static MotorController create(int id, MotorController.Type type) {
        switch (type) {
            case Talon:
                return new TalonMotorController(id);
            case SparkMax:
                return new SparkMotorController(id);
            default:
                return null;
        }
    }
}
