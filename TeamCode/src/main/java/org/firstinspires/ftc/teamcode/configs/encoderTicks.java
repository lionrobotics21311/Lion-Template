package org.firstinspires.ftc.teamcode.configs;

import static dev.nextftc.units.Units.Degrees;

import dev.nextftc.units.measuretypes.Angle;

public class encoderTicks {
    public static final double GOBILDA_30_RPM = 5281.1;
    public final double GOBILDA_43_RPM = 3895.9;
    public final double GOBILDA_60_RPM = 2786.2;
    public final double GOBILDA_84_RPM = 537.7;
    public final double GOBILDA_117_RPM = 537.7;
    public final double GOBILDA_223_RPM = 537.7;
    public final double GOBILDA_312_RPM = 537.7;
    public final double GOBILDA_435_RPM = 384.5;
    public final double GOBILDA_1150_RPM = 145.1;
    public final double GOBILDA_1620_RPM = 103.8;
    public final double GOBILDA_6000_RPM = 28.0;


    public static Angle getEncoderConversion(double TicksPerRotation) {
        return Degrees.of(360.0 / TicksPerRotation);
    }
}
