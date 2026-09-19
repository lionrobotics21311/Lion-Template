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

import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextAutonomous;
import dev.nextftc.robot.opmode.NextOpMode;

@NextAutonomous(name = "exampleAuto")
public class exampleAuto extends NextOpMode {
    private Robot robot; // import mechs to be used

    public exampleAuto(Robot robot) {
        super(robot, BulkReadHook.INSTANCE); // Bulk read sensors
    }

    // Pedro Pathing paths
    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 8, 90);
    private final Pose path1 = poseFactory.of(26.9099, 58.2665, 180);

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(follow(follower, path1()));
    }

    // runs right when you click start from init
    @Override
    public void start() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();

        schedule(autoRoutine());
    }

    // Runs every loop of the op mode until stopped
    @Override
    public void periodic() {
        robot.intake.run(1); // runs the intake at 100% power

        follower.update();
        Scheduler.execute();

        telemetry.addData("x", follower.pose().x());
        telemetry.addData("y", follower.pose().y());
        telemetry.addData("heading", follower.pose().heading());

        if (follower.currentPath() != null) {
            telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
            telemetry.addData("Path number", follower.pathIndex());
        }
    }

    public Path path1() {
        return line(start, path1).linear(start, path1);
    }
}
