package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;

public class explodeDaBomb extends AutonAction {

    static int timer = 5;
    private double targetedTime;

    @Override
    public boolean isDone() {
        //TODO: make ranges for the times so it works
        //TODO: implement a way to test the code without risking an explosion
        //TODO: also make sure it counts down properly
        if (Timer.getFPGATimestamp() == targetedTime - 5000) {
            System.out.println("KABLOOM!!!!");
            return true;
        } else if (Timer.getFPGATimestamp() == targetedTime - 4000) {
            System.out.println("i warned you");
        } else if (Timer.getFPGATimestamp() == targetedTime - 3000) {
            System.out.println("we aren't joking");
        } else if (Timer.getFPGATimestamp() == targetedTime - 2000) {
            System.out.println("id run if i where you");
        } else if (Timer.getFPGATimestamp() == targetedTime - 1000) {
            System.out.println("it's really gonna go boom");
        } else if (Timer.getFPGATimestamp() == targetedTime) {
            System.out.println("it's gonna explode");
        }
        timer--;
        return false;
    }

    @Override
    public void initiate() {
        System.out.println("the robot is exploding");
        targetedTime = Timer.getFPGATimestamp() + 5000;
    }

    @Override
    public void shutdown() {
        System.out.println("Bomb has been defused; counter-terrorists win");
    }
}
