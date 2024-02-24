package frc.robot.auton;

public abstract class AutonAction {

    public AutonAction() {}

    public abstract boolean isDone();

    public abstract void initiate();
}
