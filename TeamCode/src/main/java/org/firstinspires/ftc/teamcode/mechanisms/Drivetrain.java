package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.drive.DriveCommands;

public class Drivetrain implements Mechanism {

    // You can either specify the motor by its hardware map name in your config file
    // or you can initialize it by the port number.
    // Ref: https://nextftc.dev/hardware/actuators/motors/
    public final NextMotor frontLeft = new NextMotor(RobotController.controlHub(), Config.FrontLeftDrive);
    public final NextMotor frontRight = new NextMotor(RobotController.controlHub(), Config.FrontRightDrive);
    public final NextMotor backLeft = new NextMotor(RobotController.controlHub(), Config.BackLeftDrive);
    public final NextMotor backRight = new NextMotor(RobotController.controlHub(), Config.BackRightDrive);

    public void startDrive(Gamepad gamepad) {
        DriveCommands.mecanumDrive(frontLeft, frontRight, backLeft, backRight, gamepad).schedule();
    }

    // https://nextftc.dev/robot/drive-commands/#scalar
    public static void setScalar(double scalar) { DriveCommands.setScalar(scalar); }
    public static double getScalar() { return DriveCommands.getScalar(); }
}
