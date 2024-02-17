package frc.robot.auton;

import java.util.ArrayDeque;
import java.util.List;

public class AutonRoutes {

    public static ArrayDeque<AutonAction> RUN_INTO_WALL_AND_BREAK_ROBOT = new ArrayDeque<>(
        List.of(new AutonMoveForward(36))
    );
    public static ArrayDeque<AutonAction> RUN_INTO_OTHER_WALL_AND_BREAK_ROBOT_AGAIN = new ArrayDeque<>(
        List.of(new AutonMoveForward(-36))
    );
}
