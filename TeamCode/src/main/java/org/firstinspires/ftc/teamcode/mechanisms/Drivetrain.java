package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.configs.Config;

import java.util.function.Supplier;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.drive.DriveCommands;

public class Drivetrain implements Mechanism {

    // You can either specify the motor by its hardware map name in your config
    // or you can initialize it by the port number.
    // Ref: https://nextftc.dev/hardware/actuators/motors/
    public final NextMotor frontLeft = new NextMotor(RobotController.controlHub(), Config.FrontLeftDrive);
    public final NextMotor frontRight = new NextMotor(RobotController.controlHub(), Config.FrontRightDrive);
    public final NextMotor backLeft = new NextMotor(RobotController.controlHub(), Config.BackLeftDrive);
    public final NextMotor backRight = new NextMotor(RobotController.controlHub(), Config.BackRightDrive);

    // if you are powering your motors on the expansion hub you would use RobotController.expansionHub()
    // instead of RobotController.controlHub()

    public void startDrive(Gamepad gamepad) {
        frontLeft.setZeroPowerBehavior(NextMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(NextMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(NextMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(NextMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setDirection(NextMotor.Direction.REVERSE);
        backRight.setDirection(NextMotor.Direction.REVERSE);

        DriveCommands.mecanumDrive(frontLeft, frontRight, backLeft, backRight, gamepad).schedule();
    }

    // Field centric drive example
    /*
    public void startDrive(Gamepad gamepad, Supplier<Double> heading) {
        frontLeft.setZeroPowerBehavior(NextMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(NextMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(NextMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(NextMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setDirection(NextMotor.Direction.REVERSE);
        backRight.setDirection(NextMotor.Direction.REVERSE);

        DriveCommands.mecanumDriveFieldCentric(frontLeft, frontRight, backLeft, backRight, gamepad, heading).schedule();
    }
     */


    // https://nextftc.dev/robot/drive-commands/#scalar
    public static void setScalar(double scalar) { DriveCommands.setScalar(scalar); }
    public static double getScalar() { return DriveCommands.getScalar(); }
}
