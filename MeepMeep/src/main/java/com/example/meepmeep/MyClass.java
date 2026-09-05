package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import org.rowlandhall.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MyClass {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(15, 17) // ширина, длина в дюймах
                .setColorScheme(new ColorSchemeRedDark()) // цвет робота
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-62.5, -28, Math.toRadians(270)))
                        /*.back(55.1)
                        .turn(Math.toRadians(-53))
                        .back(24)
                        .turn(Math.toRadians(45))
                        .forward(17)
                        .turn(Math.toRadians(45))
                        .forward(40)
                        .back(40)
                        .turn(Math.toRadians(-45))
                        .back(17)
                        .forward(30)
                        .turn(Math.toRadians(90))*/
                        //.forward(0)
                        .strafeLeft(40)
                        .waitSeconds(2) 
                        .strafeLeft(11)
                        .forward(25)
                        .forward(-25)
                        .strafeRight(11)
                        .waitSeconds(2)
                        .strafeLeft(34.5)
                        .forward(25)
                        .forward(-25)
                        .strafeRight(34.5)
                        .waitSeconds(2)
                        .strafeLeft(58)
                        .forward(25)
                        .forward(-25)
                        .strafeRight(58)
                        .waitSeconds(2)
                        .build());


        Image img = null;
        try {
            img = ImageIO.read(new File("C:\\Users\\Public\\Documents\\ftc\\road-runner-quickstart-master\\FtcRobotController\\src\\main\\res\\decode.jpg"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        meepMeep.setBackground(img)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f) // 0.0–1.0, чем меньше — тем прозрачнее фон
                .addEntity(myBot)
                .start();
    }
}