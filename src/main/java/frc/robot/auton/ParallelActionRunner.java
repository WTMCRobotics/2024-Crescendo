package frc.robot.auton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParallelActionRunner {

    List<AutonAction> actions = new ArrayList<>();

    public void addActionToRun(AutonAction action) {
        actions.add(action);
        action.initiate();
    }

    public void onEveryFrame() {
        Iterator<AutonAction> it = actions.iterator();
        while (it.hasNext()) {
            AutonAction action = it.next();
            if (action.isDone()) {
                actions.remove(action);
            }
        }
    }

    // This method is important if you have code that could add two of the same action with opposite goals.
    // That would mean they would fight each other and never finish, which is bad news bears if you ask me.
    public void removeActionsOfType(Class<?> clazz) {
        Iterator<AutonAction> it = actions.iterator();
        while (it.hasNext()) {
            AutonAction auction = it.next();
            if (auction.getClass().equals(clazz)) {
                actions.remove(auction);
            }
        }
    }

    public void removeActionsOfType(AutonAction action) {
        removeActionsOfType(action.getClass());
    }
}
