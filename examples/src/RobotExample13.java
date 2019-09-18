import org.jointheleague.graphical.robot.Robot;
import org.jointheleague.graphical.robot.RobotWindow;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

public class RobotExample13 {

    public static void main(String[] args) {

        char[] text = "Amazing ROBOTS".toCharArray();
        Robot[] robots = new Robot[text.length];
        for (int i = 0; i < robots.length; i++) {
            if (text[i] == ' ') continue;
            robots[i] = new Robot(100 + 50 * i, 400);
            robots[i].miniaturize();
        }
        RobotWindow window = RobotWindow.getInstance();
        window.setWinColor(Color.WHITE);
//        final Font font = new Font("Times New Roman", Font.PLAIN, 96);
        final Font font = new Font("Helvetica", Font.PLAIN, 96);
        final Graphics2D g2 = (Graphics2D) window.getGraphics();
        final FontRenderContext frc = g2.getFontRenderContext();
        final GlyphVector glyphVector =
                font.layoutGlyphVector(frc, text, 0, text.length, Font.LAYOUT_LEFT_TO_RIGHT);
        final Rectangle2D textBounds = glyphVector.getLogicalBounds();
        final Dimension dimension = window.getSize();
        final float leftMargin = (float) ((dimension.getWidth() - textBounds.getWidth()) / 2);
        final float topMargin = 100F + (float) ((dimension.getHeight() - textBounds.getHeight()) / 2);
        for (int i = 0; i < text.length; i++) {
            if (text[i] == ' ') continue;
            Shape glyphShape = glyphVector.getGlyphOutline(i, leftMargin, topMargin);
            Robot rob = robots[i];
            rob.setSpeed(2);
            rob.setPenWidth(1);
            rob.setRandomPenColor();
            rob.penDown();
            new Thread(() -> {
                rob.followPath(glyphShape.getPathIterator(null), false);
                Rectangle bounds = glyphShape.getBounds();
                rob.penUp();
                rob.moveTo(bounds.x + bounds.width / 2F, 400, false);
                rob.turnTo(0);
            }).start();
        }
    }
}