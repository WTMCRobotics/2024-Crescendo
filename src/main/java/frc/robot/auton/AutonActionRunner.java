package frc.robot.auton;

import java.util.ArrayDeque;

public class AutonActionRunner {

    public ArrayDeque<AutonAction> queue;

    public AutonActionRunner(AutonAction... actions) {
        queue = new ArrayDeque<>();
        for (AutonAction action : actions) {
            queue.add(action);
        }
    }

    public AutonActionRunner(ArrayDeque<AutonAction> actions) {
        queue = actions;
    }

    public void initiateAuton() {
        queue.getFirst().initiate();
    }

    public void onEveryFrame() {
        AutonAction action = queue.getFirst();
        if (action.isDone()) {
            queue.removeFirst();
            // // Maybe remove if causes problems
            // onEveryFrame();
            queue.getFirst().initiate();
        }
    }

    public void addActions(AutonAction... actions) {
        for (AutonAction action : actions) {
            queue.add(action);
        }
    }
}
