package frc.robot.motor;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkLowLevel.PeriodicFrame;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkLimitSwitch;
import com.revrobotics.SparkPIDController;
import com.revrobotics.SparkRelativeEncoder;
import frc.robot.Constants;

public class SparkMotorController implements MotorController {

    public CANSparkMax controller;
    RelativeEncoder encoder;
    SparkPIDController pid;

    SparkMotorController(int canID) {
        controller = new CANSparkMax(canID, MotorType.kBrushless);
        encoder = controller.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        encoder.setPositionConversionFactor(Constants.DRIVE_GEARBOX_RATIO);
        encoder.setVelocityConversionFactor(Constants.DRIVE_GEARBOX_RATIO);

        // number
        // of ticks
        // per
        // revolution
        pid = controller.getPIDController();
    }

    @Override
    public void reset() {
        controller.restoreFactoryDefaults();
    }

    @Override
    public void setPercentOutput(double speed) {
        controller.set(speed);
    }

    // idk
    public SparkPIDController getPID() {
        return pid;
    }

    @Override
    public void setVelocity(double speed) {
        pid.setReference(speed, CANSparkMax.ControlType.kSmartVelocity);
    }

    @Override
    public void setDistance(double inches) {
        pid.setReference(
            (inches / Constants.WHEEL_CIRCUMFERENCE_INCHES) * Constants.DRIVE_GEARBOX_RATIO,
            CANSparkMax.ControlType.kSmartMotion
        );
    }

    @Override
    public void setBrakeMode(boolean brake) {
        controller.setIdleMode(brake ? IdleMode.kBrake : IdleMode.kCoast);
    }

    @Override
    public void setInverted(boolean inverted) {
        controller.setInverted(inverted);
    }

    @Override
    public void follow(MotorController leader) {
        if (!(leader instanceof SparkMotorController)) throw new IllegalArgumentException(
            "Leader must be the same type of motor controller as the follower"
        );
        controller.follow(((SparkMotorController) leader).controller);
    }

    @Override
    public double getBusVoltage() {
        return controller.getBusVoltage();
    }

    @Override
    public void setSensorSource() {
        // Do nothing
    }

    @Override
    public void setNeutralDeadband(double percent) {
        pid.setSmartMotionAllowedClosedLoopError(percent, 0);
    }

    @Override
    public void setEncoderInverted(boolean inverted) {
        encoder.setInverted(inverted);
    }

    @Override
    public void setStatusFramePeriod(int period) {
        controller.setPeriodicFramePeriod(PeriodicFrame.kStatus0, period);
        controller.setPeriodicFramePeriod(PeriodicFrame.kStatus1, period);
        controller.setPeriodicFramePeriod(PeriodicFrame.kStatus2, period);
        controller.setPeriodicFramePeriod(PeriodicFrame.kStatus3, period);
    }

    @Override
    public void setOutputLimits(double nominalForward, double nominalReverse, double peakForward, double peakReverse) {
        pid.setOutputRange(peakReverse, peakForward);
        // No need for nominal range
    }

    @Override
    public void setPID(double P, double I, double D, double F) {
        pid.setP(P);
        pid.setI(I);
        pid.setD(D);
        pid.setFF(F);
    }

    @Override
    public double getActiveTrajectoryVelocity() {
        // TODO: figure this out
        return encoder.getVelocity();
    }

    @Override
    public double getSensorVelocity() {
        return encoder.getVelocity();
    }

    @Override
    public void setMotionSpeed(double cruiseVelocity, double acceleration) {
        pid.setSmartMotionMaxVelocity(cruiseVelocity, 0);
        pid.setSmartMotionMaxAccel(acceleration, 0);
    }

    @Override
    public double getEncoderPosition() {
        return encoder.getPosition();
    }

    @Override
    public void setEncoderPosition(double position) {
        encoder.setPosition(position);
    }

    @Override
    public boolean getForwardLimit() {
        return controller.getForwardLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen).isPressed();
    }

    @Override
    public boolean getReverseLimit() {
        return controller.getReverseLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen).isPressed();
    }

    @Override
    public void set(double speed) {
        setPercentOutput(speed);
    }

    @Override
    public double get() {
        return controller.get();
    }

    @Override
    public boolean getInverted() {
        return controller.getInverted();
    }

    @Override
    public void disable() {
        controller.disable();
    }

    @Override
    public void stopMotor() {
        controller.stopMotor();
    }

    /**
     * Returns the encoder object being used for encoding.
     *
     * @return The RelativeEncoder that the spark max is using.
     */
    public RelativeEncoder getEncoderObject() {
        return encoder;
    }
}
