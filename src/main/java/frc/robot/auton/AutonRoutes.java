package frc.robot.auton;

import frc.robot.Vision.AutonRotateAction;
import java.util.ArrayDeque;
import java.util.List;

public class AutonRoutes {

    public static ArrayDeque<AutonAction> RUN_INTO_WALL_AND_BREAK_ROBOT = new ArrayDeque<>(
        List.of(new AutonMoveForward(36))
    );
    public static ArrayDeque<AutonAction> RUN_INTO_OTHER_WALL_AND_BREAK_ROBOT_AGAIN = new ArrayDeque<>(
        List.of(new AutonMoveForward(-36))
    );

    public static ArrayDeque<AutonAction> SHOOT_AND_RUN_INTO_OTHER_WALL_AND_BREAK_ROBOT = new ArrayDeque<AutonAction>(
        List.of(new AutonShoot(), new AutonMoveForward(-36), new AutonRotate(90))
    );

    public static ArrayDeque<AutonAction> SHOOT_AND_RUN_INTO_OTHER_WALL_AND_BREAK_ROBOT = new ArrayDeque<AutonAction>(
        List.of(new AutonShoot(), new AutonParallelAction(new AutonIntake(), new AutonMoveForward(-36)))
    );

    public static ArrayDeque<AutonAction> SHOOT_AND_RUN_INTO_OTHER_WALL_AND_BREAK_ROBOT = new ArrayDeque<AutonAction>(
        List.of(new AutonShoot(), new AutonMoveForward(-36), new AutonRotate(90))
    );

    public static ArrayDeque<AutonAction> SHOOT_AND_RUN_INTO_OTHER_WALL_AND_BREAK_ROBOT = new ArrayDeque<AutonAction>(
        List.of(
            new AutonParallelAction(
                new AutonSequentialAction(new AutonWait(1.5), new AutonIntake()),
                new AutonMoveForward(-36)
            )
        )
    );
}
