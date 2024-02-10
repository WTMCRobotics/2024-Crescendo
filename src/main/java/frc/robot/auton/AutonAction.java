package frc.robot.auton;

public abstract class AutonAction {

    public AutonAction() {}

    public abstract boolean executeAndIsDone();

    public abstract void initiate();
    //public/private abstract TYPE NAME(ARGUMENTS);
}
