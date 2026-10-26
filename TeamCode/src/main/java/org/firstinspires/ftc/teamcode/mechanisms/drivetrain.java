package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class drivetrain {
    public static GoBildaPinpointDriver pinpoint;
    private static GamepadEx gp;
    private static MecanumDrive drive;

    public drivetrain(HardwareMap hw, GamepadEx gamepad1) {
        pinpoint = hw.get(GoBildaPinpointDriver.class, "pinpoint");
        gp = gamepad1;

        Motor leftFront = new Motor(hw, "frontLeft", Motor.GoBILDA.RPM_1150).setInverted(true);
        Motor leftBack = new Motor(hw, "backLeft", Motor.GoBILDA.RPM_1150).setInverted(true);
        Motor rightFront = new Motor(hw, "frontRight", Motor.GoBILDA.RPM_1150);
        Motor rightBack = new Motor(hw, "backRight", Motor.GoBILDA.RPM_1150);

        leftFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        drive = new MecanumDrive(leftFront, rightFront, leftBack, rightBack);

        // pinpoint initialization
        pinpoint.setOffsets(5, -4.5, DistanceUnit.INCH);
        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.REVERSED
        );
        pinpoint.initialize();
        pinpoint.recalibrateIMU();
    }

    public static void driveFieldCentric() {
        drive.driveFieldCentric(
                gp.getLeftX(),
                gp.getLeftY(),
                gp.getRightX(),
                pinpoint.getHeading(AngleUnit.DEGREES),
                true
        );
    }

    public static void driveRobotCentric() {
        drive.driveRobotCentric(
                gp.getLeftX(),
                gp.getLeftY(),
                gp.getRightX(),
                true
        );
    }

}
