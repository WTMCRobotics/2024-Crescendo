package frc.robot.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import java.util.ArrayDeque;
import java.util.List;

public class AutonRoutes {

    public static final ArrayDeque<AutonAction> SHOOT_AND_RUN_INTO_OTHER_WALL_AND_BREAK_ROBOT = new ArrayDeque<
        AutonAction
    >(
        List.of(
            new AutonParallelAction(
                new AutonSequentialAction(new AutonWait(1.5), new AutonIntake()),
                new AutonMoveInches(-36)
            )
        )
    );

    public static final ArrayDeque<AutonAction> GO_BACKWARD_OUT_OF_STARTING_ZONE = new ArrayDeque<AutonAction>(
        (List.of(new AutonMoveInches(-84)))
    );

    public static final ArrayDeque<AutonAction> GO_FORWARD_OUT_OF_STARTING_ZONE = new ArrayDeque<AutonAction>(
        List.of(new AutonMoveInches(84))
    );

    public static final ArrayDeque<AutonAction> SHOOT_AND_BACK_UP = new ArrayDeque<AutonAction>(
        List.of(new AutonShoot(), new AutonMoveInches(-84))
    );

    public static ArrayDeque<AutonAction> BACKUP_TURN_BACKUP;

    private static final ArrayDeque<AutonAction> BLUE_BACKUP_TURN_BACKUP = new ArrayDeque<AutonAction>(
        List.of(new AutonMoveInches(-24), new AutonRotate(-135), new AutonMoveInches(36))
    );

    private static final ArrayDeque<AutonAction> RED_BACKUP_TURN_BACKUP = new ArrayDeque<AutonAction>(
        List.of(new AutonMoveInches(-24), new AutonRotate(135), new AutonMoveInches(36))
    );

    static {
        if (DriverStation.getAlliance().get() == Alliance.Red) {
            BACKUP_TURN_BACKUP = RED_BACKUP_TURN_BACKUP;
        } else {
            BACKUP_TURN_BACKUP = BLUE_BACKUP_TURN_BACKUP;
        }
    }

    public static final ArrayDeque<AutonAction> SHOOT_BACKUP_INTAKE_FORWARD_SHOOT = new ArrayDeque<AutonAction>(
        List.of(
            new AutonShoot(),
            new AutonParallelAction(new AutonIntake(), new AutonMoveInches(-84)),
            new AutonMoveInches(84),
            new AutonShoot()
        )
    );
    public static final ArrayDeque<AutonAction> BOOM = new ArrayDeque<AutonAction>(List.of(new ExplodeDaBomb()));
}
