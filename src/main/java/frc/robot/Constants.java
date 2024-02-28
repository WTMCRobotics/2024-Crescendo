package frc.robot;

public class Constants {

    /*DRIVE TRAIN MOTOR IDs*/
    public static final int DRIVE_LEFT_PARENT_ID = 4;
    public static final int DRIVE_LEFT_CHILD_ID = 8;
    public static final int DRIVE_RIGHT_PARENT_ID = 11;
    public static final int DRIVE_RIGHT_CHILD_ID = 3;

    /*CLIMB MOTOR IDS */

    public static final int LEFT_CLIMB_ID = 31;
    public static final int RIGHT_CLIMB_ID = 21;

    /*SHOOTER CONFIG*/
    /**the amount of time we let the wheels rev up when shooting in seconds */
    public static final double REV_TIME = 1.0;

    /*CLIMB CONFIG */
    public static final double CLIMB_EXTENDED_ENCODER_POSITION = 5;
    public static final double CLIMB_RETRACTED_ENCODER_POSITION = 0.5;
    /**the % of max power we give to the arm motors while extending */
    public static final double CLIMB_EXTENSION_SPEED = .25;
    /**the % of max power we give to the arm motors while retracting */
    public static final double CLIMB_RETRACTION_SPEED = -.25;
    /*DRIVE TRAIN CONFIG */
    public static final double CONTROLLER_DEADZONE = 0.06;

    /*CONTROLLER PORT IDs*/
    public static final int DRIVER_CONTROLLER_ID = 0;

    /*START OF VISION CONSTANTS */
    public static final int CAMERA_HEIGHT = 480;
    public static final int CAMERA_WIDTH = 640;
    public static final int CAMERA_BIT_CORRECTION_AMOUNT = 0;
    /**This is the amount of frames in a row that an april tag needs in frame to be to be considered confidently an april tag */
    public static final int APRIL_TAG_CONFIDENCE_FRAMES = 3;
    public static final double APRIL_TAG_ROTATION_ZONE = 5;

    /*PID GAINS *///0.00001, 100
    public static final Gains ROTATION_GAINS = new Gains(0.011, 0.0001, 0.000935, 0, 0, 0.3);
    public static final Gains NORMAL_ROBOT_GAINS = new Gains(0.0005, 0.0, 0, 0, 0, 1.);

    /*PHYSICAL ROBOT CONSTANTS */
    public static final double WHEEL_CIRCUMFERENCE_INCHES = 8 * Math.PI;

    //8.46
    public static final double DRIVE_GEARBOX_RATIO = 10.7;

    /* Jack's helper class that should probably have been retired */
    /** Which PID slot to pull gains from */
    public static final int SLOT_IDX = 0;
    /** Which PID loop to pull gains from */
    public static final int PID_LOOP_IDX = 0;
    /** amount of time in ms to wait for confirmation */
    public static final int TIMEOUT_MS = 30;
    public static final double ENCODER_ROTATION = 4096.0;
    /* ROTATION PID CONSTANTS */

    public static final double ROTATION_ERROR_DEGREES = 5.;
}
