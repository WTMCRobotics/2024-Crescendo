package frc.robot.auton;

import java.util.ArrayList;
import java.util.List;

public class ParallelActionRunner {

    List<AutonAction> actions = new ArrayList<>();

    public void addActionToRun(AutonAction action) {
        actions.add(action);
        action.initiate();
    }

    public void onEveryFrame() {
        for (int i = 0; i < actions.size(); i++) {
            AutonAction action = actions.get(i);
            if (action.isDone()) {
                actions.remove(i);
                i--;
            }
        }
    }
}
