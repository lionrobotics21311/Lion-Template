package org.firstinspires.ftc.teamcode.mechanisms;

import static java.lang.Math.abs;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@Configurable
public class Flywheel {
    private final Motor flywheel;
    static int bangbangThreshold = 25;

    public Flywheel(HardwareMap hw) {
        flywheel = new Motor(hw, "flywheel", Motor.GoBILDA.BARE);
    }

    public void updateFlywheel(double targetRPM) {
        double err = (targetRPM - (flywheel.getCorrectedVelocity()/28*60));

        if (abs(err) > bangbangThreshold) {
            flywheel.set(err > 0 ? 1 : 0);
        }
    }
}