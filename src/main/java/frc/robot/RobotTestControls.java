package frc.robot;

public class RobotTestControls {

    public static void testPeriodic() {
        if (Robot.getCoDriverController().getLeftBumperPressed()) {
            Robot.motors.getLeftClimb().set(-0.50);
        } else if (Robot.getCoDriverController().getXButtonPressed()) {
            Robot.motors.getLeftClimb().set(0.50);
        } else if (
            Robot.getCoDriverController().getLeftBumperReleased() || Robot.getCoDriverController().getXButtonReleased()
        ) {
            Robot.motors.getLeftClimb().stopMotor();
        }
        if (Robot.getCoDriverController().getYButtonPressed()) {
            Robot.motors.getRightClimb().set(-0.50);
        } else if (Robot.getCoDriverController().getBButtonPressed()) {
            Robot.motors.getRightClimb().set(0.50);
        } else if (
            Robot.getCoDriverController().getYButtonReleased() || Robot.getCoDriverController().getBButtonReleased()
        ) {
            Robot.motors.getRightClimb().stopMotor();
        }
    }
}
