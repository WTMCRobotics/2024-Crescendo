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
                action.shutdown();
                it.remove();
            }
        }
    }

    /**This method is important if you have code that could add two of the same action with opposite goals.
     *That would mean they would fight each other and never finish, which is bad news bears if you ask me.
     * @param clazzez A singular class or a array of classes*/

    public void removeActionsOfType(Class<?>... clazzez) {
        Iterator<AutonAction> it = actions.iterator();
        while (it.hasNext()) {
            AutonAction auction = it.next();
            for (Class<?> clazz : clazzez) {
                if (auction.getClass().equals(clazz)) {
                    System.out.println("Removing " + clazz.getName() + " from parallel action queue");
                    auction.shutdown();
                    it.remove();
                }
            }
        }
    }

    public void removeActionsOfType(AutonAction action) {
        removeActionsOfType(action.getClass());
    }
}
