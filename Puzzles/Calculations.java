
/***********************************************************************
 *                                                                      *
 * OnbotJava Editor is still : beta! Please inform us of any bugs      *
 * on our discord channel! https://discord.gg/e7nVjMM                   *
 * Only BLOCKS code is submitted when in Arena                          *
 *                                                                      *
 ***********************************************************************/
import java.lang.Math.*;

public class Calculations extends LinearOpMode {
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

    public void upTo(int distance){
        while(opModeIsActive()&&distance1.getDistance(DistanceUnit.CM)>distance){
            motorLeft.setPower(0.3);
            motorRight.setPower(0.3);
        }
        stop(100);
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
        int color1 = 0;
        int d1 = 0;
        int k1 = 0;
        boolean shouldturn1 = false;
        // Put initialization blocks here
        waitForStart();
        move(500);
        sleep(500);
        var sensed = Color.rgb(color1.red(), (color1.green()), (color1.blue()));
        telemetry.addData("HUE", (JavaUtil.colorToHue(sensed)));
        if(JavaUtil.colorToHue(sensed)<10){
            turnLeft(400);
            move(2000);
            move(-150);
            turnRight(400);
            move(850);
            turnRight(400);
            if(distance1.getDistance(DistanceUnit.CM)>50){
                move(1000);
                sleep(400);
                var sensed = Color.rgb(color1.red(), (color1.green()), (color1.blue()));
                telemetry.addData("HUE", (JavaUtil.colorToHue(sensed)));
                if(JavaUtil.colorToHue(sensed)<10&&distance1.getDistance(DistanceUnit.CM)>50){
                    move(2000);
                    move(-150);
                    turnLeft(400);
                    move(2000);
                    move(-150);
                    turnLeft(400);
                    move(1500);
                }else if(JavaUtil.colorToHue(sensed)<10&&distance1.getDistance(DistanceUnit.CM)<50){
                    move(-900);
                    turnRight(400);
                    move(900);
                    turnLeft(400);
                    move(2500);
                    move(-150);
                    turnLeft(400);
                    move(3000);
                    move(-150);
                    turnLeft(400);
                    move(1300);
                }else{
                    turnLeft(400);
                    move(1000);
                }
            }else{
                turnLeft(400);
                move(1300);
                turnRight(400);
                move(1400);
            }

        }else{
            move(900);
            sleep(400);
            var sensed = Color.rgb(color1.red(), (color1.green()), (color1.blue()));
            telemetry.addData("HUE", (JavaUtil.colorToHue(sensed)));
            turnRight(400);
            if(JavaUtil.colorToHue(sensed)<10&&distance1.getDistance(DistanceUnit.CM)>50){
                move(1000);
                turnLeft(400);
                move(700);
                turnLeft(400);
                move(1500);
            }else if(JavaUtil.colorToHue(sensed)<10&&distance1.getDistance(DistanceUnit.CM)<50){
                turnLeft(400);
                move(-3000);
                move(150);
                turnRight(400);
                move(1000);
                turnLeft(400);
                move(4000);
                move(-150);
                turnLeft(400);
                move(1400);
            }else{
                turnLeft(400);
                move(1000);
            }
        }
    }
}
