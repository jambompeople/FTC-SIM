
/***********************************************************************
 *                                                                      *
 * OnbotJava Editor is still : beta! Please inform us of any bugs      *
 * on our discord channel! https://discord.gg/e7nVjMM                   *
 * Only BLOCKS code is submitted when in Arena                          *
 *                                                                      *
 ***********************************************************************/
import java.lang.Math.*;

public class Grid extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    Servo servo1;
    ColorSensor color1;
    DistanceSensor distance1;
    Gyroscope imu;

    public void move(int duration){
        if(duration<0){
            motorLeft.setPower(-0.3);
            motorRight.setPower(-0.3);
            sleep(Math.abs(duration));
            stop(100);
        }else{
            motorLeft.setPower(0.3);
            motorRight.setPower(0.3);
            sleep(duration);
            stop(100);
        }

    }
    public void move(int duration, int power){
        if(duration<0){
            motorLeft.setPower(-power);
            motorRight.setPower(-power);
            sleep(Math.abs(duration));
            stop(100);
        }else{
            motorLeft.setPower(power);
            motorRight.setPower(power);
            sleep(duration);
            stop(100);
        }

    }

    public void turnLeft(int time){
        motorLeft.setPower(-0.3);
        motorRight.setPower(0.3);
        sleep(time);
        stop(100);
    }

    public void turnRight(int time){
        motorLeft.setPower(0.3);
        motorRight.setPower(-0.3);
        sleep(time);
        stop(100);
    }

    public void stop(int time){
        motorLeft.setPower(0);
        motorRight.setPower(0);
        sleep(time);
    }

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
        imu = hardwareMap.get(Gyroscope.class, "imu");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        // Put initialization blocks here
        waitForStart();
        move(2500);
        sleep(400);
        // Put run blocks here
        while (opModeIsActive()) {
            var sensed = Color.rgb(color1.red(), (color1.green()), (color1.blue()));
            telemetry.addData("HUE", (JavaUtil.colorToHue(sensed)));
            if(JavaUtil.colorToHue(sensed)<10){
                move(-1200);
                turnRight(400);
                move(1600);
                sleep(800);
                move(-500);
                move(900*2, 1);
                move(-3000);
                move(300);
                turnRight(400);
                move(1100);
                turnLeft(400);
                move(2350);
                turnLeft(400);
                move(3000);
                sleep(400);
                move(500);
                turnRight(300);
                move(500);
                break;
            }else{
                move(-2500);
                turnRight(400);
                move(1100);
                turnLeft(400);
                move(1800,0.4);
                sleep(800);
                move(-500);
                move(1400, 0.6);
                move(-3000);
                move(200);
                turnLeft(400);
                move(2000);
                move(-200);
                turnRight(400);
                move(2300);
                turnRight(400);
                move(2500);
                sleep(400);
                move(500);
                turnLeft(300);
                move(500);
                break;
            }
            // Put loop blocks here
        }
    }

}
