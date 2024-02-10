package frc.robot.auton;

import java.util.ArrayDeque;

public class AutonActionRunner {

    public ArrayDeque<AutonAction> queue;

    public AutonActionRunner(AutonAction... actions) {
        for (AutonAction action : actions) {
            queue.add(action);
        }
    }

    public void initiateAuton() {
        queue.getFirst().initiate();
    }

    public void onEveryFrame() {
        AutonAction action = queue.getFirst();
        if (action.executeAndIsDone()) {
            queue.removeFirst();
            // Maybe remove if causes problems
            onEveryFrame();
            queue.getFirst().initiate();
        }
    }

    public void addActions(AutonAction... actions) {
        for (AutonAction action : actions) {
            queue.add(action);
        }
    }
}
