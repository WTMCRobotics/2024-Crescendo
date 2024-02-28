package frc.robot.auton;

public class AutonParallelAction extends AutonAction {

    private AutonAction[] allActions;

    /**
     * Makes multiple actions run at the exact same time, and will only be counted as completed
     * after all queued actions are completed.
     * <p>
     * WARNING! Do NOT put in 2 or more of the same type of action, as it will more than likely run
     * into an infinite loop. Ex. don't say for the arm to move to the top position, and picking up
     * position in the same time.
     */
    public AutonParallelAction(AutonAction... allActions) {
        this.allActions = allActions;
    }

    @Override
    public boolean isDone() {
        boolean isAllDone = true;
        for (AutonAction action : allActions) {
            if (!action.isDone()) {
                isAllDone = false;
            } else if (action.isDone()) {
                action.shutdown();
            }
        }
        return isAllDone;
    }

    @Override
    public void initiate() {}

    @Override
    public void shutdown() {
        for (AutonAction action : allActions) {
            action.shutdown();
        }
    }
}
