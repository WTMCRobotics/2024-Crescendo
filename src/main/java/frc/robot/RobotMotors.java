package frc.robot;

import frc.robot.motor.MotorController;

public class RobotMotors {

    MotorController driveLeftParent;
    MotorController driveLeftChild;
    MotorController driveRightParent;
    MotorController driveRightChild;
    MotorController feeder;
    MotorController leftFlywheel;
    MotorController rightFlywheel;
    MotorController leftClimb;
    MotorController rightClimb;

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

    public MotorController getLeftFlywheel() {
        return leftFlywheel;
    }

    public MotorController getRightFlywheel() {
        return rightFlywheel;
    }

    public MotorController getFeeder() {
        return feeder;
    }

    public MotorController getLeftClimb() {
        return leftClimb;
    }

    public MotorController getRightClimb() {
        return rightClimb;
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

    public RobotMotors feeder(MotorController feeder) {
        this.feeder = feeder;
        return this;
    }

    public RobotMotors leftFlywheel(MotorController leftFlywheel) {
        this.leftFlywheel = leftFlywheel;
        return this;
    }

    public RobotMotors rightFlywheel(MotorController rightFlywheel) {
        this.rightFlywheel = rightFlywheel;
        return this;
    }

    public RobotMotors intakeMotor(MotorController intakeMotor) {
        this.feeder = intakeMotor;
        return this;
    }

    public RobotMotors leftClimb(MotorController leftClimb) {
        this.leftClimb = leftClimb;
        return this;
    }

    public RobotMotors rightClimb(MotorController rightClimb) {
        this.rightClimb = rightClimb;
        return this;
    }
}
