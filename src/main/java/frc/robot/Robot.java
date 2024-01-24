// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Vision.AprilTagHighlighter;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    AprilTagHighlighter aprilTagHighlighter;
    Spark driveLeftParent = new Spark(Constants.DRIVE_LEFT_PARENT_ID);
    Spark driveLeftChild = new Spark(Constants.DRIVE_LEFT_CHILD_ID);
    Spark driveRightParent = new Spark(Constants.DRIVE_RIGHT_PARENT_ID);
    Spark driveRightChild = new Spark(Constants.DRIVE_RIGHT_CHILD_ID);
    XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_ID);

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        aprilTagHighlighter = new AprilTagHighlighter();

        System.out.println(Constants.APRIL_TAG_CONFIDENCE_FRAMES);
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
    public void teleopInit() {}

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        double leftY = driverController.getLeftY();
        double rightY = driverController.getRightY();

        driveLeftParent.set(leftY);
        driveRightParent.set(rightY);

        driveLeftChild.set(leftY);
        driveRightChild.set(rightY);
    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {}

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {}

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {}

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
