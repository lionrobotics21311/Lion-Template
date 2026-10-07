package org.firstinspires.ftc.teamcode.mechanisms;

import static dev.nextftc.units.Units.Degrees;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.measuretypes.Angle;

public class Arm implements Mechanism {
    NextMotor motor = new NextMotor(RobotController.expansionHub(), Config.Arm);

    public Command run(double pwr) {
        return instant(() ->
                motor.setThrottle(pwr)
        );
    }

    public Command runToPosition(double targetPosition) {
        return instant(() ->
                motor.setPositionSetpoint(Degrees.of(targetPosition))
        );
    }

    public Angle getCurrentPosition() {
        // Since .getEncoderPosition() returns an Angle object,
        // you need to use .isNear() to check if its in a set position
        // ex: if (.isNear(Degrees.of(43), 43.0)) {};

        return motor.getEncoderPosition();
    }
}
