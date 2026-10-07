package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;

public class Intake implements Mechanism {
    NextMotor motor = new NextMotor(RobotController.controlHub(), Config.Intake);

    public Command run(double pwr) {
        return instant(() -> motor.setThrottle(pwr));
    }
}
