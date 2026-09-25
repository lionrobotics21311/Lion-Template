package org.firstinspires.ftc.teamcode.opmodes.autos;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import dev.nextftc.hardware.sensors.NextPinpoint;
import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;

@NextTeleop(name = "exampleAuto")
public class exampleTele extends NextOpMode {
    private final Robot robot;

    private final NextPinpoint imu = new NextPinpoint("pinpoint");

    // initialization i think`
    public exampleTele(Robot robot) {
        super(robot, BulkReadHook.INSTANCE);
        this.robot = robot;
    }


    // runs right when you click start from init
    @Override
    public void start() {

    }

    // Runs every loop of the op mode until stopped
    @Override
    public void periodic() {
        robot.intake.run(1);

        imu.update(); // updates imu every loop
    }
}
