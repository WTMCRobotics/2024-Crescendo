// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Vision.AprilTagHighlighter;
import frc.robot.motor.MotorController;
import frc.robot.motor.MotorControllerFactory;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRo\bot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    AprilTagHighlighter aprilTagHighlighter;
    MotorController driveLeftParent = MotorControllerFactory.create(
        Constants.DRIVE_LEFT_PARENT_ID,
        MotorController.Type.Talon
    );
    MotorController driveLeftChild = MotorControllerFactory.create(
        Constants.DRIVE_LEFT_CHILD_ID,
        MotorController.Type.Talon
    );
    MotorController driveRightParent = MotorControllerFactory.create(
        Constants.DRIVE_RIGHT_PARENT_ID,
        MotorController.Type.Talon
    );
    MotorController driveRightChild = MotorControllerFactory.create(
        Constants.DRIVE_RIGHT_CHILD_ID,
        MotorController.Type.Talon
    );
    XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_ID);
    static AHRS navX = new AHRS(SPI.Port.kMXP);

    public static AHRS getGyroscope() {
        return navX;
    }

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        aprilTagHighlighter = new AprilTagHighlighter();

        driveLeftChild.setInverted(true);
        driveLeftParent.setInverted(true);

        driveLeftChild.follow(driveLeftParent);
        driveRightChild.follow(driveRightParent);

        QuickActions.setDriveMotors(driveLeftParent, driveRightParent);
        getGyroscope().reset();
        System.out.println(Constants.APRIL_TAG_CONFIDENCE_FRAMES);
        SmartDashboard.putNumber("rotationGainsP", 1);
        SmartDashboard.putNumber("rotationGainsI", 0);
        SmartDashboard.putNumber("rotationGainsD", 0);
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
        aprilTagHighlighter.doEveryFrame();
        SmartDashboard.putNumber("Gyro Reading", getGyroscope().getAngle());
        Constants.ROTATION_GAINS.P = SmartDashboard.getNumber("rotationGainsP", kDefaultPeriod);
        Constants.ROTATION_GAINS.I = SmartDashboard.getNumber("rotationGainsI", kDefaultPeriod);
        Constants.ROTATION_GAINS.D = SmartDashboard.getNumber("rotationGainsD", kDefaultPeriod);
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
    public void autonomousInit() {}

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
    public void autonomousPeriodic() {}

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {
        aprilTagHighlighter.sequenceInitiated = false;
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        double leftY = driverController.getLeftY();
        double rightY = driverController.getRightY();

        driveLeftParent.set(leftY);
        driveRightParent.set(rightY);

        aprilTagHighlighter.doEveryTeleopFrame(driverController);

    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {}

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
}
