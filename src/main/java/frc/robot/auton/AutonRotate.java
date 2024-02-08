package frc.robot.auton;

public class AutonRotate extends AutonAction {

    double rotationAngle;

    @Override
    public boolean executeAndIsDone() {
        return false;
    }

    @Override
    public void initiate() {}

    public AutonRotate(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
}
