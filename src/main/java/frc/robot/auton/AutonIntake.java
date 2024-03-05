package frc.robot.auton;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.Robot;

public class AutonIntake extends AutonAction {

    static DigitalInput beamBreakSensor = new DigitalInput(Constants.INTAKE_BEAM_BREAK_ID);

    @Override
    public boolean isDone() {
        return beamBreakSensor.get();
    }

    @Override
    public void initiate() {
        Robot.motors.getIntake().set(Constants.INTAKE_SPEED);
    }

    @Override
    public void shutdown() {
        Robot.motors.getIntake().stopMotor();
    }
}
