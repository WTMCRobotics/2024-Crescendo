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
        if (queue.isEmpty()) {
            return;
        }
        AutonAction action = queue.getFirst();
        if (action.isDone()) {
            queue.removeFirst();
            if (queue.isEmpty()) {
                System.out.println("We have finished Auton!");
                return;
            }
            queue.getFirst().initiate();
        }
    }

    public void addActions(AutonAction... actions) {
        for (AutonAction action : actions) {
            queue.add(action);
        }
    }
}
