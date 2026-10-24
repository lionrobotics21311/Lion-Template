package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.configs.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;

public class intake_left implements Mechanism {
    NextServo CRserve = new NextServo(RobotController.controlHub(), Config.intake_left);

    public Command close() { return instant(() -> CRserve.setDirection(NextMotor.Direction.FORWARD)); }
    public Command open() { return instant(() -> CRserve.setDirection(NextMotor.Direction.REVERSE)); }
}
