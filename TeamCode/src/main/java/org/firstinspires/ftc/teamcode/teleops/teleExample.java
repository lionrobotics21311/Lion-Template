package org.firstinspires.ftc.teamcode.teleops;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.pedro.PoseStorage;

import java.util.List;

@TeleOp(name = "Example Tele", group = "TeleOp")
@Configurable
public class teleExample extends LinearOpMode {

    @Override
    public void runOpMode() {
        // --- IMU ---
        GoBildaPinpointDriver imu = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        imu.setOffsets(5, -4.5, DistanceUnit.INCH);
        imu.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        imu.initialize();
        imu.recalibrateIMU();
        // --- Auto ---
        // Heading needs to be negative for blue side
        imu.setPosition(new Pose2D(DistanceUnit.INCH, PoseStorage.currentPose.x(), PoseStorage.currentPose.y(), AngleUnit.RADIANS, PoseStorage.currentPose.heading()));

        // --- Drive Motors ---
        Motor leftFront = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        Motor rightFront = new Motor(hardwareMap, "frontRight", Motor.GoBILDA.RPM_435);
        Motor leftBack = new Motor(hardwareMap, "backLeft", Motor.GoBILDA.RPM_435);
        Motor rightBack = new Motor(hardwareMap, "backRight", Motor.GoBILDA.RPM_435);

        leftFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        leftFront.setInverted(false);
        leftBack.setInverted(true);

        MecanumDrive drive = new MecanumDrive(leftFront, rightFront, leftBack, rightBack);

        // -- main stuff ---
        Motor intake = new Motor(hardwareMap, "intake", Motor.GoBILDA.RPM_1150);

        // --- Controllers ---
        GamepadEx driver1 = new GamepadEx(gamepad1);
        GamepadEx driver2 = new GamepadEx(gamepad2);


        // bulk reading of sensors
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            // bulk reading of sensors
            for (LynxModule hub : allHubs) {
                hub.clearBulkCache();
            }

            driver1.readButtons();
            driver2.readButtons();

            // robot centric
            drive.driveRobotCentric(
                    -driver1.getLeftX(),
                    -driver1.getLeftY(),
                    -driver1.getRightX(),
                    true
            );

            // field centric
            /*
            drive.driveFieldCentric(
                    -driver1.getLeftX(),
                    -driver1.getLeftY(),
                    -driver1.getRightX(),
                    imu.getHeading(AngleUnit.DEGREES),
                    true
            );
             */

            // Reset position
            if (driver1.wasJustPressed(GamepadKeys.Button.BACK)) {
                imu.setPosition(new Pose2D(DistanceUnit.INCH, 11, 9, AngleUnit.DEGREES, 0));
            }

            if (driver1.wasJustPressed(GamepadKeys.Button.Y)) {
                imu.setHeading(0, AngleUnit.DEGREES);
            }

            if (driver1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1) {
                intake.set(1);
            }


            imu.update();
            telemetry.update();
        }
    }
}