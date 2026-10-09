package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.configs.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;

public class Claw implements Mechanism {
    NextServo servo = new NextServo(RobotController.controlHub(), Config.Claw);

    public Command open() { return instant(() -> servo.setPosition(0.2)); }
    public Command close() { return instant(() -> servo.setPosition(0.8)); }
}