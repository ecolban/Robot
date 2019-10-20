import org.jointheleague.graphical.robot.Robot;

public class MyRobot extends Robot {

	/**
	 * Draws a regular polygon with center at the robot's current position
	 * 
	 * @param numSides the number of sides of the polygon
	 * @param radius   the radius of the circumscribed circle.
	 */
	public void drawPolygon(int numSides, int radius) {
		int angle = 360 / numSides;
		double sin = Math.sin(Math.toRadians(angle / 2.0));
		int sideLen = (int) (2 * radius * Math.abs(sin));
		penUp();
		move(radius);
		turn(90 + angle / 2.0);
		penDown();
		move(sideLen);
		for (int i = 1; i < numSides; i++) {
			turn(angle);
			move(sideLen);
		}
		penUp();
		turn(-90 + angle / 2.0);
	}

}
