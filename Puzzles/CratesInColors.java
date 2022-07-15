
/***********************************************************************
 *                                                                      *
 * OnbotJava Editor is still : beta! Please inform us of any bugs      *
 * on our discord channel! https://discord.gg/e7nVjMM                   *
 * Only BLOCKS code is submitted when in Arena                          *
 *                                                                      *
 ***********************************************************************/
import java.lang.Math.*;

public class CratesInColors extends LinearOpMode {
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
        turnRight(400);
        move(1000);
        sleep(400);
        // Put run blocks here
        while (opModeIsActive()) {
            var sensed = Color.rgb(color1.red(), (color1.green()), (color1.blue()));
            telemetry.addData("HUE", (JavaUtil.colorToHue(sensed)));
            if(JavaUtil.colorToHue(sensed)<10){
                move(-1000);
                turnLeft(400);
                move(1300);
                sleep(300);
                move(700);
                sleep(400);
                turnRight(400);
                move(1500);
                move(400);
                turnLeft(400);
                move(800);
                turnLeft(400);
                move(800);
            }else{
                move(200);
                turnLeft(400);
                move(1300);
                sleep(300);
                move(1000);
                turnLeft(400);
                move(1300);
                sleep(400);
                move(400);
                turnRight(400);
                move(500);
                turnRight(400);
                move(700);

            }
            // Put loop blocks here
        }
    }

}
