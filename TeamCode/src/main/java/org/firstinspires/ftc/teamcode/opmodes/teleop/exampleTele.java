package org.firstinspires.ftc.teamcode.opmodes.teleop;

import org.firstinspires.ftc.teamcode.configs.Config;
import org.firstinspires.ftc.teamcode.Robot;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.sensors.NextPinpoint;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;

@NextTeleop
public class exampleTele extends NextOpMode {
    private final Robot robot;

    CommandGamepad gp1 = new CommandGamepad(gamepad1);

    // this is required to use NextFTC robot module.
    public exampleTele(Robot nextRobot) {
        super(nextRobot);
        this.robot = nextRobot;
    }

    private final NextPinpoint imu = new NextPinpoint(RobotController.controlHub(), Config.PinpointIMU);

    // runs right when you click start from init
    @Override
    public void start() {
        robot.drivetrain.startDrive(gamepad1);
    }

    // Runs every loop of the op mode until stopped
    @Override
    public void periodic() {
        // Gamepad Usage - https://nextftc.dev/robot/command-gamepad/ and https://nextftc.dev/robot/triggers/

        // use .whileTrue to run an action while a button is being pressed.
        gp1.rightTrigger()
                .isOver(0.1) // triggers require a value to compare to (double between 0.0 to 1.0)
                .whileTrue(robot.intake.run(1)) // run while the trigger is pressed
                .whileFalse(robot.intake.run(0)); // don't run when the trigger is not pressed


        // use .onTrue when you need an action to run on a button press.
        // useful for macros (series of actions) on
        gp1.rightBumper().onTrue(robot.claw.open());
        gp1.leftBumper().onTrue(robot.claw.close());

        imu.update(); // updates imu every loop
    }
}
