package org.firstinspires.ftc.teamcode.teleops;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.flywheel;
import org.firstinspires.ftc.teamcode.pedro.PoseStorage;

import java.util.List;

@TeleOp(name = "Example Tele", group = "TeleOp")
@Configurable
public class teleExample extends LinearOpMode {

    @Override
    public void runOpMode() {
        // bulk reading of sensors - DONT DELETE
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) { hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL); }

        // controllers
        GamepadEx driver1 = new GamepadEx(gamepad1);
        GamepadEx driver2 = new GamepadEx(gamepad2);

        // mechanisms
        new drivetrain(hardwareMap, driver1);
        new flywheel(hardwareMap);

        // --- Auto ---
        // Heading needs to be negative for blue side
        drivetrain.pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, PoseStorage.currentPose.x(), PoseStorage.currentPose.y(), AngleUnit.RADIANS, PoseStorage.currentPose.heading()));

        // -- main stuff ---
        Motor intake = new Motor(hardwareMap, "intake", Motor.GoBILDA.RPM_1150);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            // bulk reading of sensors - DONT DELETE
            for (LynxModule hub : allHubs) { hub.clearBulkCache(); }

            driver1.readButtons();
            driver2.readButtons();

            if (driver1.wasJustPressed(GamepadKeys.Button.Y)) {
                drivetrain.pinpoint.setHeading(0, AngleUnit.DEGREES);
            }

            if (driver1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1) {
                intake.set(1);
            } else {
                intake.set(0);
            }

            flywheel.updateFlywheel(3000);

            drivetrain.driveFieldCentric();
            drivetrain.pinpoint.update();
            telemetry.update();
        }
    }
}