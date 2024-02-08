package frc.robot.auton;

public class AutonRotate extends AutonAction {

    double rotationAngle;

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void initiate() {}

    public AutonRotate(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
}
