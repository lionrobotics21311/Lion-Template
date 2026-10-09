package org.firstinspires.ftc.teamcode.configs;

public class Config {
    // Drivetrain
    public static int FrontLeftDrive = 0;
    public static int FrontRightDrive = 1;
    public static int BackLeftDrive = 2;
    public static int BackRightDrive = 3;

    // Your pinpoint should NEVER be plugged into port 0
    // Port 0 is used by the internal IMU and cannot be used by an external IMU
    public static int PinpointIMU = 1;

    public static int Claw = 1;
    public static int Intake = 0;
    public static int Arm = 1;
}
