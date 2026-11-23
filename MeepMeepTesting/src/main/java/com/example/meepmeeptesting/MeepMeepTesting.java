package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        Image fieldImg = null;
        try {
            fieldImg = ImageIO.read(
                    Objects.requireNonNull(MeepMeepTesting.class.getResourceAsStream("/biobuzz.png"))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13.68)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(9, -61.5, Math.toRadians(90)))
                .splineToSplineHeading(new Pose2d(30, -39, Math.toRadians(180)), Math.toRadians(45))
                .splineToConstantHeading(new Vector2d(61, -9), Math.toRadians(45))
                //.addDisplacementMarker(() -> {
                    // Launch Code Here
                    .waitSeconds(0) // Estimated time for launch sequence\
                //})
                .strafeTo(new Vector2d(55, -9))
                .splineToSplineHeading(new Pose2d(58, -58, Math.toRadians(0)), Math.toRadians(0))
                .strafeTo(new Vector2d(61.5, -61.5))
                //.addDisplacementMarker(() -> {
                    // Ball Collect Code Here
                .waitSeconds(0) // Estimated time for collect sequence
                //})
                .strafeTo(new Vector2d(58, -58))
                .splineToConstantHeading(new Vector2d(-30, -41), Math.toRadians(120))
                .splineToConstantHeading(new Vector2d(-61, -9), Math.toRadians(200))



                .build());

        meepMeep.setBackground(fieldImg)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}