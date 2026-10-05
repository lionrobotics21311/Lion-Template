package org.firstinspires.ftc.teamcode.examples;

import com.bylazar.field.FieldManager;
import com.bylazar.field.PanelsField;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;

import org.firstinspires.ftc.teamcode.Robot;

import dev.nextftc.hardware.sensors.NextPinpoint;
import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;

@NextTeleop(name = "Panels Field")
public class panelsField extends NextOpMode {
    private final TelemetryManager panelsTele = PanelsTelemetry.INSTANCE.getTelemetry();

    private final FieldManager panelsField = PanelsField.INSTANCE.getField();

    private final NextPinpoint imu = new NextPinpoint("pinpoint");

    private final Robot robot;

    public panelsField(Robot robot) {
        super(robot, BulkReadHook.INSTANCE);
        this.robot = robot;
    }

    // runs right when you click start from init
    @Override
    public void start() {
        panelsField.setOffsets(PanelsField.INSTANCE.getPresets().getPEDRO_PATHING());
    }

    // Runs every loop of the op mode until stopped
    @Override
    public void periodic() {
        double voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();
        panelsTele.addData("voltage", voltage);

        panelsTele.update();
    }
}
