package org.firstinspires.ftc.teamcode.opmodes.teleop;

import org.firstinspires.ftc.teamcode.configs.Config;
import org.firstinspires.ftc.teamcode.Robot;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.sensors.NextPinpoint;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;

@NextTeleop
public class exampleTele extends NextOpMode {
    private final Robot robot;
    public exampleTele(Robot nextRobot) {
        super(nextRobot);
        this.robot = nextRobot;
    }

    private final NextPinpoint imu = new NextPinpoint(RobotController.controlHub(), Config.PinpointIMU);

    // runs right when you click start from init
    @Override
    public void start() {
        // robot.drivetrain.startDrive(gamepad1);
    }

    // Runs every loop of the op mode until stopped
    @Override
    public void periodic() {
        // robot.intake.run(1);

        imu.update(); // updates imu every loop
    }

    @Override
    public void end() {
    }
}
