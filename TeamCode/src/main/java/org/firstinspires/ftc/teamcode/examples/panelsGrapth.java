package org.firstinspires.ftc.teamcode.examples;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;

import org.firstinspires.ftc.teamcode.Robot;

import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;

@NextTeleop(name = "Panels Grapth")
public class panelsGrapth extends NextOpMode {
    private final TelemetryManager panelsTele = PanelsTelemetry.INSTANCE.getTelemetry();

    public panelsGrapth(Robot robot) {
        super(robot);
    }

    // runs right when you click start from init
    @Override
    public void start() {
    }

    // Runs every loop of the op mode until stopped
    @Override
    public void periodic() {
        double voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();
        panelsTele.addData("voltage", voltage);

        panelsTele.update();
    }
}
