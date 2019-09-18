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

private fun peanoCurveDemo() {
    val rob = Robot(50, 580)
    Robot.setWindowColor(Color.WHITE)
    rob.run {
        miniaturize()
        penDown()
        penWidth = 4
        setSpeed(100)
        turn(45.0)
        peanoCurve(800.0, 5)
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

fun main() {
    kochSnowflakeDemo()
//    peanoCurveDemo()
}