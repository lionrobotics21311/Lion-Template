package org.firstinspires.ftc.teamcode;

import java.util.Set;

import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;

import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;

public class Robot implements NextRobot {

    public final Claw claw = new Claw();
    public final Intake intake = new Intake();

    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(claw, intake);
    }
}