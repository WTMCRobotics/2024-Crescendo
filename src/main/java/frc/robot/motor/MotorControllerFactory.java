package frc.robot.motor;

import com.revrobotics.CANSparkLowLevel.MotorType;

public class MotorControllerFactory {

    public static MotorController create(int id, MotorController.Type type) {
        switch (type) {
            case Talon:
                return new TalonMotorController(id);
            case SparkMaxBrushless:
                return new SparkMotorController(id, MotorType.kBrushless);
            case SparkMaxBrushed:
                return new SparkMotorController(id, MotorType.kBrushed);
            default:
                return null;
        }
    }
}
