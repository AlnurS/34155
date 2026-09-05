package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@TeleOp(name = "Main TeleOp", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {

    private final Drivetrain drivetrain = new Drivetrain();

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init(hardwareMap);

        telemetry.addData("Status", "Готов к запуску!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Чтение стиков
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            // Медленный режим (Slow Mode) при зажатом левом триггере/бампере
            if (gamepad1.left_bumper) {
                drivetrain.setSpeedMultiplier(0.3); // 30% мощности для точного позиционирования
            } else { 
                drivetrain.setSpeedMultiplier(0.7); // 70% стандартная скорость
            }

            // Плавное управление
            drivetrain.driveMecanumSmooth(drive, strafe, turn);

            telemetry.addData("Speed Limit", gamepad1.left_bumper ? "Slow (30%)" : "Normal (70%)");
            telemetry.update();
        }
    }
}