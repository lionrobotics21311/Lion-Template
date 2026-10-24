package org.firstinspires.ftc.teamcode;

import java.util.Set;

import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;

import org.firstinspires.ftc.teamcode.mechanisms.Arm;
import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.flyWheel;
import org.firstinspires.ftc.teamcode.mechanisms.intake_left;
import org.firstinspires.ftc.teamcode.mechanisms.intake_right;

public class Robot implements NextRobot {

    public final Claw claw = new Claw();
    public final Intake intake = new Intake();
    public final Drivetrain drivetrain = new Drivetrain();
    public final flyWheel flyWheel = new flyWheel();
    public final intake_right intake_right = new intake_right();
    public final intake_left intake_left = new intake_left();

    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(
                claw,
                intake
        );
    }

    public Robot(){}
}