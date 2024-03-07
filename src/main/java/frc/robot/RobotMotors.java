package frc.robot;

import frc.robot.motor.MotorController;

public class RobotMotors {

    private MotorController driveLeftChild;
    private MotorController driveLeftParent;
    private MotorController driveRightParent;
    private MotorController driveRightChild;
    private MotorController feeder;
    private MotorController leftFlywheel;
    private MotorController rightFlywheel;
    private MotorController leftClimb;
    private MotorController rightClimb;
    private MotorController intake;
    private MotorController hoodAdjuster;

    public MotorController getHoodAdjuster() {
        return hoodAdjuster;
    }

    public MotorController getIntake() {
        return intake;
    }

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

    public RobotMotors intake(MotorController intake) {
        this.intake = intake;
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

    public RobotMotors hoodAdjuster(MotorController hoodAdjuster) {
        this.hoodAdjuster = hoodAdjuster;
        return this;
    }
}
