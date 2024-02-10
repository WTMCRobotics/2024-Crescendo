package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;

public class AutonWait extends AutonAction {

    private double targetedTime;
    private double delay;

    /**
     * Used to make the robot wait for a specified number of seconds. The thread will not be harmed
     *
     * @param secondsToDelay the seconds number of seconds to delay to make the robot wait, in
     *        seconds (timed in seconds)
     */
    public AutonWait(double secondsToDelay) {
        delay = secondsToDelay;
    }

    @Override
    public boolean isDone() {
        if (Timer.getFPGATimestamp() >= targetedTime) {
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {
        targetedTime = Timer.getFPGATimestamp() + delay;
    }
}
