package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    private DcMotor leftFront, rightFront, leftBack, rightBack;

    // Коэффициент максимальной скорости (0.5 = 50% от максимальной мощности для плавности)
    private double speedMultiplier = 0.7;

    public void init(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void driveMecanumSmooth(double drive, double strafe, double turn) {
        // Возводим в куб для экспоненциальной плавности (0.1 стика -> 0.001 мощности)
        drive = Math.pow(drive, 3);
        strafe = Math.pow(strafe, 3);
        turn = Math.pow(turn, 3);

        double leftFrontPower = (drive + strafe + turn) * speedMultiplier;
        double rightFrontPower = (drive - strafe - turn) * speedMultiplier;
        double leftBackPower = (drive - strafe + turn) * speedMultiplier;
        double rightBackPower = (drive + strafe - turn) * speedMultiplier;

        // Нормализация мощности
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        leftFront.setPower(leftFrontPower);
        rightFront.setPower(rightFrontPower);
        leftBack.setPower(leftBackPower);
        rightBack.setPower(rightBackPower);
    }

    // Метод для изменения скорости на лету (например, медленный режим)
    public void setSpeedMultiplier(double multiplier) {
        this.speedMultiplier = multiplier;
    }

    private void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        leftFront.setZeroPowerBehavior(behavior);
        rightFront.setZeroPowerBehavior(behavior);
        leftBack.setZeroPowerBehavior(behavior);
        rightBack.setZeroPowerBehavior(behavior);
    }
}