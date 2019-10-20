import org.jointheleague.graphical.robot.Robot;
import java.awt.Color;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


class RegularPolygon extends Robot implements Runnable {
    private final int numVertices;
    private final int sideLength;

    private RegularPolygon(int numVertices, int sideLength) {
        this.numVertices = numVertices;
        this.sideLength = sideLength;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " running...");
        long start = System.currentTimeMillis();
        setSpeed(2);
        penDown();
        turn(-90);
        drawPolygon();
        penUp();
        System.out.println(Thread.currentThread().getName()+ " done in " + (System.currentTimeMillis() - start) + " ms!");
    }

    private void drawPolygon() {
        final int degrees = 360 / numVertices;
        for (int i = 0; i < numVertices; i++) {
            move(sideLength);
            turn(degrees);
        }
    }

    public static void main(String... args) throws InterruptedException {
        Robot.setWindowColor(Color.WHITE);

        RegularPolygon triangle = new RegularPolygon(3, 100);
        triangle.setPos(200, 300);

        RegularPolygon hexagon = new RegularPolygon(6, 50);
        hexagon.setPos(450, 300);

        RegularPolygon pentagon = new RegularPolygon(5, 60);
        pentagon.setPos(700,300);

        Executor executor = Executors.newFixedThreadPool(3);
        Thread.sleep(1000);
        for (RegularPolygon robot : new RegularPolygon[]{triangle, hexagon, pentagon}) {
            robot.miniaturize();
            executor.execute(robot);
        }
    }
}


