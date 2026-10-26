package org.firstinspires.ftc.teamcode.mechanisms;

import static java.lang.Math.abs;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Configurable
public class Flywheel {
    private final Motor flywheel;
    static int bangbangThreshold = 25;

    public Flywheel(HardwareMap hw) {
        flywheel = new Motor(hw, "flywheel", Motor.GoBILDA.BARE);
    }

    // very basic flywheel controller (bang bang)
    // sets power to 100% until it reaches bang bang threshold, then sets power to 0%
    public void updateFlywheel(double targetRPM) {
        double err = (targetRPM - (flywheel.getCorrectedVelocity()/28*60));

        // Bang-bang control
        if (abs(err) > bangbangThreshold) {
            flywheel.set(err > 0 ? 1 : 0);
        }
    }

}