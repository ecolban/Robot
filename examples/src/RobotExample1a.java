import org.jointheleague.graphical.robot.Robot;

public class RobotExample1a {

    public static void main(String[] args) {

        Robot rob = new Robot();
        rob.setSpeed(10);
        rob.penDown();
        for (int i = 0 ; i < 4; i++) {
            rob.move(200);
            rob.turn(90);
        }
    }
}