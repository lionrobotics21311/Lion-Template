package org.firstinspires.ftc.teamcode.mechanisms;

import static java.lang.Math.abs;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Configurable
public class flywheel {
    private static Motor flywheel;
    static int bangbangThreshold = 25;

    public flywheel(HardwareMap hw) {
        flywheel = new Motor(hw, "flywheel", Motor.GoBILDA.BARE);
    }

    public static void updateFlywheel(double targetRPM) {
        double err = (targetRPM - (flywheel.getCorrectedVelocity()/28*60));

        // Bang-bang control
        if (abs(err) > bangbangThreshold) {
            flywheel.set(err > 0 ? 1 : 0);
        }
    }

}