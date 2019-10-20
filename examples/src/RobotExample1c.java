import org.jointheleague.graphical.robot.Robot;
import org.jointheleague.graphical.robot.RobotWindow;

import java.awt.*;

public class RobotExample1c {

    public static void main(String[] args) {

        Robot.setWindowColor(Color.WHITE);
        Robot rob = new Robot(350, 340);
        rob.miniaturize();
        rob.setSpeed(10);
        rob.setPenColor(100, 100, 255);
        rob.penDown();
        for (int i = 0 ; i < 10; i++) {
            rob.move(138);
            rob.turn(126);
            rob.move(240);
            rob.turn(126);
        }
        rob.sleep(1000);
        rob.hide();
    }
}
