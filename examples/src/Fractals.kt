import org.jointheleague.graphical.robot.Robot
import java.awt.Color

fun Robot.kochLine(dist: Double, level: Int) {
    if (level <= 0) {
        move(dist.toInt())
    } else {
        val nextDist = dist / 3.0
        val nextLevel = level - 1
        kochLine(nextDist, nextLevel)
        turn(-60.0)
        kochLine(nextDist, nextLevel)
        turn(120.0)
        kochLine(nextDist, nextLevel)
        turn(-60.0)
        kochLine(nextDist, nextLevel)
    }
}

fun Robot.peanoCurve(dist: Double, level: Int) {
    if (level <= 0) {
        move(dist.toInt())
        setRandomPenColor()
    } else {
        val nextDist = dist / 3.0
        val nextLevel = level - 1
        peanoCurve(nextDist, nextLevel)
        turn(90.0)
        peanoCurve(nextDist, nextLevel)
        turn(-90.0)
        peanoCurve(nextDist, nextLevel)
        turn(-90.0)
        peanoCurve(nextDist, nextLevel)
        turn(-90.0)
        peanoCurve(nextDist, nextLevel)
        turn(90.0)
        peanoCurve(nextDist, nextLevel)
        turn(90.0)
        peanoCurve(nextDist, nextLevel)
        turn(90.0)
        peanoCurve(nextDist, nextLevel)
        turn(-90.0)
        peanoCurve(nextDist, nextLevel)
    }
}

private fun sierpinskiMove(robot: Robot, distance: Int, level: Int) {
    if (level <= 0) {
        robot.move(distance)
    } else {
        val d2 = distance / 2
        sierpinskiMove(robot, d2, level - 1)
        robot.turn(-120.0)
        robot.move(d2)
        robot.turn(120.0)
        sierpinskiMove(robot, d2, level - 1)
        robot.turn(120.0)
        robot.move(d2)
        robot.turn(-120.0)
        sierpinskiMove(robot, d2, level - 1)
    }
}

private class HilbertCurve(val robot: Robot) {

    fun moveA(dist: Int, level: Int) {
        if (level > 0) robot.run {
            val nextLevel = level - 1
            moveD(dist, nextLevel)
            turnTo(0.0)
            move(dist)
            moveA(dist, nextLevel)
            turnTo(90.0)
            move(dist)
            moveA(dist, nextLevel)
            turnTo(180.0)
            move(dist)
            moveB(dist, nextLevel)
        }
    }

    fun moveB(dist: Int, level: Int) {
        if (level > 0) robot.run {
            val nextLevel = level - 1
            moveC(dist, nextLevel)
            turnTo(-90.0)
            move(dist)
            moveB(dist, nextLevel)
            turnTo(180.0)
            move(dist)
            moveB(dist, nextLevel)
            turnTo(90.0)
            move(dist)
            moveA(dist, nextLevel)
        }
    }

    fun moveC(dist: Int, level: Int) {
        if (level > 0) robot.run {
            val nextLevel = level - 1
            moveB(dist, nextLevel)
            turnTo(180.0)
            move(dist)
            moveC(dist, nextLevel)
            turnTo(-90.0)
            move(dist)
            moveC(dist, nextLevel)
            turnTo(0.0)
            move(dist)
            moveD(dist, nextLevel)
        }
    }

    fun moveD(dist: Int, level: Int) {
        if (level > 0) robot.run {
            val nextLevel = level - 1
            moveA(dist, nextLevel)
            turnTo(90.0)
            move(dist)
            moveD(dist, nextLevel)
            turnTo(0.0)
            move(dist)
            moveD(dist, nextLevel)
            turnTo(-90.0)
            move(dist)
            moveC(dist, nextLevel)
        }
    }
}

private fun peanoCurveDemo() {
    val rob = Robot(50, 580)
    Robot.setWindowColor(Color.WHITE)
    rob.run {
        miniaturize()
        penDown()
        penWidth = 4
        setSpeed(100)
        turn(45.0)
        peanoCurve(800.0, 3)
        hide()
    }
}

private fun kochSnowflakeDemo() {
    val rob = Robot(50, 450)
    rob.run {
        miniaturize()
        penDown()
        setSpeed(3)
        turn(30.0)
        setRandomPenColor()
        repeat(3) {
            kochLine(500.0, 2)
            turn(120.0)
        }
        hide()
    }
}

private fun sierpinskiDemo() {
    val rob = Robot(250, 400)
    Robot.setWindowColor(255, 255, 255)
    rob.miniaturize()
    rob.setSpeed(100)
    rob.turn(90.0)
    rob.penDown()
    // At this point, rob is in position, pen down and all ...
    sierpinskiMove(rob, 384, 5)
    // Create an envelope
    rob.turn(-120.0)
    rob.move(384)
    rob.turn(-120.0)
    rob.move(384)
    // Triangle is complete
    rob.sleep(1000)
    rob.hide()
}

private fun hilbertCurveDemo() {
    val robot = Robot()
    val hilbert = HilbertCurve(robot)
    Robot.setWindowColor(Color.WHITE)
    robot.run {
        miniaturize()
        setSpeed(10)
        penWidth = 1
        penDown()
        val cx = x
        val cy = y
        penColor = Color.RED
        setPos(cx - 64F, cy + 64F)
        hilbert.moveA(128, 1)
        setPos(cx - 96F, cy + 96F)
        penColor = Color.BLUE
        hilbert.moveA(64, 2)
        setPos(cx - 112F, cy + 112F)
        penColor = Color.PINK
        hilbert.moveA(32, 3)
        setSpeed(100)
        setPos(cx - 120F, cy + 120F)
        penColor = Color.GREEN
        hilbert.moveA(16, 4)
        setPos(cx - 124F, cy + 124F)
        penColor = Color.GRAY
        hilbert.moveA(8, 5)
        hide()
    }
}


fun main() {
//    kochSnowflakeDemo()
//    peanoCurveDemo()
    hilbertCurveDemo()
//    sierpinskiDemo()
}