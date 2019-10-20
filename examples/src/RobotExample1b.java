import org.jointheleague.graphical.robot.KeyboardAdapter;
import org.jointheleague.graphical.robot.Robot;

public class RobotExample1b {

    public static void main(String[] args) {

        Robot rob = new Robot();
        rob.setSpeed(10);
        rob.setPenWidth(3);
        rob.penDown();
        for (int i = 0 ; i < 12; i++) {
            rob.setRandomPenColor();
            rob.move(160);
            rob.turn(150);
        }
        rob.sleep(1000);
        rob.hide();
    }
}
