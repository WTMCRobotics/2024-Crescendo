package frc.robot;

public class Constants {

    /*DRIVE TRAIN MOTOR IDs*/
    public static final int DRIVE_LEFT_PARENT_ID = 11;
    public static final int DRIVE_LEFT_CHILD_ID = 21;
    public static final int DRIVE_RIGHT_PARENT_ID = 12;
    public static final int DRIVE_RIGHT_CHILD_ID = 22;

    /*DRIVE TRAIN CONFIG */
    public static final double CONTROLLER_DEADZONE = 0.06;

    /*CONTROLLER PORT IDs*/
    public static final int DRIVER_CONTROLLER_ID = 0;
    public static final int CODRIVER_CONTROLLER_ID = 1;

    /*PID GAINS *///0.00001, 100
    public static final Gains ROTATION_GAINS = new Gains(0.011, 0.0001, 0.000935, 0, 0, 0.3);
    public static final double ROTATION_DEGREE_TOLERANCE = 3;
    public static final double ROTATION_DEGREE_PER_SECOND_TOLERANCE = 30;
    public static final Gains NORMAL_ROBOT_GAINS = new Gains(0.0005, 0.0, 0, 0, 0, 1.);
    public static final Gains NON_SMART_MOTION_MOVEMENT_PID_ROBOT_GAINS = new Gains(0.0005, 0.0, 0, 0, 0, 1.);

    public static final double MOVEMENT_PID_INCH_TOLERANCE = 3;
    public static final double MOVEMENT_PID_INCH_PER_SECOND_TOLERANCE = 30;

    /*PHYSICAL ROBOT CONSTANTS */
    public static final double WHEEL_CIRCUMFERENCE_INCHES = 6 * Math.PI;

    //8.46
    public static final double DRIVE_GEARBOX_RATIO = 10.7;// Important: Find this out ASAP

    /* Jack's helper class that should probably have been retired */
    /** Which PID slot to pull gains from */
    public static final int SLOT_IDX = 0;
    /** Which PID loop to pull gains from */
    public static final int PID_LOOP_IDX = 0;
    /** amount of time in ms to wait for confirmation */
    public static final int TIMEOUT_MS = 30;

    public static final int ENCODER_ROTATION = 4096;
}
