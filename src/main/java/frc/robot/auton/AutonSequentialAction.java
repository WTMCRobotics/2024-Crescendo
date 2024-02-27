package frc.robot.auton;

import java.util.ArrayDeque;
import java.util.List;

public class AutonSequentialAction extends AutonAction {

    private ArrayDeque<AutonAction> actionList;

    /**
     * Makes multiple actions run in sequence, and will only be counted as completed
     * after all queued actions are completed.
     */
    public AutonSequentialAction(AutonAction... allActions) {
        this.actionList = new ArrayDeque<AutonAction>(List.of(allActions));
    }

    @Override
    public boolean isDone() {
        if (actionList.getFirst().isDone()) {
            actionList.poll().shutdown();
        }

        if (actionList.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {}

    @Override
    public void shutdown() {
        if (actionList.size() > 0) {
            actionList.getFirst().shutdown();
        }
    }
}
