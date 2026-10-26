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
import org.firstinspires.ftc.teamcode.mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.pedro.PoseStorage;

import java.util.List;


//Go to this site for documentation: https://docs.seattlesolvers.com/
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
        Drivetrain drive = new Drivetrain(hardwareMap, driver1);
        Flywheel flywheel = new Flywheel(hardwareMap);

        // --- Auto ---
        // Heading needs to be negative for blue side
        drive.pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, PoseStorage.currentPose.x(), PoseStorage.currentPose.y(), AngleUnit.RADIANS, PoseStorage.currentPose.heading()));

        // --- Motor Initialization ---
        Motor intake = new Motor(hardwareMap, "intake", Motor.GoBILDA.RPM_1150);

        // --- Standard Servo Initialization ---

        // --- Continuous Rotation Servo Initialization ---

        // --- SERVO POSITION CONSTANTS ---


        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            // bulk reading of sensors - DONT DELETE
            for (LynxModule hub : allHubs) { hub.clearBulkCache(); }

            driver1.readButtons();
            driver2.readButtons();

            if (driver1.wasJustPressed(GamepadKeys.Button.Y)) {
                drive.pinpoint.setHeading(0, AngleUnit.DEGREES);
            }

            if (driver1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1) {
                intake.set(1);
            } else {
                intake.set(0);
            }

            flywheel.updateFlywheel(3000);

            drive.driveFieldCentric();
            drive.pinpoint.update();
            telemetry.update();
        }
    }
}