package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;

import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;

public class Intake implements Mechanism {
    NextMotor motor = new NextMotor("intakeMotor");

    public Command run(double pwr) {
        return instant(() -> motor.setThrottle(pwr));
    }
}
