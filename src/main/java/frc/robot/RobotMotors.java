package frc.robot;

import frc.robot.motor.MotorController;

public class RobotMotors {

    private MotorController driveLeftChild;
    private MotorController driveLeftParent;
    private MotorController driveRightParent;
    private MotorController driveRightChild;


    public MotorController getDriveLeftParent() {
        return driveLeftParent;
    }

    public MotorController getDriveLeftChild() {
        return driveLeftChild;
    }

    public MotorController getDriveRightParent() {
        return driveRightParent;
    }

    public MotorController getDriveRightChild() {
        return driveRightChild;
    }

    //Builder notation

    public RobotMotors driveLeftParent(MotorController driveLeftParent) {
        this.driveLeftParent = driveLeftParent;
        return this;
    }

    public RobotMotors driveLeftChild(MotorController driveLeftChild) {
        this.driveLeftChild = driveLeftChild;
        return this;
    }

    public RobotMotors driveRightParent(MotorController driveRightParent) {
        this.driveRightParent = driveRightParent;
        return this;
    }

    public RobotMotors driveRightChild(MotorController driveRightChild) {
        this.driveRightChild = driveRightChild;
        return this;
    }

}
