package frc.robot.auton;

public class explodeDaBomb extends AutonAction {

    double rotationAngle;
    static int timer = 5;

    @Override
    public boolean isDone() {
        if (timer == 0) {
            System.out.println("KABLOOM!!!!");
            return true;
        } else if (timer == 1) {
            System.out.println("i warned you");
        } else if (timer == 2) {
            System.out.println("we arent joking");
        } else if (timer == 3) {
            System.out.println("id run if i where you");
        } else if (timer == 4) {
            System.out.println("it's really gonna go boom");
        } else if (timer == 5) {
            System.out.println("it's gonna explode");
        }
        timer--;
        return false;
    }

    @Override
    public void initiate() {
        System.out.println("the robot is exploding");
    }
}
