package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.robotcomponents.DriveTrain;

public class InputtedDriverControls {

    enum drivemode {
        CONFLICT,
        ROTATE,
        NONROATATIONAL,
        DEFAULT
    };

    enum drivespeed {
        SLOW,
        HYPERSLOW,
        NORMAL
    };

    public static void onEveryFrame() {
        XboxController controller = Robot.getDriverController();

        drivemode mode;
        drivespeed speed;




        if (controller.getLeftStickButton()&&controller.getRightStickButton()) {
            mode = drivemode.CONFLICT;
        }else if(controller.getLeftStickButton()){
            mode = drivemode.ROTATE;
        }else if (controller.getRightStickButton()){
            mode = drivemode.NONROATATIONAL;
        }else{
            mode = drivemode.DEFAULT;
        }
        if (!(controller.getLeftBumper()^controller.getRightBumper())){
            speed = drivespeed.NORMAL;
        }else{
            speed = controller.getRightBumper()? drivespeed.SLOW: drivespeed.HYPERSLOW;
        }
        

        double leftPower;
        if (speed==drivespeed.NORMAL){
            leftPower = controller.getLeftY() * Math.abs(controller.getLeftY());
        } else if (speed == drivespeed.HYPERSLOW){
            leftPower =controller.getLeftY()/2;
        }else{
            leftPower = controller.getLeftY();
        }

        double rightPower;
        if (speed==drivespeed.NORMAL){
            rightPower = controller.getLeftY() * Math.abs(controller.getLeftY());
        } else if (speed == drivespeed.HYPERSLOW){
            rightPower =controller.getLeftY()/2;
        }else{
            rightPower = controller.getLeftY();
        }
        if (mode ==drivemode.CONFLICT){
            leftPower = 0;
            rightPower = 0;
            Robot.driverController.setRumble(RumbleType.kBothRumble, .5);
        }else if (mode == drivemode.DEFAULT){
            //does nothing
        }else if(mode == drivemode.NONROATATIONAL){
            rightPower = leftPower;
        } else{
            leftPower = controller.getRightX();
            rightPower = -leftPower;
        }
        DriveTrain.driveTank(-leftPower, -rightPower);

        SmartDashboard.putString("Drive Mode", mode.toString());
        SmartDashboard.putString("Drive Speed Type", speed.toString());
        SmartDashboard.putString("Rough Speed Percentage",((int)(((mode==drivemode.ROTATE)?leftPower:(leftPower+rightPower)/2)*100)+ "% Speed"));
    }
}
