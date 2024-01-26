package frc.robot.Vision;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class QuickActions {

    static TalonSRX leftParent;
    static TalonSRX leftChild;
    static TalonSRX rightParent;
    static TalonSRX rightChild;

    public static void setDrivetMotors(
        TalonSRX leftParentMotor,
        TalonSRX leftChildMotor,
        TalonSRX rightParentMotor,
        TalonSRX rightChildMotor
    ) {
        leftParent = leftParentMotor;
        leftChild = leftChildMotor;
        rightParent = rightParentMotor;
        rightChild = rightChildMotor;
    }

    public static void setAll(double percent) {
        leftParent.set(ControlMode.PercentOutput, percent);
        leftChild.set(ControlMode.PercentOutput, percent);
        rightParent.set(ControlMode.PercentOutput, percent);
        rightChild.set(ControlMode.PercentOutput, percent);
    }

    public static void stopAll() {
        leftParent.set(ControlMode.PercentOutput, 0.0);
        leftChild.set(ControlMode.PercentOutput, 0.0);
        rightParent.set(ControlMode.PercentOutput, 0.0);
        rightChild.set(ControlMode.PercentOutput, 0.0);
    }

    public static void turn(TurnDirection direction, double percent) {
        if (direction == TurnDirection.LEFT) {
            leftParent.set(ControlMode.PercentOutput, -percent);
            leftChild.set(ControlMode.PercentOutput, -percent);
            rightParent.set(ControlMode.PercentOutput, percent);
            rightChild.set(ControlMode.PercentOutput, percent);
        } else if (direction == TurnDirection.RIGHT) {
            leftParent.set(ControlMode.PercentOutput, percent);
            leftChild.set(ControlMode.PercentOutput, percent);
            rightParent.set(ControlMode.PercentOutput, -percent);
            rightChild.set(ControlMode.PercentOutput, -percent);
        }
    }

    public enum TurnDirection {
        LEFT,
        RIGHT,
    }
}
