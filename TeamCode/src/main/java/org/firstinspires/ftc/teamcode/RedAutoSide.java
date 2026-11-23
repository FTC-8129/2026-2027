package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "Red Auto Side", group = "Red")
public class RedAutoSide extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor launchA = hardwareMap.get(DcMotor.class, "launchA");
        DcMotor launchB = hardwareMap.get(DcMotor.class, "launchB");
        Servo launchArm = hardwareMap.get(Servo.class, "launchArm");
        Servo spindexer = hardwareMap.get(Servo.class, "spindexer");

        spindexer.setPosition(0.0);
        launchArm.setPosition(0.0); // Arm start position
        launchA.setPower(0.0);
        launchB.setPower(0.0);

        Pose2d startPose = new Pose2d(9, -61.5, Math.toRadians(90));
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose);

        TrajectorySequence trajectory = drive.trajectorySequenceBuilder(startPose)
                .splineToSplineHeading(new Pose2d(30, -39, Math.toRadians(180)), Math.toRadians(45))
                .splineToConstantHeading(new Vector2d(61, -9), Math.toRadians(45))
                .addDisplacementMarker(() ->
                        launchFourBalls(launchA, launchB, launchArm, spindexer))
                .build();

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        try {
            drive.followTrajectorySequence(trajectory);
        } finally {
            launchA.setPower(0.0);
            launchB.setPower(0.0);
        }
    }

    private void launchFourBalls(DcMotor launchA, DcMotor launchB,
                                 Servo launchArm, Servo spindexer) {
        ElapsedTime timer = new ElapsedTime();

        launchA.setPower(1.0); // Launch motor A full speed
        launchB.setPower(1.0); // Launch motor B full speed

        spindexer.setPosition(0.0); // First ball position
        timer.reset();
        while (timer.milliseconds() < 300 && opModeIsActive()) {} // Wait for the spindexer
        launchArm.setPosition(1.0); // Arm out for first ball
        timer.reset();
        while (timer.milliseconds() < 500 && opModeIsActive()) {} // Wait for the arm
        launchArm.setPosition(0.0); // Arm back
        timer.reset();
        while (timer.milliseconds() < 500 && opModeIsActive()) {}

        spindexer.setPosition(0.25); // Second ball position
        timer.reset();
        while (timer.milliseconds() < 300 && opModeIsActive()) {} // Wait for the spindexer
        launchArm.setPosition(1.0); // Arm out for second ball
        timer.reset();
        while (timer.milliseconds() < 500 && opModeIsActive()) {} // Wait for the arm
        launchArm.setPosition(0.0); // Arm back
        timer.reset();
        while (timer.milliseconds() < 500 && opModeIsActive()) {}

        spindexer.setPosition(0.50); // Third ball position
        timer.reset();
        while (timer.milliseconds() < 300 && opModeIsActive()) {} // Wait for the spindexer
        launchArm.setPosition(1.0); // Arm out for third ball
        timer.reset();
        while (timer.milliseconds() < 500 && opModeIsActive()) {} // Wait for the arm
        launchArm.setPosition(0.0); // Arm back
        timer.reset();
        while (timer.milliseconds() < 500 && opModeIsActive()) {}

        spindexer.setPosition(0.75); // Fourth ball position
        timer.reset();
        while (timer.milliseconds() < 300 && opModeIsActive()) {} // Wait for the spindexer
        launchArm.setPosition(1.0); // Arm out for fourth ball
        timer.reset();
        while (timer.milliseconds() < 500 && opModeIsActive()) {} // Wait for the arm
        launchArm.setPosition(0.0); // Arm back
        timer.reset();
        while (timer.milliseconds() < 500 && opModeIsActive()) {}

        spindexer.setPosition(0.0); // Return to starting position
    }
}
