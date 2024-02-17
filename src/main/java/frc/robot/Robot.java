// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Vision.AprilTagHighlighter;
import frc.robot.auton.AutonAction;
import frc.robot.auton.AutonActionRunner;
import frc.robot.auton.AutonRoutes;
import frc.robot.motor.MotorController;
import java.util.ArrayDeque;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRo\bot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    //hi
    // hello!!!!!!!

    AprilTagHighlighter aprilTagHighlighter;
    MotorController driveLeftParent = RobotConfigs.getLeftParent();
    MotorController driveLeftChild = RobotConfigs.getLeftChild();
    MotorController driveRightParent = RobotConfigs.getRightParent();
    MotorController driveRightChild = RobotConfigs.getRightChild();
    XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_ID);
    static AHRS navX = new AHRS(SPI.Port.kMXP);
    AutonActionRunner auton;
    private final SendableChooser<String> autonRouteChooser = new SendableChooser<>();

    public static AHRS getGyroscope() {
        return navX;
    }

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        initializeSmartMotion(driveLeftParent, Constants.NORMAL_ROBOT_GAINS);
        initializeSmartMotion(driveRightParent, Constants.NORMAL_ROBOT_GAINS);

        // aprilTagHighlighter = new AprilTagHighlighter();
        autonRouteChooser.addOption("move forward", "move forward");
        autonRouteChooser.addOption("move backwards", "move backwards");
        SmartDashboard.putData(autonRouteChooser);

        driveLeftChild.follow(driveLeftParent);
        driveRightChild.follow(driveRightParent);

        driveLeftParent.setInverted(true);
        driveRightParent.setInverted(false);

        System.out.println("is drive righjt parent inverted? " + driveRightParent.getInverted());

        driveLeftChild.setBrakeMode(true);
        driveLeftParent.setBrakeMode(true);
        driveRightChild.setBrakeMode(true);
        driveRightParent.setBrakeMode(true);

        QuickActions.setDriveMotors(driveLeftParent, driveRightParent);
        getGyroscope().reset();
        System.out.println(Constants.APRIL_TAG_CONFIDENCE_FRAMES);
        SmartDashboard.putNumber("rotationGainsP", Constants.ROTATION_GAINS.P);
        SmartDashboard.putNumber("rotationGainsI", Constants.ROTATION_GAINS.I);
        SmartDashboard.putNumber("rotationGainsD", Constants.ROTATION_GAINS.D);

        SmartDashboard.putNumber("PIDTARGET", 90);

        System.out.println("is drive righjt parent inverted at end?? " + driveRightParent.getInverted());
    }

    /**
     * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
     * that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // aprilTagHighlighter.doEveryFrame();
        SmartDashboard.putNumber("Gyro Reading", getGyroscope().getAngle());
        SmartDashboard.putNumber("left encoder", driveLeftParent.getEncoderPosition());
        SmartDashboard.putNumber("right encoder", driveRightParent.getEncoderPosition());
        // SmartDashboard.putNumber("Left motor controller encoder", driveLeftParent.getEncoderPosition());
        // SmartDashboard.putNumber("right motor controller encoder", driveRightParent.getEncoderPosition());
        // Constants.ROTATION_GAINS.P = SmartDashboard.getNumber("rotationGainsP", kDefaultPeriod);
        // Constants.ROTATION_GAINS.I = SmartDashboard.getNumber("rotationGainsI", kDefaultPeriod);
        // Constants.ROTATION_GAINS.D = SmartDashboard.getNumber("rotationGainsD", kDefaultPeriod);
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select between different
     * autonomous modes using the dashboard. The sendable chooser code works with the Java
     * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
     * uncomment the getString line to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional comparisons to the switch structure
     * below with additional strings. If using the SendableChooser make sure to add them to the
     * chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        navX.resetDisplacement();
        navX.reset();

        ArrayDeque<AutonAction> route =
            switch (autonRouteChooser.getSelected()) {
                case "move forward" -> new ArrayDeque<>(AutonRoutes.RUN_INTO_WALL_AND_BREAK_ROBOT);
                case "move backwards" -> new ArrayDeque<>(AutonRoutes.RUN_INTO_OTHER_WALL_AND_BREAK_ROBOT_AGAIN);
                default -> new ArrayDeque<AutonAction>();
            };
        System.out.println("selected auton: " + route);
        auton = new AutonActionRunner(route);
        auton.initiateAuton();
    }

    /**Things Auton needs to do:
     *  - TODO auton wait function
     *  - TODO auton shoot function
     *  - TODO auton rotate function
     *  - TODO make it possible to choose auton paths
     *  - TODO auton adjust height of shooter
     *
     * Auton Paths:
     * TODO auton path: Touching Speaker Next To Amp:
     *  - Wait x amount of time
     *  - Shoot
     *  - Go back
     *  - Turn
     *  - Go back more
     *
     * TODO auton Touching Speaker In Middle:
     *  - Wait x amount of time
     *  - Shoot
     *  - Go back
     * Touching Speaker Next To Terminal:
     *  - Wait x amount of time
     *  - Shoot
     *  - Go back
     */

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
        auton.onEveryFrame();
    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {
        // aprilTagHighlighter.sequenceInitiated = false;
        System.out.println("FLP inverted??????:" + driveLeftParent.getInverted());
        System.out.println("FrP inverted??????:" + driveRightParent.getInverted());
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        // TODO: make slow mode
        double leftY = driverController.getLeftY();
        double rightY = driverController.getRightY();
        // TODO the left wheels were moving despite the controller output being 0, why?
        QuickActions.stopAll();
        if (Math.abs(leftY) > .03) {
            driveLeftParent.set(leftY);
        }
        if (Math.abs(rightY) > .03) {
            driveRightParent.set(rightY);
        }
        driverController.setRumble(RumbleType.kBothRumble, 0.1);
        // aprilTagHighlighter.doEveryTeleopFrame(driverController);
    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {
        driverController.setRumble(RumbleType.kBothRumble, 0.0);
    }

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {}

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {
        Thread testThread = new Thread(() -> {
            try {
                driveLeftParent.set(-0.5);
                System.out.println("Left is spinning backwards");
                SmartDashboard.putString("Test Status", "Left is spinning backwards");

                // wait for 5 seconds
                Thread.sleep(5000);
                driveLeftParent.set(0.0);

                driveRightParent.set(-0.5);
                System.out.println("Left is no longer spinning and Right is spinning backwards");
                SmartDashboard.putString("Test Status", "Left is no longer spinning and Right is spinning backwards");

                // wait for 5 seconds pt 2 electric boogaloo
                Thread.sleep(5000);
                driveRightParent.set(0.0);

                System.out.println("Right is no longer spinning");
                SmartDashboard.putString("Test Status", "Right is no longer spinning");
            } catch (InterruptedException e) {
                // If an error occurs, this code will be ran:
                e.printStackTrace();
            }
        });

        testThread.start();
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {}

    /** This function is called once when the robot is first started up. */
    @Override
    public void simulationInit() {}

    /** This function is called periodically whilst in simulation. */
    @Override
    public void simulationPeriodic() {}

    /**
     * WARNING: THIS METHOD WILL RESET MOTOR CONFIG. Do all configuring after this method is called.
     * @param motorController
     * @param gains
     */
    public void initializeSmartMotion(MotorController motorController, Gains gains) {
        /* Factory default hardware to prevent unexpected behavior */
        motorController.reset();

        /* Configure Sensor Source for Primary PID */
        motorController.setSensorSource();

        /*
         * set deadband to super small 0.001 (0.1 %). The default deadband is 0.04 (4 %)
         */
        motorController.setNeutralDeadband(0.001);

        /**
         * Configure Talon SRX Output and Sensor direction accordingly Invert Motor to have green LEDs
         * when driving Talon Forward / Requesting Positive Output Phase sensor to have positive
         * increment when driving Talon Forward (Green LED)
         */

        /* Set relevant frame periods to be at least as fast as periodic rate */
        motorController.setStatusFramePeriod(10);

        /* Set the peak and nominal outputs */
        // motorController.setOutputLimits(0, 0, gains.PEAK_OUTPUT, -gains.PEAK_OUTPUT);

        /* Set Motion Magic gains in slot0 - see documentation */
        motorController.setPID(gains);

        /* Set acceleration and vcruise velocity - see documentation */
        motorController.setMotionSpeed(15000 * Constants.DRIVE_GEARBOX_RATIO, 400 * Constants.DRIVE_GEARBOX_RATIO);
        /* Zero the sensor once on robot boot up */
        motorController.setEncoderPosition(0);
    }
}
