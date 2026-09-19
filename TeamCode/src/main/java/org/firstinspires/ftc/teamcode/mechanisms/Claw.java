package org.firstinspires.ftc.teamcode.mechanisms;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;

import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;

public class Claw implements Mechanism {
    NextServo servo = new NextServo("clawServo");

    public Command open() { return instant(() -> servo.setPosition(0.2)); }
    public Command close() { return instant(() -> servo.setPosition(0.8)); }
}